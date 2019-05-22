package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.app.entity.AppAddClientInfo;
import com.qian.code.ICCardRecordStateCode;
import com.qian.code.OperatorCode;
import com.qian.code.SMSRecordTypeCode;
import com.qian.dao.impl.ClientAirBottleTypeFeeDaoImpl;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.ICCardRecordDaoImpl;
import com.qian.dao.impl.SMSRecordDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.dao.impl.WeChatLoginInfoDaoImpl;
import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.entity.ClientInfo;
import com.qian.entity.FloorSubsidies;
import com.qian.entity.ICCardRecord;
import com.qian.entity.ICCardRecordState;
import com.qian.entity.SMSRecord;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.entity.WeChatLoginInfo;
import com.qian.service.ClientInfoService;
import com.qian.util.CodeUtils;
import com.qian.util.MyRandomUtils;
import com.qian.util.SendSMSUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 下午3:41:24
 * @Description
 */
@Service
@Transactional
public class ClientInfoServiceImpl implements ClientInfoService {
	
	private static Logger logger = Logger.getLogger(ClientInfoServiceImpl.class);
	
	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private ICCardRecordDaoImpl iCCardRecordDaoImpl;

	@Autowired
	private SMSRecordDaoImpl smsRecordDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private WeChatLoginInfoDaoImpl weChatLoginInfoDaoImpl;

	@Autowired
	private ClientAirBottleTypeFeeDaoImpl clientAirBottleTypeFeeDaoImpl;

	public List<ClientInfo> getPageList(int page, int rows) {
		return clientInfoDaoImpl.getPageList(page, rows);
	}

	public List<ClientInfo> getAllList() {

		return clientInfoDaoImpl.getAllList();
	};

	public long getTotalNum() {
		return clientInfoDaoImpl.getTotalNum();
	}

	public int edit(ClientInfo clientInfo) {
		return clientInfoDaoImpl.update(clientInfo);
	}

	public int add(ClientInfo clientInfo, User user) {

		clientInfoDaoImpl.insert(clientInfo);

		clientInfo.setClient_code(CodeUtils.setClientCode(clientInfo));

		if (clientInfo.getCard_code() == null) {
			clientInfo.setCard_code(CodeUtils.setClientCode(clientInfo));
		} else {
			newClientInfoAddICCardRecord(clientInfo, user);
		}

		clientInfo.setPassword("123456");

		return 0;
	}

	public void delByIds(String[] ids) {
		clientInfoDaoImpl.delByIds(ids);
	}

	public ClientInfo findById(Integer clientId) {
		return clientInfoDaoImpl.findById(clientId);
	}

	public ClientInfo getByMobile(List<String> list, Field filed, int page) {

		List<ClientInfo> result = clientInfoDaoImpl.getPageList(list, filed, page, 1);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public long getTotalNum(List<String> list, Field filed) {

		return clientInfoDaoImpl.getTotalNum(list, filed);
	}

	public List<ClientInfo> getPageList(List<String> list, Field filed, int page, int rows) {
		return clientInfoDaoImpl.getPageList(list, filed, page, rows);
	}

	public List<ClientInfo> getPageList(List<String> list, Field filed) {
		return clientInfoDaoImpl.getPageList(list, filed);
	}

	public ClientInfo getClientNameByclientCode(String client_code) {
		return clientInfoDaoImpl.getClientNameByclientCode(client_code);
	}

	public ClientInfo getClientInfoByClientCode(String client_code) {
		return clientInfoDaoImpl.getClientInfoByClientCode(client_code);
	}

	public CommonCode editByOrderView(int client_id, ClientInfo clientInfoTemp, User user) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(client_id);

		if (StringUtils.nonEmpty(clientInfoTemp.getCard_code())) {

			int count = clientInfoDaoImpl.getCardCodeCountNoOwn(clientInfoTemp.getCard_code(), client_id);

			// 不能重复卡号
			if (count > 0) {
				return CommonCode.CARD_INFO_HAS_EXIST;
			}

		}

		if (!hasEditPermission(client_id, user)) {

			addICCardRecord(clientInfo, clientInfoTemp.getCard_code(), user);
			clientInfo.setCard_code(clientInfoTemp.getCard_code());

			clientInfo.setModify_time(new Date());
			clientInfo.setModify_people(user.getFull_name());

			clientInfoDaoImpl.update(clientInfo);

			return CommonCode.OK;
		}

		addICCardRecord(clientInfo, clientInfoTemp.getCard_code(), user);
		clientInfo.setCard_code(clientInfoTemp.getCard_code());

		clientInfo.setClient_name(clientInfoTemp.getClient_name());
		clientInfo.setFixed_tel_number_1(clientInfoTemp.getFixed_tel_number_1());
		clientInfo.setFixed_tel_number_2(clientInfoTemp.getFixed_tel_number_2());
		clientInfo.setMobile_tel_number_1(clientInfoTemp.getMobile_tel_number_1());
		clientInfo.setMobile_tel_number_2(clientInfoTemp.getMobile_tel_number_2());
		clientInfo.setClient_address(clientInfoTemp.getClient_address());
		clientInfo.setRemark(clientInfoTemp.getRemark());
		clientInfo.setTemp_tips(clientInfoTemp.getTemp_tips());
		clientInfo.setFloorSubsidies(clientInfoTemp.getFloorSubsidies());
		clientInfo.setClientType(clientInfoTemp.getClientType());
		clientInfo.setSecondCategory(clientInfoTemp.getSecondCategory());

		clientInfo.setModify_time(new Date());
		clientInfo.setModify_people(user.getFull_name());

		clientInfoDaoImpl.update(clientInfo);

		return CommonCode.OK;
	}

	public ClientInfo getClientInfoByCardCode(String cardCode) {

		int index = cardCode.indexOf("^");

		if (index != -1) {

			String code = cardCode.substring(0, index);

			cardCode = code;
		}

		return clientInfoDaoImpl.getClientInfoByCardCode(cardCode);
	}

	public CommonCode edit(int clientId, ClientInfo clientInfoTemp, User user) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		if (StringUtils.nonEmpty(clientInfoTemp.getCard_code())) {

			int count = clientInfoDaoImpl.getCardCodeCountNoOwn(clientInfoTemp.getCard_code(), clientId);

			// 不能重复卡号
			if (count > 0) {
				return CommonCode.CARD_INFO_HAS_EXIST;
			}

		}

		// 优质客户只能开卡
		if (!hasEditPermission(clientId, user)) {

			addICCardRecord(clientInfo, clientInfoTemp.getCard_code(), user);

			clientInfo.setCard_code(clientInfoTemp.getCard_code());

			clientInfo.setModify_time(new Date());
			clientInfo.setModify_people(user.getFull_name());

			clientInfoDaoImpl.update(clientInfo);

			return CommonCode.OK;
		}

		addICCardRecord(clientInfo, clientInfoTemp.getCard_code(), user);
		clientInfo.setCard_code(clientInfoTemp.getCard_code());

		clientInfo.setOld_client_code(clientInfoTemp.getOld_client_code());
		clientInfo.setClient_name(clientInfoTemp.getClient_name());
		clientInfo.setClient_sex(clientInfoTemp.getClient_sex());
		clientInfo.setClientType(clientInfoTemp.getClientType());
		clientInfo.setFixed_tel_number_1(clientInfoTemp.getFixed_tel_number_1());
		clientInfo.setFixed_tel_number_2(clientInfoTemp.getFixed_tel_number_2());
		clientInfo.setMobile_tel_number_1(clientInfoTemp.getMobile_tel_number_1());
		clientInfo.setMobile_tel_number_2(clientInfoTemp.getMobile_tel_number_2());
		clientInfo.setClient_address(clientInfoTemp.getClient_address());
		clientInfo.setRemark(clientInfoTemp.getRemark());
		clientInfo.setPassword(clientInfoTemp.getPassword());
		clientInfo.setTemp_tips(clientInfoTemp.getTemp_tips());
		clientInfo.setFloorSubsidies(clientInfoTemp.getFloorSubsidies());
		clientInfo.setClientType(clientInfoTemp.getClientType());
		clientInfo.setSecondCategory(clientInfoTemp.getSecondCategory());

		clientInfo.setModify_time(new Date());
		clientInfo.setModify_people(user.getFull_name());

		clientInfoDaoImpl.update(clientInfo);

		return CommonCode.OK;
	}

	public ClientInfo getClientInfoByOldClientCode(String oldClientCode) {

		return clientInfoDaoImpl.getClientInfoByOldClientCode(oldClientCode);
	}

	public boolean newClientInfoAddICCardRecord(ClientInfo clientInfo, User user) {

		if (!StringUtils.nonEmpty(clientInfo.getCard_code())) {
			return false;
		}

		ICCardRecord iCCardRecord = new ICCardRecord();

		iCCardRecord.setClientInfo(clientInfo);
		iCCardRecord.setCard_code(clientInfo.getCard_code());
		iCCardRecord.setOperator(user);
		iCCardRecord.setCreate_time(new Date());
		iCCardRecord.setState(new ICCardRecordState(ICCardRecordStateCode.FIRST_CARD));
		iCCardRecordDaoImpl.insert(iCCardRecord);

		return true;

	}

	public boolean addICCardRecord(ClientInfo clientInfo, String cardCode, User user) {

		ICCardRecord iCCardRecord = new ICCardRecord();

		iCCardRecord.setClientInfo(clientInfo);
		iCCardRecord.setOperator(user);
		iCCardRecord.setCreate_time(new Date());
		iCCardRecord.setCard_code(cardCode);

		if (!StringUtils.nonEmpty(cardCode)) {
			return false;
		}

		if (!StringUtils.nonEmpty(clientInfo.getCard_code())) {
			iCCardRecord.setState(new ICCardRecordState(ICCardRecordStateCode.FIRST_CARD));
		} else {

			if (clientInfo.getCard_code().equals(cardCode)) {
				return false;
			}
			iCCardRecord.setState(new ICCardRecordState(ICCardRecordStateCode.SECOND_CARD));
		}

		iCCardRecordDaoImpl.insert(iCCardRecord);

		return true;
	}

	public CommonCode resetPasswordBySMS(String mobileTelNumber) {

		List<ClientInfo> clients = clientInfoDaoImpl.getByMobile(mobileTelNumber);

		if (clients.size() == 0) {
			return CommonCode.USER_NOT_EXIST;
		}

		String password = MyRandomUtils.get6bitNumber();

		for (ClientInfo clientInfo : clients) {

			clientInfo.setPassword(password);
		}

		clientInfoDaoImpl.update(clients);

		// 发送短信

		SendSMSUtils.sendHuMenResetPasswordBySMS(mobileTelNumber, password);

		// 短信记录
		User operator = userDaoImpl.findById(OperatorCode.WEIXIN);
		SMSRecord smsRecord = new SMSRecord(mobileTelNumber, SMSRecordTypeCode.RESET_PASSWORD, operator);

		smsRecordDaoImpl.insert(smsRecord);

		return CommonCode.OK;
	}

	public CommonCode resetPasswordByClientIdForSMS(String mobileTelNumber, Integer clientId, User operator) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		List<String> mobiles = new ArrayList<String>();

		mobiles.add(clientInfo.getFixed_tel_number_1());
		mobiles.add(clientInfo.getFixed_tel_number_2());
		mobiles.add(clientInfo.getMobile_tel_number_1());
		mobiles.add(clientInfo.getMobile_tel_number_2());

		boolean flag = false;

		for (String s : mobiles) {
			if (mobileTelNumber.equals(s)) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			return CommonCode.USER_NOT_MATCH;
		}

		String password = MyRandomUtils.get6bitNumber();

		clientInfo.setPassword(password);

		clientInfoDaoImpl.update(clientInfo);

		SendSMSUtils.sendHuMenResetPasswordBySMS(mobileTelNumber, password);

		// 短信记录
		SMSRecord smsRecord = new SMSRecord(mobileTelNumber, SMSRecordTypeCode.RESET_PASSWORD, operator);

		smsRecordDaoImpl.insert(smsRecord);

		return CommonCode.OK;
	}

	public CommonCode bindingOpenId(Integer clientId, String openId, String logiMobileTelNumber) {

		WeChatLoginInfo weChatLoginInfo = weChatLoginInfoDaoImpl.findByOpenId(openId);
		logger.error("weChatLoginInfo="+weChatLoginInfo);
		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);
		logger.error("clientInfo="+clientInfo);
		if (weChatLoginInfo == null) {
			weChatLoginInfo = new WeChatLoginInfo();
			weChatLoginInfo.setClientInfo(clientInfo);
			weChatLoginInfo.setPassword(clientInfo.getPassword());
			weChatLoginInfo.setWechat_openid(openId);
			weChatLoginInfo.setLogin_mobile_tel_number(logiMobileTelNumber);
			weChatLoginInfoDaoImpl.insert(weChatLoginInfo);
		} else {

			weChatLoginInfo.setClientInfo(clientInfo);
			weChatLoginInfo.setPassword(clientInfo.getPassword());
			weChatLoginInfo.setLogin_mobile_tel_number(logiMobileTelNumber);
			weChatLoginInfoDaoImpl.update(weChatLoginInfo);
		}

		return CommonCode.OK;
	}

	public CommonCode relieveBindingOpenId(Integer clientId) {

		WeChatLoginInfo weChatLoginInfo = weChatLoginInfoDaoImpl.findByClientId(clientId);

		if (weChatLoginInfo != null) {
			weChatLoginInfoDaoImpl.delete(weChatLoginInfo);

		}

		return CommonCode.OK;
	}

	public ClientInfo findByClientCode(String clientCode) {

		return clientInfoDaoImpl.findByClientCode(clientCode);
	}

	public boolean hasEditPermission(int clientId, User user) {

		List<ClientAirBottleTypeFee> list = clientAirBottleTypeFeeDaoImpl.findByClientId(clientId);

		if (list.size() > 0 && user.getSecondCategory() != null) {
			return false;
		}

		return true;
	}

	public CommonCode saveClientCardCode(int clientId, String cardCode, User user) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		addICCardRecord(clientInfo, cardCode, user);
		clientInfo.setCard_code(cardCode);

		clientInfoDaoImpl.update(clientInfo);

		return CommonCode.OK;
	}

	@Override
	public CommonCode addWechatClientInfo(HttpServletRequest request,AppAddClientInfo appAddClientInfo) {

		Date nowTime = new Date();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClient_name(appAddClientInfo.getClientName());
		clientInfo.setClient_address(appAddClientInfo.getClientAddress());
		clientInfo.setMobile_tel_number_1(appAddClientInfo.getMobileTelNumber());
		clientInfo.setFloorSubsidies(new FloorSubsidies(appAddClientInfo.getFloorSubsidiesId()));
		clientInfo.setSecondCategory(new SecondCategory(appAddClientInfo.getSecondCategoryId()));
		clientInfo.setRemark(appAddClientInfo.getRemark());
		clientInfo.setLongitude(appAddClientInfo.getLongitude());
		clientInfo.setLatitude(appAddClientInfo.getLatitude());

		User operator = userDaoImpl.findById(OperatorCode.NEW_WEIXIN);
		clientInfo.setCreate_people(operator.getFull_name());
		clientInfo.setCreate_time(nowTime);
		clientInfo.setModify_people(operator.getFull_name());
		clientInfo.setModify_time(nowTime);

		clientInfoDaoImpl.insert(clientInfo);

		clientInfo.setClient_code(CodeUtils.setClientCode(clientInfo));
		clientInfo.setCard_code(CodeUtils.setClientCode(clientInfo));
		clientInfo.setPassword("123456");
		clientInfoDaoImpl.update(clientInfo);
		
		
		WeChatLoginInfo weChatLoginInfo = new WeChatLoginInfo();
		weChatLoginInfo.setLogin_mobile_tel_number(appAddClientInfo.getMobileTelNumber());
		weChatLoginInfo.setPassword("123456");
		weChatLoginInfo.setWechat_openid(appAddClientInfo.getOpenid());
		weChatLoginInfo.setClientInfo(clientInfo);
		
		weChatLoginInfoDaoImpl.insert(weChatLoginInfo);
		
		
		return CommonCode.OK;
	}

	@Override
	public List<ClientInfo> findByOpenid(String openid) {
		
		return clientInfoDaoImpl.findByOpenid(openid);
	}

	@Override
	public BaseDto<Integer> bindingOpenid(Integer clientId,String phone,String openid) {
		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);
		logger.error("clientInfo="+clientInfo);
		clientInfo.setId(clientId);
		clientInfo.setPassword("1234567");
		clientInfoDaoImpl.update(clientInfo);
		
		WeChatLoginInfo weChatLoginInfo = weChatLoginInfoDaoImpl.findByClientId(clientId);
		if(weChatLoginInfo == null){
			weChatLoginInfo = new WeChatLoginInfo();
			weChatLoginInfo.setClientInfo(clientInfo);
			weChatLoginInfo.setLogin_mobile_tel_number(phone);
			weChatLoginInfo.setPassword("1234567");
			weChatLoginInfo.setWechat_openid(openid);
			weChatLoginInfoDaoImpl.insert(weChatLoginInfo);
		}else{
			weChatLoginInfo.setClientInfo(clientInfo);
			weChatLoginInfo.setLogin_mobile_tel_number(phone);
			weChatLoginInfo.setPassword("1234567");
			weChatLoginInfo.setWechat_openid(openid);
			weChatLoginInfoDaoImpl.update(weChatLoginInfo);
		}
		
		
		
//		return BaseDto.getSuccessResult(weChatLoginInfoDaoImpl.update(weChatLoginInfo));
		return BaseDto.getSuccessResult(1);
	}

	public Map<Integer, Integer> getFamilyCheckClientNumByStore(List<String> list, Field filed, String family_check_time_begin_time,
			String family_check_time_end_time) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(list);
		tmpField.setFields(filed.getFields());

		tmpList.add("last_family_check_time is not NULL");

		if (StringUtils.nonEmpty(family_check_time_begin_time)) {
			tmpList.add("last_family_check_time >= ?");
			tmpField.addDate(family_check_time_begin_time);
		}

		if (StringUtils.nonEmpty(family_check_time_end_time)) {
			tmpList.add("last_family_check_time < ?");
			tmpField.addDate(TimeUtils.getNextDayString(family_check_time_end_time));
		}

		return clientInfoDaoImpl.getClientNumByStore(tmpList, tmpField);
	}

	public Map<Integer, Integer> getTotalClientNumByStore(List<String> list, Field filed) {

		return clientInfoDaoImpl.getClientNumByStore(list, filed);
	}
	
	public void disabled(String[] ids) {
		clientInfoDaoImpl.disabled(ids);
	}

	@Override
	public ClientInfo addMobileClientInfo(HttpServletRequest request, AppAddClientInfo appAddClientInfo) {
		
		Date nowTime = new Date();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClient_name(appAddClientInfo.getClientName());
		clientInfo.setClient_address(appAddClientInfo.getClientAddress());
		clientInfo.setMobile_tel_number_1(appAddClientInfo.getMobileTelNumber());
		clientInfo.setFloorSubsidies(new FloorSubsidies(appAddClientInfo.getFloorSubsidiesId()));
		clientInfo.setSecondCategory(new SecondCategory(appAddClientInfo.getSecondCategoryId()));
		clientInfo.setRemark(appAddClientInfo.getRemark());

		User operator = userDaoImpl.findById(appAddClientInfo.getUserId());
		clientInfo.setCreate_people(operator.getFull_name());
		clientInfo.setCreate_time(nowTime);
		clientInfo.setModify_people(operator.getFull_name());
		clientInfo.setModify_time(nowTime);

		clientInfoDaoImpl.insert(clientInfo);

		clientInfo.setClient_code(CodeUtils.setClientCode(clientInfo));
		clientInfo.setCard_code(CodeUtils.setClientCode(clientInfo));
		clientInfo.setPassword("123456");
		clientInfoDaoImpl.update(clientInfo);
		
		return clientInfo;
	}
}

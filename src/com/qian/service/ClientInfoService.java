package com.qian.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qian.app.entity.AppAddClientInfo;
import com.qian.entity.ClientInfo;
import com.qian.entity.User;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 下午3:42:09
 * @Description
 */
public interface ClientInfoService {

	public List<ClientInfo> getPageList(int page, int rows);

	public List<ClientInfo> getAllList();

	public long getTotalNum();

	public int edit(ClientInfo clientInfo);

	public int add(ClientInfo clientInfo, User user);

	public void delByIds(String ids[]);

	public ClientInfo findById(Integer clientId);

	public ClientInfo getByMobile(List<String> list, Field filed, int page);

	public ClientInfo getClientNameByclientCode(String client_code);

	public ClientInfo getClientInfoByClientCode(String client_code);

	public long getTotalNum(List<String> list, Field filed);

	public List<ClientInfo> getPageList(List<String> list, Field filed, int page, int rows);

	public List<ClientInfo> getPageList(List<String> list, Field filed);

	public CommonCode editByOrderView(int client_id, ClientInfo clientInfo, User user);

	public ClientInfo getClientInfoByCardCode(String cardCode);

	public CommonCode edit(int clientId, ClientInfo clientInfo, User user);

	public ClientInfo getClientInfoByOldClientCode(String oldClientCode);

	public CommonCode resetPasswordBySMS(String mobileTelNumber);

	public CommonCode resetPasswordByClientIdForSMS(String mobileTelNumber, Integer clientId, User user);

	public CommonCode bindingOpenId(Integer clientId, String openId, String logiMobileTelNumber);

	public CommonCode relieveBindingOpenId(Integer clientId);

	public ClientInfo findByClientCode(String clientCode);

	public boolean hasEditPermission(int clientId, User user);

	public CommonCode saveClientCardCode(int clientId, String cardCode, User user);

	public CommonCode addWechatClientInfo(HttpServletRequest request,AppAddClientInfo appAddClientInfo);

	public List<ClientInfo> findByOpenid(String openid);
	
	public BaseDto<Integer> bindingOpenid(Integer clientId,String phone,String openid);
	
	public Map<Integer, Integer> getFamilyCheckClientNumByStore(List<String> list, Field filed, String family_check_time_begin_time, String family_check_time_end_time);

	public Map<Integer, Integer> getTotalClientNumByStore(List<String> list, Field filed);
	
	public void disabled(String ids[]);

	public ClientInfo addMobileClientInfo(HttpServletRequest request, AppAddClientInfo appAddClientInfo);

}

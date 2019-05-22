package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleImportInfoStateCode;
import com.qian.dao.impl.AirBottleBelongDaoImpl;
import com.qian.dao.impl.AirBottleImportInfoDaoImpl;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTypeDaoImpl;
import com.qian.dao.impl.DetectionUnitDaoImpl;
import com.qian.dao.impl.ProductionUnitDaoImpl;
import com.qian.entity.AirBottleBelong;
import com.qian.entity.AirBottleImportInfo;
import com.qian.entity.AirBottleImportInfoState;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleType;
import com.qian.entity.DetectionUnit;
import com.qian.entity.ProductionUnit;
import com.qian.entity.User;
import com.qian.service.AirBottleImportInfoService;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.rksd.utils.StringUtil;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:46:09
 * @Description
 */
@Service
@Transactional
public class AirBottleImportInfoServiceImpl implements AirBottleImportInfoService {

	@Autowired
	private AirBottleImportInfoDaoImpl airBottleImportInfoDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	@Autowired
	private DetectionUnitDaoImpl detectionUnitDaoImpl;

	@Autowired
	private ProductionUnitDaoImpl productionUnitDaoImpl;

	@Autowired
	private AirBottleBelongDaoImpl airBottleBelongDaoImpl;

	@Override
	public CommonCode upLoadExcel(List<Map<String, String>> excvelMaps, User user) {

		Date nowTime = new Date();

		List<AirBottleImportInfo> airBottleImportInfos = new ArrayList<AirBottleImportInfo>();

		for (Map<String, String> map : excvelMaps) {

			AirBottleImportInfo airBottleImportInfo = initAirBottleImportInfo(map, user, nowTime);
			airBottleImportInfos.add(airBottleImportInfo);
		}

		airBottleImportInfoDaoImpl.insert(airBottleImportInfos);

		return CommonCode.OK;
	}

	private AirBottleImportInfo initAirBottleImportInfo(Map<String, String> map, User user, Date nowTime) {

		AirBottleImportInfo airBottleImportInfo = new AirBottleImportInfo();
		airBottleImportInfo.setAir_bottle_blong_code(map.get("所属单位编码"));
		airBottleImportInfo.setAir_bottle_seal_code(map.get("气瓶钢印码"));
		airBottleImportInfo.setUse_cycle(map.get("设计使用年限"));
		airBottleImportInfo.setAir_bottle_type_code(map.get("气瓶类型编码"));
		airBottleImportInfo.setFactory_number(map.get("出厂编号"));
		airBottleImportInfo.setProduction_unit_code(map.get("生产单位编码"));
		airBottleImportInfo.setDetection_unit_code(map.get("检测单位编码"));
		airBottleImportInfo.setCheck_time(map.get("检修时间"));
		airBottleImportInfo.setNext_check_time(map.get("下次检修时间"));
		airBottleImportInfo.setProduce_time(map.get("生产时间"));
		airBottleImportInfo.setVolume(map.get("容积"));
		airBottleImportInfo.setBottle_weight(map.get("气瓶重量"));
		airBottleImportInfo.setRemark(map.get("备注信息"));
		airBottleImportInfo.setOperator(user);
		airBottleImportInfo.setCreate_time(nowTime);
		airBottleImportInfo.setState(new AirBottleImportInfoState(AirBottleImportInfoStateCode.PENDING));

		return airBottleImportInfo;
	}

	@Override
	public Long getTotalNum(List<String> l, Field filed) {

		return airBottleImportInfoDaoImpl.getTotalNum(l, filed);
	}

	@Override
	public List<AirBottleImportInfo> getPageList(List<String> l, Field filed, int page, int rows) {

		return airBottleImportInfoDaoImpl.getPageList(l, filed, page, rows);
	}

	@Override
	public CommonCode delByIds(String ids) {

		String[] idList = ids.split(",");

		airBottleImportInfoDaoImpl.delByIds(idList);

		return CommonCode.OK;
	}

	@Override
	public CommonCode add(AirBottleImportInfo airBottleImportInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		airBottleImportInfo.setCreate_time(new Date());
		airBottleImportInfo.setOperator(user);
		airBottleImportInfo.setState(new AirBottleImportInfoState(AirBottleImportInfoStateCode.PENDING));
		airBottleImportInfoDaoImpl.insert(airBottleImportInfo);

		return CommonCode.OK;
	}

	@Override
	public CommonCode edit(AirBottleImportInfo airBottleImportInfo, int entityId) {

		AirBottleImportInfo newAirBottleImportInfo = airBottleImportInfoDaoImpl.findById(entityId);

		newAirBottleImportInfo.setAir_bottle_blong_code(airBottleImportInfo.getAir_bottle_blong_code());
		newAirBottleImportInfo.setAir_bottle_seal_code(airBottleImportInfo.getAir_bottle_seal_code());
		newAirBottleImportInfo.setAir_bottle_type_code(airBottleImportInfo.getAir_bottle_type_code());
		newAirBottleImportInfo.setFactory_number(airBottleImportInfo.getFactory_number());
		newAirBottleImportInfo.setProduction_unit_code(airBottleImportInfo.getProduction_unit_code());
		newAirBottleImportInfo.setDetection_unit_code(airBottleImportInfo.getDetection_unit_code());
		newAirBottleImportInfo.setRemark(airBottleImportInfo.getRemark());
		newAirBottleImportInfo.setProduce_time(airBottleImportInfo.getProduce_time());
		newAirBottleImportInfo.setCheck_time(airBottleImportInfo.getCheck_time());
		newAirBottleImportInfo.setNext_check_time(airBottleImportInfo.getNext_check_time());
		newAirBottleImportInfo.setUse_cycle(airBottleImportInfo.getUse_cycle());
		newAirBottleImportInfo.setVolume(airBottleImportInfo.getVolume());
		newAirBottleImportInfo.setBottle_weight(airBottleImportInfo.getBottle_weight());

		return CommonCode.OK;
	}

	@Override
	public CommonCode transform(List<String> l, Field filed) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		Date nowTime = new Date();

		List<AirBottleImportInfo> airBottleImportInfos = airBottleImportInfoDaoImpl.getAllList(l, filed);

		Map<String, AirBottleType> airBottleTypeCodeMap = getAirBottleTypeCodeMap();

		Map<String, DetectionUnit> detectionUnitCodeMap = getDetectionUnitCodeMap();

		Map<String, ProductionUnit> productionUnitCodeMap = getProductionUnitCodeMap();

		Map<String, AirBottleBelong> airBottleBelongCodeMap = getAirBottleBelongCodeMap();

		for (AirBottleImportInfo airBottleImportInfo : airBottleImportInfos) {

			if (airBottleImportInfo.getState().getId() == AirBottleImportInfoStateCode.IMPORT_SUCCESS) {
				continue;
			}

			initTransform(airBottleImportInfo, airBottleTypeCodeMap, detectionUnitCodeMap, productionUnitCodeMap, airBottleBelongCodeMap, user, nowTime);

		}

		return CommonCode.OK;
	}

	private Map<String, AirBottleType> getAirBottleTypeCodeMap() {

		Map<String, AirBottleType> airBottleTypeCodeMap = new TreeMap<String, AirBottleType>();

		List<AirBottleType> airBottleTypes = airBottleTypeDaoImpl.getAllList();

		for (AirBottleType airBottleType : airBottleTypes) {
			if (StringUtils.nonEmpty(airBottleType.getAir_bottle_type_code())) {
				airBottleTypeCodeMap.put(airBottleType.getAir_bottle_type_code(), airBottleType);
			}
		}

		return airBottleTypeCodeMap;
	}

	private Map<String, DetectionUnit> getDetectionUnitCodeMap() {

		Map<String, DetectionUnit> detectionUnitCodeMap = new TreeMap<String, DetectionUnit>();

		List<DetectionUnit> detectionUnits = detectionUnitDaoImpl.getAllList();

		for (DetectionUnit detectionUnit : detectionUnits) {
			if (StringUtils.nonEmpty(detectionUnit.getDetection_unit_code())) {
				detectionUnitCodeMap.put(detectionUnit.getDetection_unit_code(), detectionUnit);
			}
		}

		return detectionUnitCodeMap;
	}

	private Map<String, ProductionUnit> getProductionUnitCodeMap() {

		Map<String, ProductionUnit> productionUnitCodeMap = new TreeMap<String, ProductionUnit>();

		List<ProductionUnit> productionUnits = productionUnitDaoImpl.getAllList();

		for (ProductionUnit productionUnit : productionUnits) {
			if (StringUtils.nonEmpty(productionUnit.getProduction_unit_code())) {
				productionUnitCodeMap.put(productionUnit.getProduction_unit_code(), productionUnit);
			}
		}

		return productionUnitCodeMap;
	}

	private Map<String, AirBottleBelong> getAirBottleBelongCodeMap() {

		Map<String, AirBottleBelong> airBottleBelongCodeMap = new TreeMap<String, AirBottleBelong>();

		List<AirBottleBelong> airBottleBelongs = airBottleBelongDaoImpl.getAllList();

		for (AirBottleBelong airBottleBelong : airBottleBelongs) {
			if (StringUtils.nonEmpty(airBottleBelong.getAir_bottle_blong_code())) {
				airBottleBelongCodeMap.put(airBottleBelong.getAir_bottle_blong_code(), airBottleBelong);
			}
		}

		return airBottleBelongCodeMap;
	}

	private void initTransform(AirBottleImportInfo airBottleImportInfo, Map<String, AirBottleType> airBottleTypeCodeMap,
			Map<String, DetectionUnit> detectionUnitCodeMap, Map<String, ProductionUnit> productionUnitCodeMap,
			Map<String, AirBottleBelong> airBottleBelongCodeMap, User user, Date nowTime) {

		String msg = null;

		// 检查钢码重复
		if (airBottleInfoDaoImpl.checkExistByAirBottleSealCode(airBottleImportInfo.getAir_bottle_seal_code())) {
			msg = "气瓶钢码重复";
		}

		if (StringUtils.isEmpty(airBottleImportInfo.getAir_bottle_type_code())) {
			msg = "物料类型编码不能为空";
		} else {
			// 检查物料类型
			if (!airBottleTypeCodeMap.containsKey(airBottleImportInfo.getAir_bottle_type_code())) {
				msg = "物料类型编码不存在";
			}
		}

		if (StringUtils.isEmpty(airBottleImportInfo.getAir_bottle_blong_code())) {
			msg = "所属单位编码不能为空";
		} else {
			// 检查所属单位
			if (!airBottleBelongCodeMap.containsKey(airBottleImportInfo.getAir_bottle_blong_code())) {
				msg = "所属单位编码不存在";
			}
		}

		if (StringUtils.isEmpty(airBottleImportInfo.getProduction_unit_code())) {
			msg = "生产单位编码不能为空";
		} else {
			// 检查生产单位
			if (!productionUnitCodeMap.containsKey(airBottleImportInfo.getProduction_unit_code())) {
				msg = "生产单位编码不存在";
			}
		}

		// 检查检测单位
		if (!StringUtil.isNull(airBottleImportInfo.getDetection_unit_code())) {
			if (!detectionUnitCodeMap.containsKey(airBottleImportInfo.getDetection_unit_code())) {
				msg = "检测单位编码不存在";
			}
		}

		if (StringUtil.isNull(airBottleImportInfo.getUse_cycle())) {
			msg = "设计使用年限不能为空";
		}

		// 检查时间
		if (StringUtil.isNull(airBottleImportInfo.getProduce_time())) {
			msg = "生产时间不能为空";
		} else {
			if (!TimeUtils.isValidDate(airBottleImportInfo.getProduce_time())) {
				msg = "生产时间格式不合法";
			}
		}

		// 检测时间允许为空
		if (StringUtil.notNull(airBottleImportInfo.getCheck_time())) {
			if (!TimeUtils.isValidDate(airBottleImportInfo.getCheck_time())) {
				msg = "检测时间格式不合法";
			}
		}

		if (StringUtil.isNull(airBottleImportInfo.getNext_check_time())) {
			msg = "下次检测时间不能为空";
		} else {
			if (!TimeUtils.isValidDate(airBottleImportInfo.getNext_check_time())) {
				msg = "下次检测时间格式不合法";
			}
		}

		if (StringUtil.isNull(airBottleImportInfo.getVolume())) {
			msg = "容积不能为空";
		}

		if (StringUtil.isNull(airBottleImportInfo.getBottle_weight())) {
			msg = "气瓶重量不能为空";
		}

		if (StringUtil.notNull(msg)) {
			airBottleImportInfo.setImport_remark(msg);
			airBottleImportInfo.setState(new AirBottleImportInfoState(AirBottleImportInfoStateCode.IMPORT_FAIL));
			airBottleImportInfoDaoImpl.update(airBottleImportInfo);
			return;
		}

		airBottleImportInfo.setImport_remark(null);
		airBottleImportInfo.setState(new AirBottleImportInfoState(AirBottleImportInfoStateCode.IMPORT_SUCCESS));
		airBottleImportInfoDaoImpl.update(airBottleImportInfo);

		AirBottleInfo airBottleInfo = new AirBottleInfo();
		airBottleInfo.setAir_bottle_seal_code(airBottleImportInfo.getAir_bottle_seal_code());
		airBottleInfo.setAirBottleType(airBottleTypeCodeMap.get(airBottleImportInfo.getAir_bottle_type_code()));
		airBottleInfo.setFactory_number(airBottleImportInfo.getFactory_number());
		airBottleInfo.setAirBottleBelong(airBottleBelongCodeMap.get(airBottleImportInfo.getAir_bottle_blong_code()));
		airBottleInfo.setProductionUnit(productionUnitCodeMap.get(airBottleImportInfo.getProduction_unit_code()));
		if (!StringUtil.isNull(airBottleImportInfo.getDetection_unit_code())) {
			airBottleInfo.setDetectionUnit(detectionUnitCodeMap.get(airBottleImportInfo.getDetection_unit_code()));
		}
		airBottleInfo.setRemark(airBottleImportInfo.getRemark());
		airBottleInfo.setProduce_time(TimeUtils.getDateByyyyyMMdd(airBottleImportInfo.getProduce_time()));
		if (StringUtil.notNull(airBottleImportInfo.getCheck_time())) {
			airBottleInfo.setCheck_time(TimeUtils.getDateByyyyyMMdd(airBottleImportInfo.getCheck_time()));
		}
		airBottleInfo.setNext_check_time(TimeUtils.getDateByyyyyMMdd(airBottleImportInfo.getNext_check_time()));
		airBottleInfo.setUse_cycle(Integer.parseInt(airBottleImportInfo.getUse_cycle()));
		airBottleInfo.setVolume(airBottleImportInfo.getVolume());
		airBottleInfo.setBottle_weight(Double.parseDouble(airBottleImportInfo.getBottle_weight()));
		airBottleInfo.setCreate_time(nowTime);
		airBottleInfo.setCreate_people(user.getFull_name());
		airBottleInfoDaoImpl.insert(airBottleInfo);

	}

	@Override
	public CommonCode batchDel(List<String> l, Field filed) {

		List<AirBottleImportInfo> airBottleImportInfos = airBottleImportInfoDaoImpl.getAllList(l, filed);

		for (AirBottleImportInfo airBottleImportInfo : airBottleImportInfos) {

			if (airBottleImportInfo.getState().getId() == AirBottleImportInfoStateCode.IMPORT_SUCCESS) {
				continue;
			}

			airBottleImportInfoDaoImpl.delete(airBottleImportInfo);

		}

		return CommonCode.OK;
	}

	@Override
	public int getPirntTimes(Integer orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AirBottleImportInfo findOrderInfoById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}

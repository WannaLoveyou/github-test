package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AirBottleTypeDaoImpl;
import com.qian.entity.AirBottleType;
import com.qian.service.AirBottleTypeService;

@Service
@Transactional
public class AirBottleTypeServiceImpl implements AirBottleTypeService {

	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	public List<AirBottleType> getPageList(int page, int rows) {

		return airBottleTypeDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {

		return airBottleTypeDaoImpl.getTotalNum();
	}

	public int add(AirBottleType airBottleType) {

		return airBottleTypeDaoImpl.insert(airBottleType);
	}

	public int edit(AirBottleType airBottleTypeTemp, int airBottleTypeId) {

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(airBottleTypeId);

		airBottleType.setAir_bottle_specifications(airBottleTypeTemp.getAir_bottle_specifications());
		airBottleType.setAir_bottle_type_code(airBottleTypeTemp.getAir_bottle_type_code());
		airBottleType.setMeasurement_unit(airBottleTypeTemp.getMeasurement_unit());
		airBottleType.setPrice(airBottleTypeTemp.getPrice());
		airBottleType.setWeixin_discounty_fee(airBottleTypeTemp.getWeixin_discounty_fee());
		airBottleType.setDelivery_fee(airBottleTypeTemp.getDelivery_fee());
		airBottleType.setInspection_fee(airBottleTypeTemp.getInspection_fee());
		airBottleType.setSupply_delivery_fee(airBottleTypeTemp.getSupply_delivery_fee());
		airBottleType.setWeixin_buy(airBottleTypeTemp.getWeixin_buy());
		airBottleType.setAir_bottle_product_name(airBottleTypeTemp.getAir_bottle_product_name());
		airBottleType.setHas_gas_weight(airBottleTypeTemp.getHas_gas_weight());
		airBottleType.setAir_bottle_length_width(airBottleTypeTemp.getAir_bottle_length_width());
		airBottleType.setVolume(airBottleTypeTemp.getVolume());
		airBottleType.setBottle_weight(airBottleTypeTemp.getBottle_weight());
		airBottleType.setTotal_weight(airBottleTypeTemp.getTotal_weight());
		airBottleType.setAirBottleVariety(airBottleTypeTemp.getAirBottleVariety());
		airBottleType.setBottle_model(airBottleTypeTemp.getBottle_model());
		airBottleType.setNominal_working_pressure(airBottleTypeTemp.getNominal_working_pressure());
		airBottleType.setWater_test_pressure(airBottleTypeTemp.getWater_test_pressure());
		airBottleType.setWall_thickness(airBottleTypeTemp.getWall_thickness());
		airBottleType.setFillingMedium(airBottleTypeTemp.getFillingMedium());
		airBottleType.setDefault_detection_cycle(airBottleTypeTemp.getDefault_detection_cycle());
		airBottleType.setDefault_use_cycle(airBottleTypeTemp.getDefault_use_cycle());
		
		return airBottleTypeDaoImpl.update(airBottleType);
	}
	
	@Override
	public int editSpecial(AirBottleType airBottleTypeTemp, int airBottleTypeId) {

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(airBottleTypeId);

		airBottleType.setInspectionSystemCode(airBottleTypeTemp.getInspectionSystemCode());
		airBottleType.setPublicServiceMaterialType(airBottleTypeTemp.getPublicServiceMaterialType());
		
		return airBottleTypeDaoImpl.update(airBottleType);
	}

	public void delByIds(String[] ids) {

		airBottleTypeDaoImpl.delByIds(ids);

	}

	public List<AirBottleType> getAllList() {
		return airBottleTypeDaoImpl.getAllList();
	}

	public AirBottleType getByAirBottleTypeName(String airBottleTypeName) {

		return airBottleTypeDaoImpl.getByAirBottleTypeName(airBottleTypeName);
	}

	public AirBottleType findById(int airBottleTypeId) {

		return airBottleTypeDaoImpl.findById(airBottleTypeId);
	}

	public List<AirBottleType> getAllWeiXinList() {

		return airBottleTypeDaoImpl.getAllWeiXinList();
	}

	public List<AirBottleType> getAllListForSpecialPrice(Integer secondCategoryId) {

		List<AirBottleType> airBottleTypes = airBottleTypeDaoImpl.getAllList();

		return airBottleTypes;
	}

}

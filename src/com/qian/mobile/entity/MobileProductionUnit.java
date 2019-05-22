package com.qian.mobile.entity;

import com.qian.entity.ProductionUnit;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午3:50:41
 * @Description
 */
public class MobileProductionUnit {

	private int id;

	private String production_unit_name;

	public MobileProductionUnit(ProductionUnit productionUnit) {

		this.id = productionUnit.getId();
		this.production_unit_name = productionUnit.getProduction_unit_name();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduction_unit_name() {
		return production_unit_name;
	}

	public void setProduction_unit_name(String production_unit_name) {
		this.production_unit_name = production_unit_name;
	}

}

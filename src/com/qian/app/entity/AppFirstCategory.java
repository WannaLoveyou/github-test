package com.qian.app.entity;

import com.qian.entity.FirstCategory;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-15 下午4:21:04
 * @Description
 */
public class AppFirstCategory {

	private int firstCategoryId; // 第一区域ID

	private String firstCategoryName; // 第一区域名称

	public AppFirstCategory(FirstCategory firstCategory) {
		this.firstCategoryId = firstCategory.getId();
		this.firstCategoryName = firstCategory.getFirst_category_name();
	}

	public int getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(int firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

}

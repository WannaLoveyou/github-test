package com.qian.app.entity;

import com.qian.entity.SecondCategory;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-15 下午4:28:47
 * @Description
 */
public class AppSecondCategory {

	private int firstCategoryId; // 第一区域ID

	private int secondCategoryId; // 第二区域ID

	private String secondCategoryName; // 第一区域名称

	public AppSecondCategory(SecondCategory secondCategory) {
		this.firstCategoryId = secondCategory.getId();
		this.secondCategoryId = secondCategory.getId();
		this.secondCategoryName = secondCategory.getSecond_category_name();
	}

	public int getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(int firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public int getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(int secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

}

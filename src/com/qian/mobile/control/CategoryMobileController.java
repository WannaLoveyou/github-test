package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppFirstCategory;
import com.qian.app.entity.AppSecondCategory;
import com.qian.entity.FirstCategory;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.FirstCategoryService;
import com.qian.service.SecondCategoryService;
import com.qian.service.UserService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2019-1-10 下午4:24:48
 * @Description
 */
@Controller
@RequestMapping("/mobile/category")
public class CategoryMobileController {

	@Autowired
	private FirstCategoryService firstService;

	@Autowired
	private SecondCategoryService secondCategoryService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getFirstCategoryList")
	public @ResponseBody
	BaseDto<List<AppFirstCategory>> getFirstCategoryList(HttpServletRequest request, Map<String, Object> map) {

		List<AppFirstCategory> appFirstCategorys = new ArrayList<AppFirstCategory>();

		List<FirstCategory> firstCategorys = firstService.getAllList();

		for (FirstCategory firstCategory : firstCategorys) {
			AppFirstCategory appFirstCategory = new AppFirstCategory(firstCategory);
			appFirstCategorys.add(appFirstCategory);
		}

		return BaseDto.getSuccessResult(appFirstCategorys);
	}

	@RequestMapping(value = "getSecondCategoryList")
	public @ResponseBody
	BaseDto<List<AppSecondCategory>> getSecondCategoryList(Map<String, Object> map, int userId) {

		List<AppSecondCategory> appSecondCategorys = new ArrayList<AppSecondCategory>();

		User user = userService.findById(userId);
		if (user.getSecondCategory() != null) {
			AppSecondCategory appSecondCategory = new AppSecondCategory(user.getSecondCategory());
			appSecondCategorys.add(appSecondCategory);
			return BaseDto.getSuccessResult(appSecondCategorys);
		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		for (SecondCategory secondCategory : secondCategorys) {
			AppSecondCategory appSecondCategory = new AppSecondCategory(secondCategory);
			appSecondCategorys.add(appSecondCategory);
		}

		return BaseDto.getSuccessResult(appSecondCategorys);
	}
}

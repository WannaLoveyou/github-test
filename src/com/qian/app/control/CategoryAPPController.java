package com.qian.app.control;

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
import com.qian.service.FirstCategoryService;
import com.qian.service.SecondCategoryService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-15 下午4:17:31
 * @Description
 */
@Controller
@RequestMapping("/app/category")
public class CategoryAPPController {

	@Autowired
	private FirstCategoryService firstService;
	
	@Autowired
	private SecondCategoryService secondCategoryService;
	
	@RequestMapping(value = "getFirstCategoryAllList")
	public @ResponseBody
	BaseDto<List<AppFirstCategory>> getNewAllList(HttpServletRequest request, Map<String, Object> map) {
		
		List<AppFirstCategory> appFirstCategorys = new ArrayList<AppFirstCategory>();
		
		List<FirstCategory> firstCategorys = firstService.getAllList();
		
		for(FirstCategory firstCategory:firstCategorys){
			AppFirstCategory appFirstCategory = new AppFirstCategory(firstCategory);
			appFirstCategorys.add(appFirstCategory);
		}
		
		return BaseDto.getSuccessResult(appFirstCategorys);
	}
	
	@RequestMapping(value = "getSecondCategoryListByFirstCategoryId")
	public @ResponseBody
	BaseDto<List<AppSecondCategory>> getSecondCategoryListByFirstCategoryId(Map<String, Object> map, int firstCategoryId) {
		
		List<AppSecondCategory> appSecondCategorys = new ArrayList<AppSecondCategory>();

		List<SecondCategory> secondCategorys = secondCategoryService.getListByFirstId(firstCategoryId);
		
		for(SecondCategory secondCategory:secondCategorys){
			AppSecondCategory appSecondCategory = new AppSecondCategory(secondCategory);
			appSecondCategorys.add(appSecondCategory);
		}
		
		return BaseDto.getSuccessResult(appSecondCategorys);
	}
}

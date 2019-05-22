package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.User;
import com.qian.mobile.entity.MobleDeliveryMan;
import com.qian.service.UserService;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 下午7:11:40
 * @Description
 */
@Controller
@RequestMapping("/mobile/user")
public class UserMobileController {

	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "findUserByCardCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobleDeliveryMan> findUserByCardCode(HttpServletRequest request, Map<String, Object> map, String cardCode) {

		if (!StringUtils.nonEmpty(cardCode)) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		User user = userService.findByCardCode(cardCode);

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		MobleDeliveryMan mobleDeliveryMan = new MobleDeliveryMan(user);

		return BaseDto.getSuccessResult(mobleDeliveryMan);
	}
	
	@RequestMapping(value = "findDeliveryManBySecondCategoryId")
	public @ResponseBody
	BaseDto<List<MobleDeliveryMan>> findDeliveryManBySecondCategoryId(HttpServletRequest request, Map<String, Object> map, Integer secondCategoryId) {

		List<User> deliveryMans = userService.getIsDeliveryManList(secondCategoryId);

		List<MobleDeliveryMan> mobleDeliveryMans = new ArrayList<>();

		for(User user : deliveryMans){
			MobleDeliveryMan mobleDeliveryMan = new MobleDeliveryMan(user);
			mobleDeliveryMans.add(mobleDeliveryMan);
		}
		
		return BaseDto.getSuccessResult(mobleDeliveryMans);
	}
}

package com.qian.app.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAirBottleType;
import com.qian.entity.AirBottleType;
import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.service.AirBottleTypeService;
import com.qian.service.ClientAirBottleTypeFeeService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-20 下午5:24:08
 * @Description
 */
@Controller
@RequestMapping("/app/airBottleType")
public class AirBottleTypeAppController {

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private ClientAirBottleTypeFeeService clientAirBottleTypeFeeService;

	@RequestMapping(value = "getAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppAirBottleType>> getAllList(HttpServletRequest request, Map<String, Object> map) {

		List<AirBottleType> list = airBottleTypeService.getAllWeiXinList();

		List<AppAirBottleType> result = new ArrayList<AppAirBottleType>();

		for (AirBottleType airBottleType : list) {

			AppAirBottleType appAirBottleType = new AppAirBottleType(airBottleType);

			result.add(appAirBottleType);
		}

		return BaseDto.getSuccessResult(result);

	}

	@RequestMapping(value = "getAirBottleTypeById", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppAirBottleType> getAirBottleTypeById(HttpServletRequest request, Map<String, Object> map, int airBottleTypeId, Integer clientId) {

		AirBottleType airBottleType = airBottleTypeService.findById(airBottleTypeId);

		if (clientId != null) {

			ClientAirBottleTypeFee clientAirBottleTypeFee = clientAirBottleTypeFeeService.getDeliveryandCheckFee(airBottleTypeId, clientId);

			if (clientAirBottleTypeFee != null) {
				if (clientAirBottleTypeFee.getDelivery_fee() != null) {
					airBottleType.setDelivery_fee(clientAirBottleTypeFee.getDelivery_fee());
				}
			}
		}

		return BaseDto.getSuccessResult(new AppAirBottleType(airBottleType));

	}
}

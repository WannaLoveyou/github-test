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

import com.qian.app.entity.AppPayType;
import com.qian.entity.PayType;
import com.qian.service.PayTypeService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-22 下午4:37:29
 * @Description
 */
@Controller
@RequestMapping("/app/payType")
public class PayTypeAppController {

	@Autowired
	private PayTypeService payTypeService;

	@RequestMapping(value = "getAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppPayType>> getAllList(HttpServletRequest request, Map<String, Object> map) {

		List<PayType> list = payTypeService.getAllList();

		List<AppPayType> result = new ArrayList<AppPayType>();

		for (PayType payType : list) {

			AppPayType appPayType = new AppPayType(payType);

			result.add(appPayType);
		}

		return BaseDto.getSuccessResult(result);

	}
}

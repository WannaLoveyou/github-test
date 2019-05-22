package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.PayState;
import com.qian.service.PayStateService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-15 上午11:11:19
 * @Description
 */

@Controller
@RequestMapping("/payState")
public class PayStateController {

	@Autowired
	private PayStateService payStateService;

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<PayState> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<PayState> list = payStateService.getAllList();
		return list;
	}
}

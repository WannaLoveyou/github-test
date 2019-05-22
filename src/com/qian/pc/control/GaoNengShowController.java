package com.qian.pc.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-29 上午11:03:18
 * @Description
 */
@Controller
@RequestMapping("/gaoNengShow")
public class GaoNengShowController {

	private static final String BEFORE_CHECK_LIST = "gaoNengShow/before_check_list";
	
	private static final String GAS_SALE_LIST = "gaoNengShow/gas_sale_list";
	
	private static final String LUQID_SALE_LIST = "gaoNengShow/luqid_sale_list";
	
	@RequiresUser
	@RequestMapping(value = "beforeCheckList", method = RequestMethod.GET)
	public String beforeCheckList(HttpServletRequest request, Map<String, Object> map) {
		return BEFORE_CHECK_LIST;
	}
	
	@RequiresUser
	@RequestMapping(value = "gasSaleList", method = RequestMethod.GET)
	public String gasSaleList(HttpServletRequest request, Map<String, Object> map) {
		return GAS_SALE_LIST;
	}
	
	@RequiresUser
	@RequestMapping(value = "luqidSaleList", method = RequestMethod.GET)
	public String luqidSaleList(HttpServletRequest request, Map<String, Object> map) {
		return LUQID_SALE_LIST;
	}
}

package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.InspectionSystemCode;
import com.qian.service.InspectionSystemCodeService;

/**
 * @author Lu Kongwen
 * @version Create time：2018-6-7 下午3:59:20
 * @Description
 */
@Controller
@RequestMapping("/inspectionSystemCode")
public class InspectionSystemCodeController {

	@Autowired
	private InspectionSystemCodeService inspectionSystemCodeService;

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<InspectionSystemCode> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<InspectionSystemCode> list = inspectionSystemCodeService.getAllList();
		return list;
	}
}

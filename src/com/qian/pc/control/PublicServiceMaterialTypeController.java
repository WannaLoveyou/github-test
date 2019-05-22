package com.qian.pc.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.PublicServiceMaterialType;
import com.qian.service.PublicServiceMaterialTypeService;

@Controller
@RequestMapping("/publicServiceMaterialType")
public class PublicServiceMaterialTypeController {

	@Autowired
	private PublicServiceMaterialTypeService publicServiceMaterialTypeService;

	@RequestMapping(value = "getAllList")
	public @ResponseBody List<PublicServiceMaterialType> getAllList() {
		return publicServiceMaterialTypeService.getAllList();
	}

}
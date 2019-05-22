package com.qian.pc.control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qian.entity.AirBottleInfo;
import com.qian.entity.User;
import com.qian.service.AirBottleInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/airBottleInfo")
public class AirBottleInfoController {

	private static final String LIST = "airBottleInfo/list";

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, Map<String, Object> map, AirBottleInfo airBottleInfo) throws IOException {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		Iterator<String> iter = multiRequest.getFileNames();

		while (iter.hasNext()) {

			String fileName = iter.next();

			MultipartFile multipartFile = multiRequest.getFile(fileName);

			File file = new File("D:/resources/");

			if (!file.exists()) {
				file.mkdir();
			}

			long timeFile = new Date().getTime();

			File newFile = new File("D:/resources/" + timeFile + fileName);

			FileUtils.writeByteArrayToFile(newFile, multipartFile.getBytes());

			if (fileName.equals("uploadfile1")) {
				airBottleInfo.setImg1("D:/resources/" + timeFile + fileName);
			}

			if (fileName.equals("uploadfile2")) {
				airBottleInfo.setImg2("D:/resources/" + timeFile + fileName);
			}

		}

		airBottleInfoService.add(airBottleInfo, user);

		return list(request, map);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<CommonCode> del(HttpServletRequest request, Map<String, Object> map, String ids) {

		String idString[] = ids.split(",");

		CommonCode commonCode = airBottleInfoService.delByIds(idString);

		return BaseDto.getUnKnowResult(null, commonCode);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, AirBottleInfo airBottleInfo, int airBottleId) throws IOException {
	
		airBottleInfoService.edit(airBottleInfo, airBottleId);
		
		return "ok";
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		ToolsBarUtils.getAirBottleSearchCondition(request, l, filed);
		Long total = airBottleInfoService.getTotalNum(l, filed);
		List<AirBottleInfo> list = airBottleInfoService.getPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "viewAirBottlePic", method = { RequestMethod.GET, RequestMethod.POST })
	public void viewStoreInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, String imgPath) {

		BufferedInputStream br = null;

		try {

			String listimgPath = imgPath;
			br = new BufferedInputStream(new FileInputStream(listimgPath));
			byte[] buf = new byte[1024];
			int len = 0;
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "cleanQRCodeInfo")
	public @ResponseBody
	BaseDto<CommonCode> cleanQRCodeInfo(HttpServletRequest request, Map<String, Object> map) throws IOException {
	
		return airBottleInfoService.cleanQRCodeInfo();
		
	}
}

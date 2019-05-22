package com.qian.mobile.control;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.MobileLog;
import com.qian.service.MobileLogService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-23 上午9:11:29
 * @Description
 */
@Controller
@RequestMapping("/mobile/log")
public class LogMobileController {

	@Autowired
	private MobileLogService mobileLogService;
	
	@RequestMapping(value = "log", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> log(HttpServletRequest request, Map<String, Object> map, String log,String deviceCode) throws UnsupportedEncodingException {

		
		MobileLog mobileLog = new MobileLog();
		
		mobileLog.setLog(log);
		mobileLog.setDevice_code(deviceCode);
		mobileLog.setCreate_time(new Date());
		
		mobileLogService.add(mobileLog);

		return BaseDto.getSuccessResult(null);
	}
}

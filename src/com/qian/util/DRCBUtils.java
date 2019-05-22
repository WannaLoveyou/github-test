package com.qian.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qian.listener.InitListener;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-17 上午11:07:52
 * @Description
 */
public class DRCBUtils {

	private static Logger log = Logger.getLogger(InitListener.class);

	private static final String BASE_URL = "http://127.0.0.1:8080/DRCB-forward";

	private static final String REFRESH_URL = BASE_URL + "/forward/getBankSaveRecord";

	public static void main(String argsp[]) {

		Date eDate = new Date();

		List<Map<String, String>> result = getBankSaveRecord("000001", null, eDate);

		System.out.println(result);
	}

	public static List<Map<String, String>> getBankSaveRecord(String as_acno, Date lastRefeshDate, Date nowTime) {

		StringBuffer sb = new StringBuffer();
		sb.append("as_acno=").append(as_acno);
		if (lastRefeshDate != null) {
			sb.append("&lastRefeshDate=").append(lastRefeshDate.getTime());
		}
		sb.append("&nowTime=").append(nowTime.getTime());

		String result = HttpUtils.sendPost(REFRESH_URL, sb.toString());

		if (result == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> resultMapList = null;
		try {
			resultMapList = mapper.readValue(result, new TypeReference<List<Map<String, String>>>() {
			});
		} catch (Exception e) {
			log.error(e);
		}

		return resultMapList;
	}

}

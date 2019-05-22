package com.qian.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午2:49:03
 * @Description
 */
public class HttpRestUtil {

	private static Logger log = Logger.getLogger(HttpRestUtil.class);

	/**
	 * 发送post请求
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求json数据
	 * @return
	 */
	public static String postJson(String url, JSONObject param) {
		ObjectMapper mapper = new ObjectMapper();
		String retSrc;
		try {
			URL restURL = new URL(url);
			/*
			 * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类
			 * 的子类HttpURLConnection
			 */
			HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
			// 请求方式
			conn.setRequestMethod("POST");
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			// httpUrlConnection.setDoInput(true);
			conn.setDoOutput(true);
			// allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL
			// 进行检查。
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");

			
			
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(mapper.writeValueAsBytes(param));
//			outputStream.write(param.toString().getBytes());
			outputStream.flush();
			
			BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

			String line, resultStr = "";

			while (null != (line = bReader.readLine())) {
				resultStr += line;
			}
			bReader.close();
			retSrc = resultStr;
//			retSrc = "";
		} catch (Exception e) {
			retSrc = null;
			e.printStackTrace();
			log.debug(e);
		}
		//System.out.println("请求返回数据："+retSrc);
		return retSrc;
	}

}

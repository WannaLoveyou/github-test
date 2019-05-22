package com.qian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-22 下午4:51:01
 * @Description
 */
public class Test {

	public static JSONObject sendGetByJson(String url, JSONObject json) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				response = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		
		StringBuffer sb = new StringBuffer();
		sb.append(new Date().getTime()).append(MyRandomUtils.get6bitNumber());
		System.out.println(sb);
				
		//sendPlInCost();
		//sendGetPlInInfo();
		//sendPlIn();
		//sendPlOutCost();
		//sendPlOut();
		
		
		//test();
	}
	
	public static void test(){
		
		String str = "0010000001";
		
		byte[] bs = str.getBytes();
		
		for(byte b : bs){
			System.out.println(b);
		}
		
	}

	public static void sendJson() {
		JSONObject rowJSON = new JSONObject();
		rowJSON.put("userId", "a4923ecd9e024db29bc9e86f4f921bf2");
		rowJSON.put("deviceCode", "3F:40:04:10:A8:4F");
		rowJSON.put("date", "2017-07-19");

		String url = "http://127.0.0.1:8080/smart-app/sport/getSportH?";

		System.out.println(sendGetByJson(url, rowJSON));
	}

	public static void sendPost() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://192.168.1.101:8080/energymanage/airBottleInfoApp/getAirBottleInfoByAirBottleCode?";
		StringBuffer sb = new StringBuffer();
		sb.append("airBottleCode=").append("LquFHtFrlcQZjb7OeIT/xw==");

		String sr = sendPost(url, sb.toString());

		System.out.println(sr);

	}
	

	public static void sendGetPlInInfo() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://localhost:8080/parking/jieshu/in/getvehicleinfo?";
		StringBuffer sb = new StringBuffer();
		sb.append("formMap[car_number]=").append("K98");
		sb.append("&formMap[pl_code]=").append("PL002");
		sb.append("&formMap[ic_code]=").append("UG001");

		String sr = sendPost(url, sb.toString());

		System.out.println(sr);
	}
	
	public static void sendPlInCost() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://localhost:8080/parking/jieshu/in/getcost?";
		StringBuffer sb = new StringBuffer();
		sb.append("&formMap[car_number]=").append("说K1234");
		sb.append("&formMap[vt_code]=").append("VT002");
		sb.append("&formMap[pl_code]=").append("PL002");
		sb.append("&formMap[ic_code]=").append("E001");

		String sr = sendPost(url, sb.toString());

		System.out.println(sr);
	}
	
	public static void sendPlIn() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://localhost:8080/parking/jieshu/in/addentrancefee?";
		StringBuffer sb = new StringBuffer();
		sb.append("&formMap[ir_handling_cost]=").append("0");
		sb.append("&formMap[user_type]=").append("D");
		sb.append("&formMap[ir_entry_cost]=").append("10");
		sb.append("&formMap[ic_code]=").append("E001");
		sb.append("&formMap[vt_code]=").append("VT002");
		sb.append("&formMap[ir_in_time]=").append("2017/8/3 10:42:29");
		sb.append("&formMap[ir_in_operator]=").append("肖淦辉");
		sb.append("&formMap[pl_code]=").append("PL001");
		sb.append("&formMap[ir_temporary_card]=").append("9C6AABC5");
		sb.append("&formMap[ir_in_weight]=").append("0");  
		sb.append("&formMap[ir_in_code]=").append("1491106");  //进场编号

		String sr = sendPost(url, sb.toString());

		System.out.println(sr);
	}
	
	public static void sendPlOutCost() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://localhost:8080/parking/jieshu/out/getcost?";
		StringBuffer sb = new StringBuffer();
		sb.append("&formMap[ic_code]=").append("E004");
		sb.append("&formMap[ir_num]=").append("");
		sb.append("&formMap[ir_out_time]=").append("2017-08-03 11:02:07");
		sb.append("&formMap[pl_code]=").append("PL001");
		sb.append("&formMap[ir_in_code]=").append("1491106");

		String sr = sendPost(url, sb.toString());

		System.out.println(sr);
	}
	
	public static void sendPlOut() throws UnsupportedEncodingException {
		// 发送 POST 请求

		String url = "http://localhost:8080/parking/jieshu/out/addoutfee?";
		StringBuffer sb = new StringBuffer();
		
		sb.append("&formMap[ir_num]=").append("鲁D72336");
		sb.append("&formMap[user_type]=").append("D");
		sb.append("&formMap[ic_code]=").append("S003");
		sb.append("&formMap[ir_stop_cost]=").append("132");
		sb.append("&formMap[ir_out_time]=").append("2017-08-07 15:41:33");
		sb.append("&formMap[ir_temporary_card]=").append("8B06EDBD");
		sb.append("&formMap[ir_in_code]=").append("1494555");
		sb.append("&formMap[ir_out_weight]=").append("0");
		sb.append("&formMap[ir_out_operator]=").append("陈展邦");

		
		
		String sr = sendPost(url, sb.toString());

		System.out.println(sr);
	}

}

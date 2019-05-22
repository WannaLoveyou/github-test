package com.qian.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-25 下午5:03:16
 * @Description
 */
public class XMLClient {

	private CloseableHttpClient client;

	public static void main(String[] args) throws Exception {
		XMLClient client = new XMLClient();
		// 报文头(默认为静态代码块中的值 需要修改直接覆盖) 这条是测试对外支付
		/*
		 * header.put("tr_code", HandlerNumber.对外支付);// 操作码 // 报文体 Map<String, Object> body = new HashMap<String, Object>(); body.put("pay_acno",
		 * "120010190010017046"); body.put("pay_cur_code", "01"); body.put("pay_acname", "东莞市虎门能源有限责任公司"); body.put("as_flag", "0"); body.put("as_acno", "");
		 * body.put("as_acname", ""); body.put("pay_accaddr", ""); body.put("cert_type", ""); body.put("cert_no", "123456"); body.put("rcv_acno",
		 * "380010000006009"); body.put("rcv_cur_code", "01"); body.put("rcv_accaddr", ""); body.put("rcv_acname", "张三"); body.put("rcv_bank_no",
		 * "380010000006009"); body.put("rcv_bank_name", ""); body.put("amt", "10"); body.put("bank_flag", "0"); body.put("urgency_flag", "0");
		 * body.put("area_flag", "0"); body.put("bank_kind", "0"); body.put("purpose", "不知道"); body.put("postscript", "不知道"); body.put("booking_flag", "");
		 * body.put("booking_date", ""); body.put("booking_time", "");
		 */
		// 发送XML数据到服务
		// 测试查询交易明细

	}

	// 使用POST方法发送XML数据
	public String sendXMLDataByPost(String url, String xmlData) throws Exception {
		if (client == null) {
			client = HttpClients.createDefault();
		}
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("xml", xmlData));
		post.setHeader("charset", "utf-8");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		// 设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
		StringEntity stringEntity = new StringEntity(xmlData, "UTF-8");
		stringEntity.setContentEncoding("utf-8");
		post.setEntity(stringEntity);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		String result = EntityUtils.toString(entity, "utf-8");

		System.out.println(result);

		return result;
	}

}
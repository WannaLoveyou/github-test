package com.qian.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午2:49:03
 * @Description
 */
public class HttpUtils {

	private static Logger log = Logger.getLogger(HttpUtils.class);

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
				log.info(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.info("发送GET请求出现异常！" + e);
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
		DataOutputStream out = null;
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
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new DataOutputStream(conn.getOutputStream());
			// 发送请求参数
			// out.print(param);
			out.write(param.toString().getBytes("UTF-8"));
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.info("发送 POST 请求出现异常！" + e);
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

	public static String httpPost(String url, Map<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "UTF-8");

			httpPost.setEntity(formEntity);
			HttpResponse resp = client.execute(httpPost);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			httpPost.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	public static String httpPostByTimeOut(String url, Map<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpClient client = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "UTF-8");
			
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(5000).build();			
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(formEntity);
			HttpResponse resp = client.execute(httpPost);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			httpPost.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	public static String httpPostByTimeOut(String url, String params) {
		try {
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpClient client = HttpClients.createDefault();
			
			StringEntity strEntity = new StringEntity(params, "UTF-8");
			
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(15000).build();			
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(strEntity);
			HttpResponse resp = client.execute(httpPost);
			
			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			httpPost.abort();
			client.getConnectionManager().shutdown();
			
			return respContent;
			
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}

	public static String refundByWeiXin(String weiXinUrl, String xmlStr) throws Exception {

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(SignUtils.WeiXinCertPath));
		try {
			keyStore.load(instream, SignUtils.WeiXinMchId.toCharArray());
		} finally {
			instream.close();
		}

		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, SignUtils.WeiXinMchId.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		StringBuffer result = new StringBuffer();

		try {

			HttpPost httpPost = new HttpPost(weiXinUrl);

			log.info("executing request" + httpPost.getRequestLine());

			StringEntity reqEntity = new StringEntity(xmlStr, "UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			reqEntity.setContentEncoding("UTF-8");
			httpPost.setEntity(reqEntity);

			CloseableHttpResponse response = httpclient.execute(httpPost);

			try {
				HttpEntity entity = response.getEntity();

				log.info("----------------------------------------");
				log.info(response.getStatusLine());
				if (entity != null) {
					log.info("Response content length: " + entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						log.info(text);
						result.append(text);
					}

				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result.toString();
	}

	public static String getWeiXinPhoto(String path, String access_token, String media_id) {

		access_token = getEncryption(access_token);// 异或解密

		String urlNameString = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + media_id;

		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		String saveFileName = null;
		try {
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			inputStream = connection.getInputStream();

			byte[] data = new byte[1024];
			int len = 0;

			saveFileName = UniqueIDGenerator.getUUID() + ".jpg";

			path = path + saveFileName;

			fileOutputStream = new FileOutputStream(path);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
			fileOutputStream.flush();

		} catch (Exception e) {
			log.info("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return path;

	}

	public static String getEncryption(String para) {
		char[] charArray = para.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (charArray[i] ^ 1);
		}
		return String.valueOf(charArray);
	}

	// post方式请求
	public static String sendXmlByPost(String url, String xmlData) {

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("charset", "utf-8");
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");

		String str = null;

		try {
			StringEntity stringEntity = new StringEntity(xmlData, "UTF-8");
			stringEntity.setContentEncoding("UTF-8");
			httppost.setEntity(stringEntity);

			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 将返回的数据直接转成String
					str = EntityUtils.toString(entity, "UTF-8");

				}
			} finally {
				response.close();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return str;
	}

	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		List<String> keys = new ArrayList<String>(map.keySet());

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = "";
			if (map.get(key) != null) {
				value = map.get(key).toString();
			}
			sb.append(key + "=" + value);
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = s.substring(0, s.lastIndexOf("&"));
		}

		return s;
	}
}

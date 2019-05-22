package com.qian.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.util.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-20 上午11:24:23
 * @Description
 */
public class XMLUitls {

	public static Map<String, Object> XmlToDRCBMap(String xml) {
		xml = xml.replace("00<ap>", "<ap>");// 删除00前缀
		Map<String, Object> objMap = new HashMap<String, Object>();
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document d = DocumentHelper.readDocument(is);
			NodeList node = d.getElementsByTagName("ap");// 根元素
			objMap = nodeIteration(node.item(0));
			objMap.remove("body");
			objMap.remove("head");
			return objMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static String MapToDRCBXml(Map<String, Object> header, Map<String, Object> body) {
		StringBuffer sb = new StringBuffer();
		sb.append("00<ap><head>");
		// head
		for (Entry<String, Object> entry : header.entrySet()) {
			String nodeBegin = "<" + entry.getKey() + ">";
			String nodeEnd = "</" + entry.getKey() + ">";
			sb.append(nodeBegin + entry.getValue() + nodeEnd);
		}
		sb.append("</head><body>");
		// body
		for (Entry<String, Object> entry : body.entrySet()) {
			String nodeBegin = "<" + entry.getKey() + ">";
			String nodeEnd = "</" + entry.getKey() + ">";
			sb.append(nodeBegin + entry.getValue() + nodeEnd);
		}
		sb.append("</body></ap>");
		return sb.toString();
	}

	public static Map<String, Object> nodeIteration(Node rn) {
		NodeList childs = rn.getChildNodes();
		Map<String, Object> result = new HashMap<String, Object>();
		for (int i = 0; i < childs.getLength(); i++) {
			result.put(childs.item(i).getNodeName(), childs.item(i).getTextContent());
			if (childs.item(i).getChildNodes().getLength() > 1) {
				Map<String, Object> sonMap = nodeIteration(childs.item(i));
				for (Entry<String, Object> entry : sonMap.entrySet()) {
					result.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return result;
	}
}

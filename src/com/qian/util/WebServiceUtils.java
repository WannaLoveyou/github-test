package com.qian.util;


/**
 * @author Lu Kongwen
 * @version Create time：2016-12-26 上午11:21:25
 * @Description
 */
public class WebServiceUtils {

	public static void invokeRemoteFuc(String str, String sign, String tradeId){

		/*EndpointReference targetEPR = new EndpointReference("http://test.rongxintong.com:8088/rxtWebService/services/GylLoanInfoWebService?wsdl");
		RPCServiceClient sender = new RPCServiceClient();
		Options options = sender.getOptions();
		options.setTimeOutInMilliSeconds(2 * 20000L);// 超时时间20s
		options.setTo(targetEPR);

		QName qname = new QName("http://ws.apache.org/axis2", "loanInfo");

		Object[] param = new Object[] { str, sign, tradeId };
		Class<?>[] types = new Class[] { String.class }; // 这是针对返值类型的
		Object[] response = sender.invokeBlocking(qname, param, types);
		System.out.println(response[0]);
		*/
	}

	
}

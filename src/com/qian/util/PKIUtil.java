package com.qian.util;


import com.cfca.util.pki.api.CertUtil;
import com.cfca.util.pki.api.KeyUtil;
import com.cfca.util.pki.api.SignatureUtil;
import com.cfca.util.pki.cert.X509Cert;
import com.cfca.util.pki.cipher.JCrypto;
import com.cfca.util.pki.cipher.JKey;
import com.cfca.util.pki.cipher.Session;
import com.rksd.utils.StringUtil;

public class PKIUtil {
	
	public PKIUtil() {}
	
	private static Session session = null;
	private final static String privateKeyPassword = "123456";
	
	/**
	 * 初始化加密库
	 * 
	 * @return
	 */
	static{
		System.out.println("-------------------------加密库初始化开始-------------------------");
		try {
			// 初始化加密库，获得会话session
			// 多线程的应用可以共享一个session,不需要重复
			// 初始化加密库并获得session。
			// 系统退出后要jcrypto.finalize()，释放加密库
			JCrypto jcrypto = JCrypto.getInstance();
			jcrypto.initialize(JCrypto.JSOFT_LIB, null);
			session = jcrypto.openSession(JCrypto.JSOFT_LIB);
			System.out.println("-------------------------加密库初始化成功-------------------------");
		} catch (Exception ex) {
			System.out.println("-------------------------加密库初始化异常-------------------------");
			System.out.println(ex.toString());
		}
	}
	
	/**
	 * 加签方法
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String sign(String src) throws Exception{
		System.out.println("--------------------------------------------------加签----------------------------------------------------------");
		SignatureUtil signUtilSign = new SignatureUtil();
		String signAlg = SignatureUtil.MD5_RSA;
		JKey priKeyBggm = KeyUtil.getPriKey("D:/rongXinTong/cert.pfx", privateKeyPassword);
		X509Cert decodeCertBggm = CertUtil.getCert("D:/rongXinTong/cert.pfx", privateKeyPassword);
		//对消息签名
		String signedStr = new String ( signUtilSign.p7SignMessage(true, src.getBytes("UTF-8"), signAlg, priKeyBggm, new X509Cert[]{decodeCertBggm}, session) );
		System.out.println("签名结果为："+signedStr);
		System.out.println("--------------------------------------------------加签----------------------------------------------------------");
		return signedStr;
	}
	
	/**
	 * 验签方法
	 * @param src
	 * @param sign
	 * @param dnValue
	 * @return
	 */
	public static boolean signVerify(String src, String sign, String dnValue){
	
		SignatureUtil signUtilVerifySign = new SignatureUtil();
		String dn = dnValue.replace(" ", "");
		boolean verify = false;
		try {
			verify = signUtilVerifySign.p7VerifySignMessage(sign.getBytes("UTF-8"), session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("验证签名失败!");
			return verify;
		}
		if (verify) {
			System.out.println("验证签名成功!");
			try {
				System.out.println("签名中的原文为：" + new String(signUtilVerifySign.getSignedContent(),"UTF-8"));
				System.out.println("签名中的DN值为：" + new String(signUtilVerifySign.getSigerCert()[0].getSubject()));
				String signedContent = new String(signUtilVerifySign.getSignedContent());
				String dnFromSign = new String(signUtilVerifySign.getSigerCert()[0].getSubject());
				dnFromSign = dnFromSign.replace(" ", "");
				if(StringUtil.null2blank(src).equals(signedContent)&&dn.equals(dnFromSign)){
					System.out.println("内容与DN值验证成功!");
					return verify;
				}
				else{
					verify = false;
					return verify;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return verify;
	}

}

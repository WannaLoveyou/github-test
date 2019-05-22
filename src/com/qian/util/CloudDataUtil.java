package com.qian.util;

import java.util.ResourceBundle;

/**
 * 云平台数据上传工具类
 * @author DUANKK
 *
 */
public class CloudDataUtil {
	
	static ResourceBundle resource = ResourceBundle.getBundle("properties/clouddata");
	
	/**地址*/
	public static final String URL = resource.getString("url");
	
	/**接口*/
	public static final String RICORA00TEST = resource.getString("invoke.RicorA00Test");
	public static final String AUTHENTICATION = resource.getString("invoke.authentication");
	public static final String UPLOAD_GAS_CYLINDER = resource.getString("invoke.upload_gas_cylinder");
	public static final String UPLOAD_CHECK_RECORD = resource.getString("invoke.upload_check_record");
	public static final String UPLOAD_FILLING_RECORD = resource.getString("invoke.upload_filling_record");
	
	/**单次上传数量*/
	public static final int UP_NUMBER = Integer.valueOf(resource.getString("up_number"));

	/**其他数据*/
	public static final String USERNAME = PathUtils.getProperty("clouddata.userName");
	public static final String UNITNAME = PathUtils.getProperty("clouddata.unitName");
	public static final String APPKEY = PathUtils.getProperty("clouddata.appKey");
	
	public static final String ISINDUSTRIALGAS = PathUtils.getProperty("clouddata.isIndustrialGas");
	
	public static final String MAKENO = PathUtils.getProperty("clouddata.MAKENO");
	public static final String FILLINGUNIT = PathUtils.getProperty("clouddata.FILLINGUNIT");
	public static final String FILLINGCODE = PathUtils.getProperty("clouddata.FILLINGCODE");
	public static final String OWNFLAG = PathUtils.getProperty("clouddata.OWNFLAG");
	public static final String OWNUNIT = PathUtils.getProperty("clouddata.OWNUNIT");
	public static final String SBTYPE = PathUtils.getProperty("clouddata.SBTYPE");
	public static final String JARMEDIA = PathUtils.getProperty("clouddata.JARMEDIA");
	public static final String USESTATUS_USE = PathUtils.getProperty("clouddata.USESTATUS_USE");
	public static final String USESTATUS_SCRAP = PathUtils.getProperty("clouddata.USESTATUS_SCRAP");
	public static final String USESTATUS_LOSE = PathUtils.getProperty("clouddata.USESTATUS_LOSE");
	public static final String USESTATUS_CANCEL = PathUtils.getProperty("clouddata.USESTATUS_CANCEL");
	public static final String DESIGNUSEYEARS = PathUtils.getProperty("clouddata.DESIGNUSEYEARS");
	public static final String TESTRESULT_PASS = PathUtils.getProperty("clouddata.TESTRESULT_PASS");
	public static final String TESTRESULT_NOPASS = PathUtils.getProperty("clouddata.TESTRESULT_NOPASS");
	public static final String SUPERUNIT = PathUtils.getProperty("clouddata.SUPERUNIT");
	public static final String BUSISTATUS = PathUtils.getProperty("clouddata.BUSISTATUS");
	public static final String TAGNO = PathUtils.getProperty("clouddata.TAGNO");
	public static final String DATAFROM = PathUtils.getProperty("clouddata.DATAFROM");
	public static final String CITY = PathUtils.getProperty("clouddata.CITY");
	public static final String AREA = PathUtils.getProperty("clouddata.AREA");
	public static final String JClassP = PathUtils.getProperty("clouddata.JClassP");
}

package com.qian.vo;

import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleType;
import com.qian.util.CloudDataUtil;
import com.qian.util.DateUtil;
import com.qian.util.TimeUtils;

/**
 * 钢瓶vo
 * @author DUANKK
 * @version 2018年4月16日16:02:14
 */
public class GasCylinderDataVo {

	private String TokenKey;
	private String ID;//	气瓶ID
	private String MAKER;//	制造单位名称
	private String MAKENO;//	制造出厂钢印编号
	private String MAKEDATE;//	制造出厂日期
	private String JARNO;//	钢印永久性标记码(单位自编号)
	private String FILLINGUNIT;//	气瓶使用管理人
	private String FILLINGCODE;//	登记自编号
	private String OWNFLAG;//	产权归属
	private String OWNUNIT;//	产权单位
	private String JARUUID;//	唯一标识编码
	private String SBTYPE;//	设备种类
	private String JARTYPE;//	气瓶品种
	private String JARMODEL;//	气瓶型号
	private String JARMEDIA;//	充装介质
	private String USESTATUS;//	使用状态
	private String TESTPRESSURE;//	水试验压力(MPa)
	private String WORKPRESSURE;//	公称工作压力(MPa)
	private String DESIGNTHICKNESS;//	设计壁厚(mm)
	private String DIMENSION;//	容积(L)
	private String WEIGHT;//	重量(Kg)
	private String DESIGNUSEYEARS;//	设计使用年限
	private String LASTDATE;//	上次检验日期
	private String NEXTDATE;//	下次检验日期
	private String LASTUSEDATE;//	使用截止日期
	private String EndUSEDATE;//	终止使用日期
	private String TESTRESULT;//	检验结论
	private String TESTUNIT;//	气瓶检验单位
	private String SUPERUNIT;//	监察机关
	private String ReformUNIT;//	安装单位
	private String ReformDate;//	安装日期
	private String ReformTestUNIT;//	安装监检机构
	private String ReformTestDate;//	监检日期
	private String PlateNo;//	车辆牌号
	private String BUSISTATUS;//	管理状态
	private String TAGCODE;//	标签二维码值
	private String TAGNO;//	标签介质种类
	private String TAGPASTEDATE;//	标签粘贴时间
	private String DATAFROM;//	数据来源
	private String CITY;//	所在地市
	private String AREA;//	所在区县
	private String JClassP;//	档案库
	
	public GasCylinderDataVo(){}
	
	public GasCylinderDataVo(String TokenKey,String cloudId,AirBottleInfo a,AirBottleType type){
		
		String USESTATUS = CloudDataUtil.USESTATUS_USE;
		String TESTRESULT = CloudDataUtil.TESTRESULT_PASS;
		switch (a.getIsscrap()) {
		case 1:
			USESTATUS = CloudDataUtil.USESTATUS_SCRAP;
			TESTRESULT = CloudDataUtil.TESTRESULT_NOPASS;
			break;

		default:
			USESTATUS = CloudDataUtil.USESTATUS_USE;
			break;
		}
		
		int addNextYear = 4;
		addNextYear = (CloudDataUtil.ISINDUSTRIALGAS!=null&&"1".equals(CloudDataUtil.ISINDUSTRIALGAS))?3:4;
		
		this.TokenKey = TokenKey;
		this.ID = cloudId;
		this.MAKER = a.getProductionUnit().getProduction_unit_name();
		this.MAKENO = CloudDataUtil.MAKENO;
		this.MAKEDATE = TimeUtils.getyyyyMMddStringByDate(a.getProduce_time());
		this.JARNO = a.getAir_bottle_seal_code().toUpperCase();
		this.FILLINGUNIT = CloudDataUtil.FILLINGUNIT;
		this.FILLINGCODE = CloudDataUtil.FILLINGCODE;
		this.OWNFLAG = CloudDataUtil.OWNFLAG;
		this.OWNUNIT = CloudDataUtil.OWNUNIT;
		this.JARUUID = a.getAir_bottle_seal_code().toUpperCase();
		this.SBTYPE = CloudDataUtil.SBTYPE;
		this.JARTYPE = type.getAirBottleVariety().getAir_bottle_variety_name();
		this.JARMODEL = type.getAir_bottle_specifications();
		this.JARMEDIA = type.getFillingMedium().getFilling_medium_name();
		this.USESTATUS = USESTATUS;
		this.TESTPRESSURE = type.getWater_test_pressure();
		this.WORKPRESSURE = type.getNominal_working_pressure();
		this.DESIGNTHICKNESS = type.getWall_thickness().toString();
		this.DIMENSION = a.getVolume() == null?type.getVolume():a.getVolume();
		this.WEIGHT =a.getBottle_weight() == null? String.valueOf(type.getBottle_weight()) : String.valueOf(a.getBottle_weight());
		this.DESIGNUSEYEARS = a.getUse_cycle().toString();
		this.LASTDATE = TimeUtils.getyyyyMMddStringByDate(a.getCheck_time()==null?a.getProduce_time():a.getCheck_time());
		this.NEXTDATE = TimeUtils.getyyyyMMddStringByDate(a.getNext_check_time()==null?DateUtil.addYear2Date(addNextYear, a.getProduce_time()):a.getNext_check_time());
		this.LASTUSEDATE = TimeUtils.getyyyyMMddStringByDate(DateUtil.addYear2Date(a.getUse_cycle(), a.getProduce_time()));
		this.EndUSEDATE = TimeUtils.getyyyyMMddStringByDate(DateUtil.addYear2Date(a.getUse_cycle(), a.getProduce_time()));
		this.TESTRESULT = TESTRESULT;
		this.TESTUNIT = a.getDetectionUnit()==null?a.getProductionUnit().getProduction_unit_name():a.getDetectionUnit().getDetection_unit_name();
		this.SUPERUNIT = CloudDataUtil.SUPERUNIT;
		this.ReformUNIT = null;
		this.ReformDate = null;
		this.ReformTestUNIT = null;
		this.ReformTestDate = null;
		this.PlateNo = null;
		this.BUSISTATUS = CloudDataUtil.BUSISTATUS;
		this.TAGCODE = a.getAir_bottle_code();
		this.TAGNO = CloudDataUtil.TAGNO;
		this.TAGPASTEDATE = TimeUtils.getyyyyMMddHHmmssStringByDate(a.getCreate_time());
		this.DATAFROM = CloudDataUtil.DATAFROM;
		this.CITY = CloudDataUtil.CITY;
		this.AREA = CloudDataUtil.AREA;
		this.JClassP = CloudDataUtil.JClassP;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMAKER() {
		return MAKER;
	}
	public void setMAKER(String mAKER) {
		MAKER = mAKER;
	}
	public String getMAKENO() {
		return MAKENO;
	}
	public void setMAKENO(String mAKENO) {
		MAKENO = mAKENO;
	}
	public String getMAKEDATE() {
		return MAKEDATE;
	}
	public void setMAKEDATE(String mAKEDATE) {
		MAKEDATE = mAKEDATE;
	}
	public String getJARNO() {
		return JARNO;
	}
	public void setJARNO(String jARNO) {
		JARNO = jARNO;
	}
	public String getFILLINGUNIT() {
		return FILLINGUNIT;
	}
	public void setFILLINGUNIT(String fILLINGUNIT) {
		FILLINGUNIT = fILLINGUNIT;
	}
	public String getFILLINGCODE() {
		return FILLINGCODE;
	}
	public void setFILLINGCODE(String fILLINGCODE) {
		FILLINGCODE = fILLINGCODE;
	}
	public String getOWNFLAG() {
		return OWNFLAG;
	}
	public void setOWNFLAG(String oWNFLAG) {
		OWNFLAG = oWNFLAG;
	}
	public String getOWNUNIT() {
		return OWNUNIT;
	}
	public void setOWNUNIT(String oWNUNIT) {
		OWNUNIT = oWNUNIT;
	}
	public String getJARUUID() {
		return JARUUID;
	}
	public void setJARUUID(String jARUUID) {
		JARUUID = jARUUID;
	}
	public String getSBTYPE() {
		return SBTYPE;
	}
	public void setSBTYPE(String sBTYPE) {
		SBTYPE = sBTYPE;
	}
	public String getJARTYPE() {
		return JARTYPE;
	}
	public void setJARTYPE(String jARTYPE) {
		JARTYPE = jARTYPE;
	}
	public String getJARMODEL() {
		return JARMODEL;
	}
	public void setJARMODEL(String jARMODEL) {
		JARMODEL = jARMODEL;
	}
	public String getJARMEDIA() {
		return JARMEDIA;
	}
	public void setJARMEDIA(String jARMEDIA) {
		JARMEDIA = jARMEDIA;
	}
	public String getUSESTATUS() {
		return USESTATUS;
	}
	public void setUSESTATUS(String uSESTATUS) {
		USESTATUS = uSESTATUS;
	}
	public String getWORKPRESSURE() {
		return WORKPRESSURE;
	}
	public void setWORKPRESSURE(String wORKPRESSURE) {
		WORKPRESSURE = wORKPRESSURE;
	}
	public String getDESIGNTHICKNESS() {
		return DESIGNTHICKNESS;
	}
	public void setDESIGNTHICKNESS(String dESIGNTHICKNESS) {
		DESIGNTHICKNESS = dESIGNTHICKNESS;
	}
	public String getDIMENSION() {
		return DIMENSION;
	}
	public void setDIMENSION(String dIMENSION) {
		DIMENSION = dIMENSION;
	}
	public String getWEIGHT() {
		return WEIGHT;
	}
	public void setWEIGHT(String wEIGHT) {
		WEIGHT = wEIGHT;
	}
	public String getDESIGNUSEYEARS() {
		return DESIGNUSEYEARS;
	}
	public void setDESIGNUSEYEARS(String dESIGNUSEYEARS) {
		DESIGNUSEYEARS = dESIGNUSEYEARS;
	}
	public String getLASTDATE() {
		return LASTDATE;
	}
	public void setLASTDATE(String lASTDATE) {
		LASTDATE = lASTDATE;
	}
	public String getNEXTDATE() {
		return NEXTDATE;
	}
	public void setNEXTDATE(String nEXTDATE) {
		NEXTDATE = nEXTDATE;
	}
	public String getLASTUSEDATE() {
		return LASTUSEDATE;
	}
	public void setLASTUSEDATE(String lASTUSEDATE) {
		LASTUSEDATE = lASTUSEDATE;
	}
	public String getEndUSEDATE() {
		return EndUSEDATE;
	}
	public void setEndUSEDATE(String endUSEDATE) {
		EndUSEDATE = endUSEDATE;
	}
	public String getTESTRESULT() {
		return TESTRESULT;
	}
	public void setTESTRESULT(String tESTRESULT) {
		TESTRESULT = tESTRESULT;
	}
	public String getTESTUNIT() {
		return TESTUNIT;
	}
	public void setTESTUNIT(String tESTUNIT) {
		TESTUNIT = tESTUNIT;
	}
	public String getSUPERUNIT() {
		return SUPERUNIT;
	}
	public void setSUPERUNIT(String sUPERUNIT) {
		SUPERUNIT = sUPERUNIT;
	}
	public String getReformUNIT() {
		return ReformUNIT;
	}
	public void setReformUNIT(String reformUNIT) {
		ReformUNIT = reformUNIT;
	}
	public String getReformDate() {
		return ReformDate;
	}
	public void setReformDate(String reformDate) {
		ReformDate = reformDate;
	}
	public String getReformTestUNIT() {
		return ReformTestUNIT;
	}
	public void setReformTestUNIT(String reformTestUNIT) {
		ReformTestUNIT = reformTestUNIT;
	}
	public String getReformTestDate() {
		return ReformTestDate;
	}
	public void setReformTestDate(String reformTestDate) {
		ReformTestDate = reformTestDate;
	}
	public String getPlateNo() {
		return PlateNo;
	}
	public void setPlateNo(String plateNo) {
		PlateNo = plateNo;
	}
	public String getBUSISTATUS() {
		return BUSISTATUS;
	}
	public void setBUSISTATUS(String bUSISTATUS) {
		BUSISTATUS = bUSISTATUS;
	}
	public String getTAGCODE() {
		return TAGCODE;
	}
	public void setTAGCODE(String tAGCODE) {
		TAGCODE = tAGCODE;
	}
	public String getTAGNO() {
		return TAGNO;
	}
	public void setTAGNO(String tAGNO) {
		TAGNO = tAGNO;
	}
	public String getTAGPASTEDATE() {
		return TAGPASTEDATE;
	}
	public void setTAGPASTEDATE(String tAGPASTEDATE) {
		TAGPASTEDATE = tAGPASTEDATE;
	}
	public String getDATAFROM() {
		return DATAFROM;
	}
	public void setDATAFROM(String dATAFROM) {
		DATAFROM = dATAFROM;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getAREA() {
		return AREA;
	}
	public void setAREA(String aREA) {
		AREA = aREA;
	}
	public String getJClassP() {
		return JClassP;
	}
	public void setJClassP(String jClassP) {
		JClassP = jClassP;
	}

	public String getTokenKey() {
		return TokenKey;
	}

	public void setTokenKey(String tokenKey) {
		TokenKey = tokenKey;
	}

	public String getTESTPRESSURE() {
		return TESTPRESSURE;
	}

	public void setTESTPRESSURE(String tESTPRESSURE) {
		TESTPRESSURE = tESTPRESSURE;
	}

	@Override
	public String toString() {
		return "GasCylinderDataVo [TokenKey=" + TokenKey + ", ID=" + ID
				+ ", MAKER=" + MAKER + ", MAKENO=" + MAKENO + ", MAKEDATE="
				+ MAKEDATE + ", JARNO=" + JARNO + ", FILLINGUNIT="
				+ FILLINGUNIT + ", FILLINGCODE=" + FILLINGCODE + ", OWNFLAG="
				+ OWNFLAG + ", OWNUNIT=" + OWNUNIT + ", JARUUID=" + JARUUID
				+ ", SBTYPE=" + SBTYPE + ", JARTYPE=" + JARTYPE + ", JARMODEL="
				+ JARMODEL + ", JARMEDIA=" + JARMEDIA + ", USESTATUS="
				+ USESTATUS + ", WORKPRESSURE=" + WORKPRESSURE
				+ ", DESIGNTHICKNESS=" + DESIGNTHICKNESS + ", DIMENSION="
				+ DIMENSION + ", WEIGHT=" + WEIGHT + ", DESIGNUSEYEARS="
				+ DESIGNUSEYEARS + ", LASTDATE=" + LASTDATE + ", NEXTDATE="
				+ NEXTDATE + ", LASTUSEDATE=" + LASTUSEDATE + ", EndUSEDATE="
				+ EndUSEDATE + ", TESTRESULT=" + TESTRESULT + ", TESTUNIT="
				+ TESTUNIT + ", SUPERUNIT=" + SUPERUNIT + ", ReformUNIT="
				+ ReformUNIT + ", ReformDate=" + ReformDate
				+ ", ReformTestUNIT=" + ReformTestUNIT + ", ReformTestDate="
				+ ReformTestDate + ", PlateNo=" + PlateNo + ", BUSISTATUS="
				+ BUSISTATUS + ", TAGCODE=" + TAGCODE + ", TAGNO=" + TAGNO
				+ ", TAGPASTEDATE=" + TAGPASTEDATE + ", DATAFROM=" + DATAFROM
				+ ", CITY=" + CITY + ", AREA=" + AREA + ", JClassP=" + JClassP
				+ "]";
	}
	
	
}

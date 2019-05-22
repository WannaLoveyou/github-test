package com.qian.vo;

/**
 * 充装记录vo
 * @author DUANKK
 * @version 2018年4月17日09:52:20
 */
public class FillingDataVo {

	private String TokenKey;//
	private String ID;//	ID
	private String MAKER;//	制造单位名称
	private String MAKENO;//	制造出厂钢印编号
	private String MAKEDATE;//	制造出厂日期
	private String JARNO;//	钢印永久性标记码(单位自编号)
	private String JCJG;//	充前检查结果
	private String FCJG;//	充后复检结果
	private String FILLINGTIME;//	充装时间
	private String MEMO;//	备注
	private String FILLINGUNIT;//	气瓶使用管理人
	private String FILLINGCODE;//	登记自编号
	
	public FillingDataVo(){}
	
	public FillingDataVo(String TokenKey, String ID,String MAKER,String MAKENO,String MAKEDATE,String JARNO,
			String JCJG,String FCJG,String FILLINGTIME,String MEMO,String FILLINGUNIT,String FILLINGCODE){
		this.TokenKey=TokenKey;
		this.ID=ID;
		this.MAKER=MAKER;
		this.MAKENO=MAKENO;
		this.MAKEDATE=MAKEDATE;
		this.JARNO=JARNO;
		this.JCJG=JCJG;
		this.FCJG=FCJG;
		this.FILLINGTIME=FILLINGTIME;
		this.MEMO=MEMO;
		this.FILLINGUNIT=FILLINGUNIT;
		this.FILLINGCODE=FILLINGCODE;
	}
	
	public String getTokenKey() {
		return TokenKey;
	}

	public void setTokenKey(String tokenKey) {
		TokenKey = tokenKey;
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
	public String getJCJG() {
		return JCJG;
	}
	public void setJCJG(String jCJG) {
		JCJG = jCJG;
	}
	public String getFCJG() {
		return FCJG;
	}
	public void setFCJG(String fCJG) {
		FCJG = fCJG;
	}
	public String getFILLINGTIME() {
		return FILLINGTIME;
	}
	public void setFILLINGTIME(String fILLINGTIME) {
		FILLINGTIME = fILLINGTIME;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
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
	
}

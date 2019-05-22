package com.qian.vo;

/**
 * 检测记录vo
 * @author DUANKK
 * @version 2018年4月17日09:35:45
 */
public class CheckDataVo {

	private String TokenKey;//
	private String ID;//	ID
	private String MAKER;//	制造单位名称
	private String MAKENO;//	制造出厂钢印编号
	private String MAKEDATE;//	制造出厂日期
	private String JARNO;//	钢印永久性标记码(单位自编号)
	private String REPORTCODE;// 	报告书编号
	private String EXAMUNIT;//	检验单位
	private String EXAMRESULT;//	检验结论
	private String LASTDATE;//	检验日期
	private String NEXTDATE;//	下次检验日期
	private String EXAMDESC;//	结论说明
	private String MEMO;//	备注
	private String FILLINGUNIT;//	气瓶使用管理人
	private String FILLINGCODE;//	登记自编号
	
	public CheckDataVo(){}
	
	public CheckDataVo(String TokenKey, String ID,String MAKER,String MAKENO,String MAKEDATE,String JARNO,
			String REPORTCODE ,String EXAMUNIT,String EXAMRESULT,String LASTDATE,
			String NEXTDATE,String EXAMDESC,String MEMO,String FILLINGUNIT,String FILLINGCODE){
		this.TokenKey=TokenKey;
		this.ID=ID;
		this.MAKER=MAKER;
		this.MAKENO=MAKENO;
		this.MAKEDATE=MAKEDATE;
		this.JARNO=JARNO;
		this.REPORTCODE =REPORTCODE ;
		this.EXAMUNIT=EXAMUNIT;
		this.EXAMRESULT=EXAMRESULT;
		this.LASTDATE=LASTDATE;
		this.NEXTDATE=NEXTDATE;
		this.EXAMDESC=EXAMDESC;
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
	public String getREPORTCODE() {
		return REPORTCODE;
	}
	public void setREPORTCODE(String rEPORTCODE) {
		REPORTCODE = rEPORTCODE;
	}
	public String getEXAMUNIT() {
		return EXAMUNIT;
	}
	public void setEXAMUNIT(String eXAMUNIT) {
		EXAMUNIT = eXAMUNIT;
	}
	public String getEXAMRESULT() {
		return EXAMRESULT;
	}
	public void setEXAMRESULT(String eXAMRESULT) {
		EXAMRESULT = eXAMRESULT;
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
	public String getEXAMDESC() {
		return EXAMDESC;
	}
	public void setEXAMDESC(String eXAMDESC) {
		EXAMDESC = eXAMDESC;
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

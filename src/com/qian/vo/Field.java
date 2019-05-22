package com.qian.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库字段定义
 * 
 * @Author 李朝阳
 * @Date 2015-9-22
 * @Version 1.0
 * @Remark
 */
public class Field {
	private List<FieldVo> m_aParam = new ArrayList<FieldVo>();

	public Field addFieldVo(FieldVo fieldVo){
		this.m_aParam.add(fieldVo);
		return this;
	}
	
	public Field addStr(String sValue) {
		return addStr(null, sValue);
	}

	public Field addStr(String sKey, String sValue) {
		m_aParam.add(new FieldVo(sKey, sValue, java.sql.Types.VARCHAR));
		return this;
	}

	public Field addStrL(String sValue) {
		return addStrL(null, sValue);
	}

	public Field addStrL(String sKey, String sValue) {
		m_aParam.add(new FieldVo(sKey, sValue, java.sql.Types.CLOB));
		return this;
	}

	public Field addInt(int nValue) {
		return addInt(null, nValue);
	}

	public Field addInt(String sKey, int nValue) {
		m_aParam.add(new FieldVo(sKey, nValue, java.sql.Types.INTEGER));
		return this;
	}

	public Field addFloat(float nValue) {
		return addFloat(null, nValue);
	}

	public Field addFloat(String sKey, float nValue) {
		m_aParam.add(new FieldVo(sKey, nValue, java.sql.Types.FLOAT));
		return this;
	}

	public Field addDouble(double nValue) {
		return addDouble(null, nValue);
	}

	public Field addDouble(String sKey, double nValue) {
		m_aParam.add(new FieldVo(sKey, nValue, java.sql.Types.DOUBLE));
		return this;
	}

	public Field addDateTime(long time) {
		return addDateTime(null, time);
	}

	public Field addDateTime(String sKey, long time) {
		m_aParam.add(new FieldVo(sKey, time, java.sql.Types.TIMESTAMP));
		return this;
	}

	public Field addDate(String s) {
		return addDate(null, s);
	}

	public Field addDate(String sKey, String s) {
		m_aParam.add(new FieldVo(sKey, s, java.sql.Types.DATE));
		return this;
	}

	/****
	 * 根据key 获取查找字段 如果查找不到 则返回null
	 * 
	 * @param fKey
	 * @return
	 */
	public FieldVo getByKey(String fKey) {
		for (FieldVo fv : m_aParam) {
			if (fv != null && fv.m_sKey.equals(fKey))
				return fv;

		}
		return null;
	}

	public List<FieldVo> getFields() {
		return m_aParam;
	}

	public void setFields(List<FieldVo> l) {
		m_aParam.addAll(l);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m_aParam.size(); i++) {
			sb.append(m_aParam.get(i).m_sKey).append(":").append(m_aParam.get(i).m_oValue).append("|");
		}
		return sb.toString();
	}
}

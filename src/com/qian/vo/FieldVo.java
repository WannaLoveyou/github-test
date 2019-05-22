package com.qian.vo;



public class FieldVo
{
	public String m_sKey;
	public Object m_oValue;
	public int m_nType;
	
	public FieldVo(String sKey, Object oValue, int nType)
	{
		m_sKey = sKey;
		m_oValue = oValue;
		m_nType = nType;
	}
}
package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.RepairType;
import com.qian.vo.Field;
@Component
public class RepairTypeDaoImpl extends HBaseDao<RepairType> {
	
	public List<RepairType> getPageList(int page,int rows){
		return selectFromHQL("from RepairType", (page-1)*rows, rows);
	}
	
	public Long getTotalNum(){
		return getTotalFromHQL("from RepairType");
	}
	
	public void delByIds(String[] ids){
		List<Field> afield=new ArrayList<Field>();
		for(String s:ids){
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}
		delete("delete from RepairType where id=?",afield);
	}
	
	public List<RepairType> getAllList(){
		return selectFromHQL("from RepairType");
	}
}

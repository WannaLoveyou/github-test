package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ComplaintType;
import com.qian.vo.Field;
@Component
public class ComplaintTypeDaoImpl extends HBaseDao<ComplaintType>{
	public List<ComplaintType> getPageList(int page,int rows){
		return selectFromHQL("from ComplaintType", (page-1)*rows, rows);
	}
	
	public Long getTotalNum(){
		return getTotalFromHQL("from ComplaintType");
	}
	
	public void delByIds(String[] ids){
		List<Field> afield=new ArrayList<Field>();
		for(String s:ids){
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}
		delete("delete from ComplaintType where id=?",afield);
	}
	
	public List<ComplaintType> getAllList(){
		
		return selectFromHQL("from ComplaintType");
	}
}

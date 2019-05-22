package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.VisitType;
import com.qian.vo.Field;
@Component
public class VisitTypeDaoImpl extends HBaseDao<VisitType>{
	public List<VisitType> getPageList(int page,int rows){
		return selectFromHQL("from VisitType", (page-1)*rows, rows);
	}
	
	public Long getTotalNum(){
		return getTotalFromHQL("from VisitType");
	}
	
	public void delByIds(String[] ids){
		List<Field> afield=new ArrayList<Field>();
		for(String s:ids){
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}
		delete("delete from VisitType where id=?",afield);
	}
	
	public List<VisitType> getAllList(){
		
		return selectFromHQL("from VisitType");
	}
}

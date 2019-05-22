package com.qian.dao.impl;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ClientType;
import com.qian.vo.Field;

@Component
public class ClientTypeDaoImpl extends HBaseDao<ClientType> {

	public List<ClientType> getPageList(int page,int rows){
		return selectFromSQL(" select * from client_type limit "+(page-1)*rows+","+rows);
	}
	
	public long toallNum(){
		return getTotalFromHQL(" from ClientType");
	}
	
	public void delById(int id){
		Field field = new Field();
		field.addInt(id);
		delete(" delete from client_type where id=?",field);
	}
	
	public List<ClientType> getAllList() {
		return selectFromHQL(" from ClientType");
	}
}
	
	
	
	
	
	
	
	
	
	
	

package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PartPrice;
import com.qian.vo.Field;

@Component
public class PartPriceDaoImpl extends HBaseDao<PartPrice>{
	
	public List<PartPrice> getPageList(int page,int rows){
		return selectFromHQL("from PartPrice", (page-1)*rows, rows);
	}
	
	public Long getTotalNum(){
		return getTotalFromHQL("from PartPrice");
	}
	
	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();
		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete PartPrice as a where a.id = ?", afield);

	}
	
	
	public List<PartPrice> getAllList() {
		
		return selectFromHQL(" from PartPrice");
	}
	
	public List<PartPrice> getFee(int airBottleTypeId,int partTypeId){
		
		List<PartPrice> partPrice=selectFromHQL(" from PartPrice  where air_bottle_type_id="+airBottleTypeId+" and part_type_id="+partTypeId);
		return partPrice;
	}

	public List<PartPrice> getListByBottleType(int bottleTypeId) {
		
		Field field = new Field();
		field.addInt(bottleTypeId);
		
		List<PartPrice> result = selectFromHQL(" from PartPrice  where airBottleType.id = ? ",field);
		
		return result;
	};
}

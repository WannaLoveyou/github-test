package com.qian.dao;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.qian.dao.impl.HDaoImpl;


/**
 * Hibernate数据库抽象类
 * @Author 李朝阳
 *@Author 李朝阳
 * @Date 2015-9-22
 * @Version 1.0
 * @Remark
 */
public abstract class HBaseDao<T> extends HDaoImpl<T>
{
	@Autowired
    @Qualifier("sessionFactory")
	@Override
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}

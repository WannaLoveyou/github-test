package com.qian.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.qian.util.SqlUtils;
import com.qian.vo.Field;
import com.qian.vo.FieldVo;

/**
 * 数据库操作实现
 * 
 * @Author 李朝阳
 * @Date 2015-9-22
 * @Version 1.0
 * @Remark
 */
public abstract class HDaoImpl<T> {
	protected SessionFactory sessionFactory = null;
	protected Class<T> entityClass = null;
	private final int m_nBatchSize = 100;

	/**
	 * 通过主键获取对象
	 * 
	 * @param oPKId
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T findById(Serializable oPKId) {
		return (T) (getSession().get(getEntityClass(), oPKId));
	}

	/**
	 * HQL查询数据库记录
	 * 
	 * @param HQL
	 * @return List<T>
	 */
	public List<T> selectFromHQL(String HQL) {
		return selectFromHQL(HQL, null);
	}

	/**
	 * SQL查询数据库记录
	 * 
	 * @param SQL
	 * @return List<T>
	 */
	public List<T> selectFromSQL(String SQL) {
		return selectFromSQL(SQL, null);
	}

	/**
	 * 查询数据库记录
	 * 
	 * @param SQL
	 * @param claz
	 *            实体类类型
	 * @return List
	 */
	public List selectFromSQLByClass(String SQL, Class claz) {
		return selectFromSQLByClass(SQL, null, claz);
	}

	public List selectFromSQLByMap(String SQL) {
		Query query = getSession().createSQLQuery(SQL).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	/**
	 * 通过条件查询数据库记录（HQL语句）
	 * 
	 * @param HQL
	 * @param oField
	 *            条件值
	 * @return List<T>
	 */
	public List<T> selectFromHQL(String HQL, Field oField) {
		return select(HQL, oField, false);
	}

	/**
	 * 通过条件查询数据库记录（SQL语句）
	 * 
	 * @param SQL
	 * @param oField
	 *            条件值
	 * @return List<T>
	 */
	public List<T> selectFromSQL(String SQL, Field oField) {
		return select(SQL, oField, true);
	}

	/**
	 * 通过条件查询数据库记录（SQL语句）
	 * 
	 * @param SQL
	 * @param oField
	 *            条件值
	 * @param claz
	 *            类型
	 * @return List
	 */
	private List selectFromSQLByClass(String SQL, Field oField, Class claz) {
		return selectByClass(SQL, oField, true, claz);
	}

	/**
	 * 通过条件查询数据库记录（HQL语句）
	 * 
	 * @param HQL
	 * @param oField
	 *            条件值
	 * @param claz
	 *            类型
	 * @return List
	 */
	public List selectFromHQLByClass(String HQL, Field oField, Class claz) {
		return selectByClass(HQL, oField, false, claz);
	}

	public List selectFromHQLByClass(String HQL, Field oField, int nStart, int nOffset, Class claz) {
		return selectByClass(HQL, oField, nStart, nOffset, false, claz);
	}

	@SuppressWarnings("unchecked")
	private List<T> select(String sHQL, Field oField, boolean bIsSQL) {
		Query oQuery = getQuery(sHQL, oField, bIsSQL);
		return oQuery.list();
	}

	private List selectByClass(String sHQL, Field oField, boolean bIsSQL, Class claz) {
		Query oQuery = getQuery(sHQL, oField, bIsSQL, claz);
		return oQuery.list();
	}

	/**
	 * 查询数据库记录-带分页
	 * 
	 * @param HQL
	 * @param nStart
	 * @param nOffset
	 * @return List<T>
	 */
	public List<T> selectFromHQL(String HQL, int nStart, int nOffset) {
		return select(HQL, null, nStart, nOffset);
	}

	/**
	 * 查询数据库记录-带分页-带条件
	 * 
	 * @param HQL
	 * @param nStart
	 * @param nOffset
	 * @return List<T>
	 */
	public List<T> selectFromHQL(String HQL, Field oField, int nStart, int nOffset) {
		return select(HQL, oField, nStart, nOffset);
	}

	/**
	 * 查询数据库记录-带分页
	 * 
	 * @param SQL
	 * @param nStart
	 * @param nOffset
	 * @return List<T>
	 */
	public List<T> selectFromSQL(String SQL, int nStart, int nOffset) {
		return selectFromSQL(SQL, null, nStart, nOffset);
	}

	/**
	 * 查询数据库记录-带分页
	 * 
	 * @param SQL
	 * @param nStart
	 * @param nOffset
	 * @param claz
	 *            类型
	 * @return List
	 */
	public List selectFromSQLByClass(String SQL, int nStart, int nOffset, Class claz) {
		return selectFromSQLByClass(SQL, null, nStart, nOffset, claz);
	}

	/**
	 * 查询数据库记录-带分页
	 * 
	 * @param HQL
	 * @param oField
	 * @param nStart
	 * @param nOffset
	 * @return List<T>
	 */
	private List<T> select(String HQL, Field oField, int nStart, int nOffset) {
		return select(HQL, oField, nStart, nOffset, false);
	}

	private List<T> selectFromSQL(String SQL, Field oField, int nStart, int nOffset) {
		return select(SQL, oField, nStart, nOffset, true);
	}

	private List selectFromSQLByClass(String sSQL, Field oField, int nStart, int nOffset, Class claz) {
		return selectByClass(sSQL, oField, nStart, nOffset, true, claz);
	}

	@SuppressWarnings("unchecked")
	private List<T> select(String sHQL, Field oField, int nStart, int nOffset, boolean bIsSQL) {
		Query oQuery = getQuery(sHQL, oField, bIsSQL);
		oQuery.setFirstResult(nStart);
		oQuery.setMaxResults(nOffset);
		return oQuery.list();
	}

	private List selectByClass(String sHQL, Field oField, int nStart, int nOffset, boolean bIsSQL, Class clz) {
		Query oQuery = getQuery(sHQL, oField, bIsSQL, clz);
		oQuery.setFirstResult(nStart);
		oQuery.setMaxResults(nOffset);
		return oQuery.list();
	}

	/**
	 * 获取数据库记录总数
	 * 
	 * @param HQL
	 * @return Long
	 */
	public Long getTotalFromHQL(String HQL) {
		return getTotalFromHQL(HQL, null);
	}

	/**
	 * 获取数据库记录总数
	 * 
	 * @param HQL
	 * @param oField
	 * @return Long
	 */
	public Long getDistinctTotalFromHQL(String HQL, Field oField, String distinctHQL) {
		Query oQuery = getQuery("SELECT COUNT(" + distinctHQL + ") " + HQL, oField, false);
		return oQuery == null ? 0L : oQuery.uniqueResult() == null ? 0L : ((Long) oQuery.uniqueResult()).intValue();
	}

	public Long getTotalFromHQL(String HQL, Field oField) {
		Query oQuery = getQuery("SELECT COUNT(*) " + HQL, oField, false);
		return oQuery == null ? 0L : oQuery.uniqueResult() == null ? 0L : ((Long) oQuery.uniqueResult()).intValue();
	}

	public Object getTotalFromSQLByClass(String SQL, Field oField, Class claz) {
		Query oQuery = getQuery(SQL, oField, true, claz);
		return oQuery == null ? 0L : oQuery.uniqueResult() == null ? 0L : oQuery.uniqueResult();
	}

	/**
	 * 增加数据库记录
	 * 
	 * @param oObject
	 * @return int
	 */
	public int insert(T oObject) {
		int nTotal = -1;
		try {
			nTotal = getSession().save(oObject) == null ? -1 : 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nTotal;
	}

	/**
	 * 增加数据库记录
	 * 
	 * @param oObject
	 * @return T
	 */
	public T save(T oObject) {
		try {
			getSession().save(oObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oObject;
	}

	/**
	 * 批量增加数据库记录
	 * 
	 * @param aObject
	 * @return int
	 */
	public int insert(List<T> aObject) {
		int nTotal = 0;
		for (T oObj : aObject) {
			if (insert(oObj) != -1) {
				getSession().flush();
				getSession().clear();
				nTotal++;
			}
			submitBatch(nTotal);
		}
		return nTotal == 0 ? -1 : nTotal;
	}

	/**
	 * 更新数据库记录
	 * 
	 * @param oObject
	 * @return int
	 */
	public int update(T oObject) {
		int nTotal = -1;
		try {
			getSession().update(oObject);
			getSession().flush();
			getSession().clear();
			nTotal = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nTotal;
	}

	/**
	 * 批量更新数据库记录
	 * 
	 * @param aObject
	 * @return int
	 */
	public int update(List<T> aObject) {
		int nTotal = 0;
		for (T oObj : aObject) {
			if (update(oObj) != -1) {
				nTotal++;
			}
			submitBatch(nTotal);
		}
		return nTotal == 0 ? -1 : nTotal;
	}

	/**
	 * 更新数据库记录
	 * 
	 * @param HQL
	 * @return int
	 */
	public int update(String HQL) {
		return exec(HQL, null);
	}

	/**
	 * 更新数据库记录
	 * 
	 * @param SQL
	 * @param oField
	 * @return int
	 */
	public int update(String SQL, Field oField) {
		return execBysql(SQL, oField);
	}

	public int updateSQL(String SQL) {
		return execBysql(SQL, null);
	}
	
	public int updateHQL(String HQL, Field oField) {
		return exec(HQL, oField);
	}

	/**
	 * 批量更新数据库记录
	 * 
	 * @param HQL
	 * @param aField
	 * @return int
	 */
	public int update(String HQL, List<Field> aField) {
		return execBatch(HQL, aField);
	}
	
	/**
	 * 批量条件更新批量数据库字段记录
	 * @param setList 需要更新的字段 eg:status = ?
	 * @param setField 需要更新的字段内容 eg:1
	 * @param whereList 条件字段 eg:id in (?)
	 * @param whereField 条件字段内容 eg:1,2
	 * @param Entiy 更新的对象
	 * @return int 执行条数
	 */
	public int update(List<String> setList, Field setField, List<String> whereList, Field whereField, String Entiy){
		Field field = new Field();
		List<FieldVo> setFields = setField.getFields();
		for (FieldVo fieldVo : setFields) {
			field.addFieldVo(fieldVo);
		}
		List<FieldVo> whereFields = whereField.getFields();
		for (FieldVo fieldVo : whereFields) {
			field.addFieldVo(fieldVo);
		}
		return updateHQL(SqlUtils.initUpdateConditionsSql(setList, whereList, Entiy), field);
	}

	/**
	 * 删除数据库记录
	 * 
	 * @param oObject
	 * @return int
	 */
	public int delete(T oObject) {
		int nTotal = -1;
		try {
			getSession().delete(oObject);
			getSession().flush();
			getSession().clear();
			nTotal = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nTotal;
	}

	/**
	 * 删除数据库记录
	 * 
	 * @param aObject
	 * @return int
	 */
	public int delete(List<T> aObject) {
		int nTotal = 0;
		for (T oObj : aObject) {
			if (delete(oObj) != -1) {
				getSession().flush();
				getSession().clear();
				nTotal++;
			}
			submitBatch(nTotal);
		}
		return nTotal == 0 ? -1 : nTotal;
	}

	/**
	 * 删除数据库记录
	 * 
	 * @param HQL
	 * @return int
	 */
	public int delete(String HQL) {
		return exec(HQL, null);
	}

	/**
	 * 删除数据库记录
	 * 
	 * @param SQL
	 * @param oField
	 * @return int
	 */
	public int delete(String SQL, Field oField) {
		return execBysql(SQL, oField);
	}

	/**
	 * 批量删除数据库记录
	 * 
	 * @param HQL
	 * @param aField
	 * @return int
	 */
	public int delete(String HQL, List<Field> aField) {
		return execBatch(HQL, aField);
	}

	/**
	 * 执行更新、删除操作
	 * 
	 * @param sHQL
	 * @param oField
	 * @return int
	 */
	private int exec(String sHQL, Field oField) {
		Query oQuery = getQuery(sHQL, oField, false);
		return oQuery.executeUpdate();
	}

	private int execBysql(String sHQL, Field oField) {
		Query oQuery = getQuery(sHQL, oField, true);
		return oQuery.executeUpdate();
	}

	private int execBatch(String sHQL, List<Field> aField) {
		int nTotal = 0;
		if (aField != null && aField.size() > -0) {
			for (Field oField : aField) {
				if (oField != null) {
					nTotal += exec(sHQL, oField);
					submitBatch(nTotal);
				}
			}
		}
		return nTotal;
	}

	public int execBatchBysql(String sHQL, List<Field> aField) {
		int nTotal = 0;
		String[] sqlA = sHQL.split(";");
		if (aField != null && aField.size() > 0) {
			for (int i = 0; i < aField.size(); i++) {
				Field oField = aField.get(i);
				String sql = sqlA[i];
				if (oField != null && sql != null && !sql.trim().equals("")) {
					nTotal += execBysql(sql, oField);
					submitBatch(nTotal);
				}
			}
		}
		return nTotal;
	}

	/**
	 * 获取Hibernate的数据操作工厂
	 * 
	 * @param sessionFactory
	 */
	public abstract void setSessionFactory(SessionFactory sessionFactory);

	/**
	 * 获取泛型参数类型
	 * 
	 * @return Class<T>
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	/**
	 * 获取数据库的操作Session
	 * 
	 * @return Session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 获取查询的Query
	 * 
	 * @param sHQL
	 * @param oField
	 * @param bIsSQL
	 *            true为sql操作 false为hql操作
	 * @return Query
	 */
	private Map getQueryByMap(String sHQL, Field oField, boolean bIsSQL) {
		Query oQuery = bIsSQL ? getSession().createSQLQuery(sHQL).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) : getSession().createQuery(sHQL);
		setParameters(oQuery, oField);
		return (Map) oQuery;
	}

	private Query getQuery(String sHQL, Field oField, boolean bIsSQL) {
		Query oQuery = bIsSQL ? getSession().createSQLQuery(sHQL).setResultTransformer(Transformers.aliasToBean(getEntityClass())) : getSession().createQuery(
				sHQL);
		setParameters(oQuery, oField);
		return oQuery;
	}

	private Query getQuery(String sHQL, Field oField, boolean bIsSQL, Class claz) {
		Query oQuery = bIsSQL ? getSession().createSQLQuery(sHQL).setResultTransformer(Transformers.aliasToBean(claz)) : getSession().createQuery(sHQL);
		setParameters(oQuery, oField);
		return oQuery;
	}

	/**
	 * 设置SQL参数
	 * 
	 * @param oQuery
	 * @param oField
	 */
	protected void setParameters(Query oQuery, Field oField) {
		if (oField != null && oField.getFields().size() > 0) {
			List<FieldVo> aParam = oField.getFields();
			for (int i = 0, nLen = aParam.size(); i < nLen; i++) {
				FieldVo oFieldVo = aParam.get(i);
				switch (oFieldVo.m_nType) {
				case Types.INTEGER:
					oQuery.setInteger(i, Integer.parseInt(oFieldVo.m_oValue.toString()));
					break;
				case Types.FLOAT:
					oQuery.setFloat(i, Float.parseFloat(oFieldVo.m_oValue.toString()));
					break;
				case Types.DOUBLE:
					oQuery.setDouble(i, Double.parseDouble(oFieldVo.m_oValue.toString()));
					break;
				case Types.TIMESTAMP:
					oQuery.setTimestamp(i, new Timestamp(Long.parseLong(oFieldVo.m_oValue.toString())));
					break;
				case Types.DATE:
					oQuery.setDate(i, java.sql.Date.valueOf(oFieldVo.m_oValue.toString()));
					break;
				case Types.TIME:
					oQuery.setTime(i, java.sql.Time.valueOf(oFieldVo.m_oValue.toString()));
					break;
				default:
					oQuery.setParameter(i, oFieldVo.m_oValue.toString());
					break;
				}
			}
		}
	}

	/**
	 * 批量提交
	 * 
	 * @param nTotal
	 *            return void
	 */
	protected void submitBatch(int nTotal) {
		if (nTotal > 0 && nTotal % m_nBatchSize == 0) {
			getSession().flush();
			getSession().clear();
		}
	}

	public String getEntityName() {

		Class cls = getEntityClass();

		return cls.getSimpleName();
	}

	// 按条件分页查找
	public List<T> getPageList(List<String> l, Field field, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), field, (page - 1) * rows, rows);
	}

	// 按条件查询总数
	public Long getTotalNum(List<String> l, Field filed) {
		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);
	}

	public List<T> getAllList(List<String> l, Field field) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), field);
	}

	public List<T> getAllList() {
		return selectFromHQL("from " + getEntityName());
	}

	public void delete(int id) {
		exec(SqlUtils.initRealDelEntityHql(getEntityName()), new Field().addInt(id));
	}
}

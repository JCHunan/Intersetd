package com.jason.manager.base.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.jason.manager.utils.PageBean;
import com.jason.manager.utils.ProjectException;
import com.jason.manager.xutil.XDBUtil;

public class BaseDao<T extends Serializable, PK extends Serializable> {
	
	private final Logger logger = Logger.getLogger(BaseDao.class);
	private static final DateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd");

	protected SqlMapClientTemplate sqlMapClientTemplate;
	protected SqlMapClientTemplate secondSqlMapClientTemplate;

	public Object insert(String sta, Object o) {		
		return sqlMapClientTemplate.insert(sta, o);
	}

	public Object save(String sta, Object o) {
		return sqlMapClientTemplate.insert(sta, o);
	}

	public int update(String sta, Object o) {
		return sqlMapClientTemplate.update(sta, o);
		
	}

	public int delete(String sta, Object o) {
		return sqlMapClientTemplate.delete(sta, o);
	}

	@SuppressWarnings("unchecked")
	public <R> List<R> queryForList(String sta, Object o) {
		return (List<R>)secondSqlMapClientTemplate.queryForList(sta, o);
	}

	@SuppressWarnings("unchecked")
	public <R> List<R> queryForList(String sta) {
		return (List<R>)secondSqlMapClientTemplate.queryForList(sta);
	}

	public Object queryForObject(String sta, Object o) {
		return secondSqlMapClientTemplate.queryForObject(sta, o);
	}

	public Object queryForObject(String sta) {
		return secondSqlMapClientTemplate.queryForObject(sta);
	}

	public int update(String sta) {
		return sqlMapClientTemplate.update(sta);
	}

	public List<T> queryForEntity(String statement, Object obj) {
		List<T> list = secondSqlMapClientTemplate.queryForList(statement, obj);
		return list;
	}
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
	public void setSecondSqlMapClientTemplate(SqlMapClientTemplate secondSqlMapClientTemplate) {
		this.secondSqlMapClientTemplate = secondSqlMapClientTemplate;
	}

	public SqlMapClientTemplate getSecondSqlMapClientTemplate() {
		return secondSqlMapClientTemplate;
	}

	/** 共用的都放在这里 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <R  extends Serializable> PageBean<R> findPageList(PageBean<R> pagebean, String statement, Map map) {
		int count = (Integer) sqlMapClientTemplate.queryForObject(statement + "_count", map);
		pagebean.setTotalResults(count);
		pagebean.setObjList(null);
		if (map == null)
			map = new HashMap();
		if(pagebean.getStartResult()>=count){
			pagebean.setStartResult(pagebean.getStartResult()-count);
			return pagebean;
		}
		map.put("pageStart", pagebean.getStartResult());
		map.put("pageSize", pagebean.getPageSize());
		List<R> list = sqlMapClientTemplate.queryForList(statement, map);
		pagebean.setObjList(list);
		return pagebean;
	}
	
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}
	
	protected Connection getConnection(){
		try {
			return secondSqlMapClientTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			 throw new ProjectException(e);
		}
	}
	
	protected boolean isExists(String sql, Object... params){
		Connection cnn = null;
		try{
			cnn = getConnection();
			return XDBUtil.isExists(cnn, sql, params);
		}finally{
			if(cnn != null)
				try {
					cnn.close();
				} catch (SQLException e) {
					throw new ProjectException(e);
				}
		}
	}
	
//	protected int executeSqlUpdate(String sql, Object...params){
//		Connection cnn = null;
//		try{
//			cnn = getConnection();
//			return XDBUtil.executeSQLUpdate(cnn, sql, params);
//		}finally{
//			if(cnn != null)
//				try {
//					cnn.close();
//				} catch (SQLException e) {
//					throw new ProjectException(e);
//				}
//		}
//		
//	}
	
	/**
	 * 执行查询，返回list
	 * @param sql
	 * @param useBeanName
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> executeSQLQuery(String sql, boolean useBeanName, Object... params){
		Connection cnn = null;
		try{
			cnn = getConnection();
			return XDBUtil.executeSQLQuery(cnn, sql, useBeanName, params);
		}finally{
			if(cnn != null)
				try {
					cnn.close();
				} catch (SQLException e) {
					throw new ProjectException(e);
				}
		}
	}
	

	
	protected List<String> getDayList(String startDate, String endDate) {
		Date startDay;
		Date endDay;
		try {
			startDay = FORMATTER.parse(startDate);
			endDay = FORMATTER.parse(endDate);
		} catch (Exception e) {
			return new ArrayList<String>();
		}

		return getDayList(startDay,endDay);
	}
	
	protected List<String> getDayList(Date startDate, Date endDate) {
		List<String> dayList = new ArrayList<String>();
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		startDay.setTime(startDate);
		endDay.setTime(endDate);

		if (startDay.compareTo(endDay) > 0) {
			return dayList;
		}
		
		dayList.add(FORMATTER.format(startDate));
		if (startDay.compareTo(endDay) == 0) {
			return dayList;
		}

		
		Calendar currentPrintDay = startDay;
		while (true) {
			currentPrintDay.add(Calendar.DATE, 1);
			if (currentPrintDay.compareTo(endDay) == 0) {
				break;
			}
			dayList.add(FORMATTER.format(currentPrintDay.getTime()));
		}
		dayList.add(FORMATTER.format(endDate));
		return dayList;
	}
}
package com.jason.manager.xutil;


import java.sql.*;
import java.util.*;



  
/**
 * 数据库操作工具类
 * 
 */
public class XDBUtil {
	/**
     * 将传入的无分页的SQL语句组装成有分页的SQL语句，
     * 返回的语句可以当成实际的数据库表直接使用
     * 返回的表中将在原SQL语句基础上增加新的列ROWNO，代表行的编号
     * 
     * 注意：为了提高性能，需要在内层嵌套的SQL语句中使用，不能在最终的查询结果上使用
     * 
     * @param sql 原始无分页的SQL语句
     * @param start 分页起始行号,从0开始
     * @param limit 分页大小
     * @return 分页的SQL语句，可以当表使用
     */
    public static String createOraPageTable(String sql, Integer start, Integer limit){
    	Integer end = new Integer(start.intValue() + limit.intValue());
    	if(end.intValue() > 0){    	
    		Object[] objs = new Object[3];
    		objs[0] = sql;
    		objs[1] = end;
    		objs[2] = start;
    		return String.format(
    				"select * from "+ 
    				"  (select table_alias_.*, rownum rowno from "+ 
    				"      (%s) table_alias_ where rownum <= %d) "+
    				"where rowno >%d ", objs);
    		
    	}else{   
    		Object[] objs = new Object[2];
    		objs[0] = sql;
    		objs[1] = start;
    		return String.format(
    				"select * from "+ 
    				"  (select table_alias_.*, rownum rowno from "+ 
    				"      (%s) table_alias_) "+
    				"where rowno >%d ", objs);
    	}    		
    }
    
    /**
     * 创建mysql数据库的分页语句
     * 
     * @param sql
     * @param start 从0开始
     * @param limit
     * @return
     */
    public static String createMySQLPageTable(String sql, Integer start, Integer limit){
    	return String.format(
    			"select * from (%s) _table_alias_ limit %d, %d",
    			sql, start, limit);    	 	
    }
    
    /**
     * 对传入的sql语句进行重构，变成查询记录数的SQL语句
     * 返回的SQL语句可以直接执行，进而获取记录集中的记录数
     * 
     * 如：传入SQL语句为 select id, name from table1
     * 转换后的SQL语句为 select count(*) from table1
     * 
     * @param sql 原始SQL语句
     * @param roundMode 是否使用在原始SQL语句外层包含查询数量的语句
     * @return 重构后查询记录数的SQL语句
     */
    public static String createSizeSQL(String sql, boolean...roundMode){    
    	boolean nRoundMode = false;
    	if(roundMode.length == 1){
    		nRoundMode = roundMode[0];
    	}
    	
    	if(nRoundMode){
    		return String.format("select count(1) from (%s) __size_table", sql);
    	}else{
    		int selectPos = sql.toLowerCase().indexOf("select");
        	int fromPos = sql.toLowerCase().indexOf("from");
        	
        	if(selectPos == -1 || fromPos == -1)
        		throw new RuntimeException("无效的SQL语句，未发现select, from关键字");
        	
        	String sizeSql = sql.substring(0, selectPos + 6) + " count(1) " + 
        	                 sql.substring(fromPos, sql.length());
        	return sizeSql;
    	}
    }
    
    /**
	 * 与toBeanField相反。将写字母转换成"_"
	 * @param beanField 包含大些字母的字段名
	 * @return String 全大写的数据库字段名
	 */
	public static String toDbField(String dbField){
		//assertIt(dbField.trim().length() > 0);
		StringBuffer result = new StringBuffer(30);
		char[] chars = dbField.toCharArray();
		for(int i=0 ;i<chars.length ;i++) {
            if(Character.isUpperCase(chars[i])) {
            	result.append("_");
            	result.append(chars[i]);
            }else {
            	result.append(chars[i]);
            }
        }
		return result.toString().toUpperCase();
	}
	
	/**
	 * 将以下划线分隔的多个全部为大写字母的单词转换为JAVA BEAN的命名格式的单词
	 * 
	 * 如：TOWN_NAME将转换为townName
	 * 
	 * @param field 大写的由多个单词组成的字段名
	 * @return JAVA BEAN格式的单词
	 */
	public static String toBeanField(String field){

				
		field = field.toLowerCase();
		String[] words = field.split("_");
		String result = words[0];
		String word;
		for(int i=1; i<words.length; i++){			
			word = words[i];			
			result += word.substring(0, 1).toUpperCase();
			if(word.length() > 1){
				result += word.substring(1, word.length());
			}
		}
		return result;
	}
	
	/**
	 * 将以下划线分隔的多个全部为大写字母的单词转换为JAVA BEAN的命名格式的单词
	 * 
	 * 如：TOWN_NAME将转换为townName
	 * 
	 * @param field 大写的由多个单词组成的字段名
	 * @return JAVA BEAN格式的单词
	 */
	public static String[] toBeanField(String[] fields){
		String[] result = new String[fields.length];
		for(int i=0; i<fields.length; i++){
			result[i] = toBeanField(fields[i]);
		}
		return result;
	}

	/**
	 * 执行批量SQL语句
	 * 
	 * @param sqls SQL语句集合
	 * @return 每条语句执行后更新的行数
	 */
	public static int[] executeBatchBySQL(Connection cnn, String[] sqls){		
		Statement stmt = null;		
		
		try{			
			stmt = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
												 ResultSet.CONCUR_UPDATABLE);			
			for(int i=0; i<sqls.length; i++){
				stmt.addBatch(sqls[i]);
			}
			return stmt.executeBatch();
		}catch(Exception e){
			throw new UtilException(e);
		 		
		}finally{			
			try{
				stmt.close();
				stmt = null;
				cnn=null;
			}catch(Exception e){}
		}
	}
	     
	/**
	 * 执行SQL更新
	 * @param sql 待执行的SQL更新语句
	 * @return 执行更新语句后影响的记录数
	 */
	public static int executeSQLUpdate(Connection cnn, String sql, Object... params) {
		PreparedStatement pstmt = null;
		 
		try{
			pstmt = cnn.prepareStatement(sql);
			
			if(params.length > 0)
				XDBUtil.prepareParams(pstmt, params);
			
			return pstmt.executeUpdate();
			
//			stmt = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
//												 ResultSet.CONCUR_UPDATABLE);		
//			
//			
//			return stmt.executeUpdate(sql);		
		}catch(Exception e){
			throw new UtilException(e);		 	
		}finally{			
			try{
				pstmt.close();
				pstmt=null;				
			}catch(Exception e){
				
			}
		}
	}
	
	public static int executeSQLUpdate(Connection cnn, 
			Map<String, Object> properties, String tableName, String primaryKey, String primaryValue){
		
		StringBuilder builder = new StringBuilder();
		builder.append("update ")
		       .append(tableName)
		       .append(" set ");
		
		boolean first = true;
		String key;
		List<Object> params = new ArrayList<Object>();
		for(Iterator<String> iter=properties.keySet().iterator(); iter.hasNext();){
			if(!first)
				builder.append(",");
			key = iter.next();
			builder.append(key + "=?");
			params.add(properties.get(key));
			first = false;
		}
		
		builder.append(" where ")
			   .append(primaryKey)
			   .append("=?");
		params.add(primaryValue);
		System.out.println(builder.toString());
		
		return executeSQLUpdate(cnn, builder.toString(), params.toArray());
//		PreparedStatement pstmt = null;
//		try{
//			pstmt = cnn.prepareStatement(builder.toString());
//			XDBUtil.prepareParams(pstmt, params);
//			return pstmt.executeUpdate();
//		}catch(Exception e){
//			throw new UtilException(e);		
//		}finally{			
//			try{
//				pstmt.close();
//				pstmt=null;				
//			}catch(Exception e){
//				
//			}
//		}
	}
	
	public static int executeSQLInsert(Connection cnn, String sql, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = cnn.prepareStatement(sql);
			
			if(params.length > 0)
				XDBUtil.prepareParams(pstmt, params);
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();			
			if(rs.next())
				return rs.getInt(1);
			else				
				return 0;
		}catch(Exception e){
			throw new UtilException(e);		 	
		}finally{			
			try{
				pstmt.close();
				pstmt=null;	
				rs.close();
			}catch(Exception e){
				
			}
		}
	}
	
	/**
	 * 读取单个整数值，当返回记录多于1时，将读取第一条记录的第一列的值
	 * 
	 * @param sql 一条返回一条记录单个值的SQL语句
	 * @return 读取第一条记录的第一列的值,返回值类型为int
	 */
	public static Integer readIntegerBySQL(Connection cnn, String sql) {
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, true);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return ConvertUtil.str2Integer(rec.get(key), null);
	}
	
	public static Integer readIntegerBySQL(Connection cnn, String sql, Object... param) {
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, false, param);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return ConvertUtil.str2Integer(rec.get(key), null);
	}
	
	public static int readIntBySQL(Connection cnn, String sql){
		Integer v = readIntegerBySQL(cnn, sql);
		return v == null ? 0 : v;
	}
	
	public static int readIntBySQL(Connection cnn, String sql, Object... param){
		Integer v = readIntegerBySQL(cnn, sql, param);
		return v == null ? 0 : v;
	}
	
	
	
	public static List<Integer> readIntegerListBySQL(Connection cnn, String sql, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{			
			pstmt = cnn.prepareStatement(sql);			
			pstmt = XDBUtil.prepareParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			
			List<Integer> list = new ArrayList<Integer>();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			return list;
			
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读取单个字符串值，当返回记录多于1时，将读取第一条记录的第一列的值
	 * 
	 * @param sql 一条返回一条记录单个值的SQL语句
	 * @return 读取第一条记录的第一列的值,返回值类型为string
	 */
	public static String readStringBySQL(Connection cnn, final String sql){
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, true);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return rec.get(key);
	}
	
	public static String readStringBySQL(Connection cnn, final String sql, Object... param){
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, false, param);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return rec.get(key);
	}
	
	
	
	
	
	public static long readlongBySQL(Connection cnn, String sql) {
		Long v = readLongBySQL(cnn, sql);
		return v == null ? 0L : v;
	}
	
	public static long readlongBySQL(Connection cnn, String sql, Object... param) {
		 Long v = readLongBySQL(cnn, sql, param);
		 return v == null ? 0L : v;
	}
	
	/**
	 * 读取单个整数值，当返回记录多于1时，将读取第一条记录的第一列的值
	 * 
	 * @param sql 一条返回一条记录单个值的SQL语句
	 * @return 读取第一条记录的第一列的值，返回值类型为long 
	 */
	public static Long readLongBySQL(Connection cnn, String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try{			
			stmt = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				       ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			
			if(!rs.first()){				
				return null;
			}else
				return rs.getLong(1);			
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				rs.close();
				rs=null;
				stmt.close();
				stmt=null;
				cnn = null;
			}catch(Exception e){}
		}	 		
	}
	
	public static Long readLongBySQL(Connection cnn, String sql, Object... param) {
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, false, param);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return ConvertUtil.str2Long(rec.get(key), null);
	}
	
	public static double readdoubleBySQL(Connection cnn, String sql){
		Double v = readDoubleBySQL(cnn, sql);
		return v == null ? 0 : v;
	}
	
	public static double readdoubleBySQL(Connection cnn, String sql, Object... param){
		Double v = readDoubleBySQL(cnn, sql, param);
		return v == null ? 0 : v;
	}
	
	public static Double readDoubleBySQL(Connection cnn, String sql) {
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, false);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return ConvertUtil.str2Double(rec.get(key), null);
	}
	
	public static Double readDoubleBySQL(Connection cnn, String sql, Object... param) {
		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, false, param);
		if(recs.size() == 0)
			return null;
		
		Map<String, String> rec = recs.get(0);
		String key = (String)rec.keySet().iterator().next();
		return ConvertUtil.str2Double(rec.get(key), null);
	}
	
	/**
	 * 获取JDBC记录集的列名
	 * 
	 * @param rs JDBC记录集
	 * @param useBeanName 是否使用JAVA BEAN的命名格式返回字段名
	 *        JAVA BEAN的命名格式为：townName
	 * @return 记录集中所有的字段名
	 */
	public static String[] getColumnNames(ResultSet rs, boolean useBeanName){
		try{
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();
			String[] colNames = new String[colCount];
			
			for(int i=0; i<colCount; i++){
				String col = meta.getColumnLabel(i+1);
				
				if(useBeanName){
					col = toBeanField(col);
				}
				colNames[i] = col;
			}
			
			return colNames;
		}catch(Exception e){
			throw new UtilException(e);
		}		
	}
	
	public static String[] getColumnNames(Connection cnn, String tableName){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = cnn.prepareStatement("select * from " + tableName);
			rs = pstmt.executeQuery();
			return getColumnNames(rs, false);
			
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
		}
	}
	
	public static int[] getColumnTypes(ResultSet rs){
		try{
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();						
			int[] columnTypes = new int[colCount];
			for(int i=0; i<colCount; i++){				
				columnTypes[i] = meta.getColumnType(i+1);		
			}
			
			return columnTypes;
		}catch(Exception e){
			throw new UtilException(e);
		}		
	} 
	
	public static int[] getColumnTypes(Connection cnn, String tableName){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = cnn.prepareStatement("select * from " + tableName);
			rs = pstmt.executeQuery();
			return getColumnTypes(rs);
			
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 获取一个表的所有主键列名
	 * @param cnn
	 * @param tableName
	 * @return
	 */
	public static List<String> getPrimaryColumnNames(Connection cnn, String tableName){
		
		ResultSet rs = null;
		try{
			DatabaseMetaData m = cnn.getMetaData();
			rs = m.getPrimaryKeys(null, null, "t_role");
			
			List<String> list = new ArrayList<String>();
			while(rs.next()){			
				list.add(rs.getString("COLUMN_NAME"));
			}
			return list;
		
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			if(rs != null)
				try{
					rs.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 执行查询语句，将第一条记录已map形式返回
	 * 
	 * @param sql 查询语句
	 * @param cnn 数据库连接
	 * @param useBeanField 是否使用java bean形式命名字段名
	 * @return 第一条数据库记录
	 */
//	public static Map<String, String> readFirstRecord(Connection cnn, String sql, boolean useBeanName){		 
//		List<Map<String, String>> recs = executeSQLQuery(cnn, sql, useBeanName);
//		if(recs.size() == 0)
//			return null;
//		
//		return recs.get(0);
//	}	
	
	/**
	 * 查询查询结果集中第一条记录
	 * 
	 * @param cnn
	 * @param sql
	 * @param useBeanField
	 * @param param
	 * @return 数据结果集中第一条记录，如不存在返回null
	 */
	public static Map<String, String> readFirstRecord(Connection cnn, String sql, boolean useBeanName, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{			
			pstmt = cnn.prepareStatement(sql);
			if(params.length > 0){
				XDBUtil.prepareParams(pstmt, params);
			}
						
			rs = pstmt.executeQuery();	
			if(!rs.first())
				return null;
			
			return executeSQLQuery(rs, useBeanName, 0);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 查询查询结果集中最后一条记录
	 * 
	 * @param cnn
	 * @param sql
	 * @param useBeanField
	 * @param param
	 * @return 数据结果集中第一条记录，如不存在返回null
	 */
	public static Map<String, String> readLastRecord(Connection cnn, String sql, boolean useBeanName, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{			
			pstmt = cnn.prepareStatement(sql);
			if(params.length > 0){
				XDBUtil.prepareParams(pstmt, params);
			}
						
			rs = pstmt.executeQuery();	
			rs.last();
			return executeSQLQuery(rs, useBeanName, 0);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 根据SQL语句查询记录集合，返回字段名大小写可以指定
	 * 
	 * 执行一条SQL语句，返回一个结果集，结果集由存储在List中，其中每个元素是一个Map型对象
	 * 每个Map是一条记录，Map的KEY值是参数实际列名(大写)相应位置的字符串，如果转换成JSON时，
	 * Map可以作为一个动态JAVA BEAN使用，并且使用相同的方式进行处理
	 * 
	 * 此方法返回结果集中的所有记录，因此如果想进行分页查询，必须在SQL语句中加入分页条件
	 * 
	 * @param sql 执行查询的SQL语句
	 * @param colNames 返回结果集中显示的列名
	 * @return Map对象的集合List，即结果集
	 */	
	public static List<Map<String, String>> executeSQLQuery(Connection cnn, String sql, boolean useBeanName){		 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{			
			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);	
			
			return executeSQLQuery(rs, useBeanName);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 执行SQL语句
	 * 
	 * @param cnn
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, String>> executeSQLQuery(Connection cnn, String sql, boolean useBeanName, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{			
			pstmt = cnn.prepareStatement(sql);
			
//			int idx = 1;
//			for(Object p : params){
//				if(p == null){
//					pstmt.setNull(idx, Types.VARCHAR);
//									
//				}else if(p instanceof Integer){
//					pstmt.setInt(idx, (Integer)p);
//					
//				}else if(p instanceof Long){
//					pstmt.setLong(idx, (Long)p);
//					
//				}else if(p instanceof String){
//					pstmt.setString(idx, (String)p);
//				}
//				++idx;
//			}
			pstmt = XDBUtil.prepareParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			return executeSQLQuery(rs, useBeanName);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 从rs中读取记录
	 * 
	 * @param rs
	 * @param useBeanField
	 * @return
	 */
	public static List<Map<String, String>> executeSQLQuery(ResultSet rs, boolean useBeanName){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try{			
		 		
			String[] colNames = getColumnNames(rs, useBeanName);			
			while(rs.next()){
				Map<String, String> record = new HashMap<String, String>();
				for(int i=0; i<colNames.length; i++){
					String colName =colNames[i];					
					String colValue = rs.getString(i+1);
					record.put(colName, colValue);
				}
				result.add(record);
			}
							
			return result;
		}catch(Exception e){
			throw new UtilException(e);
		} 
	}
	
	/**
	 * 从rs中查询指定行的记录
	 * 
	 * @param rs
	 * @param useBeanField
	 * @param row 要读取的记录在记录集中的位置，如果 <= 0，直接读取当前位置的记录
	 * @return
	 */
	public static Map<String, String> executeSQLQuery(ResultSet rs, boolean useBeanName, int row){
		
		try{			
			if(row > 0)
				rs.absolute(row);
			
			if(rs.isBeforeFirst() || rs.isAfterLast())
				return null;
		 	
		 			 	 
		 	String[] colNames = getColumnNames(rs, useBeanName);		
		 	Map<String, String> record = new HashMap<String, String>();
			for(int i=0; i<colNames.length; i++){
				String colName =colNames[i];					
				String colValue = rs.getString(i+1);
				record.put(colName, colValue);
			}	
		 	
			return record;
			 
		}catch(Exception e){
			throw new UtilException(e);
		} 
	}
	
	
	/**
	 * 执行SQL查询，返回实际数据类型的结果集。
	 * 返回结果是一个map组成的集合，集合中的每一个map存储一条记录的数据，
	 *     这个map的键值是查询字段的名称，如果指定useBeanName为true，字段
	 * 名使用实际字段名的JavaBean形式。
	 *     map的值类型与数据库结果集中对应字段的类型一致。	 
	 * 
	 * @param cnn 执行SQL查询的数据库连接对象
	 * @param sql 执行查询的SQL语句
	 * @param useBeanName true结果集的map键值使用查询字段的JavaBean形式, 否者使用与查询语句中完全一直的名称
	 * @return 结果集列表
	 */
	public static List<Map<String, Object>> find(Connection cnn, String sql, boolean useBeanName){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{			
			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);	
			
			return find(rs, useBeanName);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 执行SQL查询，返回实际数据类型的结果集。可以在SQL语句中使用?指定查询条件，
	 * 底层将使用PreparedStatement执行带参数的查询语句。
	 * 
	 * 详情参考find(Connection cnn, String sql, boolean useBeanName)
	 * 
	 * @param cnn 执行SQL查询的数据库连接对象
	 * @param sql 执行查询的SQL语句
	 * @param useBeanName true结果集的map键值使用查询字段的JavaBean形式, 否者使用与查询语句中完全一直的名称
	 * @return 结果集列表
	 */
	public static List<Map<String, Object>> find(Connection cnn, String sql, boolean useBeanName, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{			
			pstmt = cnn.prepareStatement(sql);
			
			int idx = 1;
			for(Object p : params){
				if(p == null){
					pstmt.setNull(idx, Types.VARCHAR);
									
				}else if(p instanceof Integer){
					pstmt.setInt(idx, (Integer)p);
					
				}else if(p instanceof Long){
					pstmt.setLong(idx, (Long)p);
					
				}else if(p instanceof String){
					pstmt.setString(idx, (String)p);
				}
				++idx;
			}
			
			rs = pstmt.executeQuery();
			return find(rs, useBeanName);
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs=null;
				}
				if(pstmt != null){
					pstmt.close();
					pstmt=null;
				}								
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 从已经打开的ResultSet中读取记录值。
	 * 
	 * 详情参考find(Connection cnn, String sql, boolean useBeanName)
	 * 
	 * @param cnn 执行SQL查询的数据库连接对象
	 * @param sql 执行查询的SQL语句
	 * @param useBeanName true结果集的map键值使用查询字段的JavaBean形式, 否者使用与查询语句中完全一直的名称
	 * @return 结果集列表
	 */
	public static List<Map<String, Object>> find(ResultSet rs, boolean useBeanName){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{			
		 	ResultSetMetaData meta = rs.getMetaData();
			String[] colNames = getColumnNames(rs, useBeanName);			
			
			int idx = 1;
			while(rs.next()){
				Map<String, Object> record = new HashMap<String, Object>();
				for(int i=0; i<colNames.length; i++){
					String name =colNames[i];									
					Object value = null;
					
					int colType = meta.getColumnType(idx++);
					if(colType == Types.INTEGER){
						value = rs.getInt(i);
						
					}else if(colType == Types.BIGINT){
						value = rs.getLong(i);	
						
					}else if(colType == Types.VARCHAR){
						value = rs.getString(i);
						
					}else if(colType == Types.TIMESTAMP){
						value = rs.getTimestamp(i);
					}
					
					record.put(name, value);
				}
				result.add(record);
			}
						
			return result;
		}catch(Exception e){
			throw new UtilException(e);
		} 
	}
	
	/**
	 * 查询记录是否存在
	 * 
	 * @param cnn 数据库连接
	 * @param sql 查询语句
	 * @param params SQL语句中的参数
	 * @return true查询语句返回了有效的记录，false未查询到任何记录
	 */
	public static boolean isExists(Connection cnn, String sql, Object... params){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = cnn.prepareStatement(sql);
			prepareParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			return rs.first();
			
		}catch(Exception e){
			throw new UtilException(e);
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
			}catch(Exception e){
				throw new UtilException("关闭数据库资源失败", e);
			}
		}
	}
	
	private static PreparedStatement prepareParams(PreparedStatement pstmt,
			Object... params) throws SQLException {
		int idx = 1;
		for (Object p : params) {
			if (p == null) {
				pstmt.setNull(idx, Types.VARCHAR);
			} else if (p instanceof Integer) {
				pstmt.setInt(idx, (Integer) p);
				
			} else if (p instanceof Long) {
				pstmt.setLong(idx, (Long) p);
				
			} else if (p instanceof String) {
				pstmt.setString(idx, (String) p);
			
			
			
			} else if (p instanceof Byte) {
				pstmt.setByte(idx, (Byte) p);
				
			} else if(p instanceof Double){
				pstmt.setDouble(idx, (Double)p);
				
			} else if (p instanceof Integer[]) {
				Integer[] s = (Integer[]) p;
				for (int i = 0; i < s.length; i++) {
					pstmt.setInt(idx, s[i]);
					++idx;
				}
				--idx;			
				
			} else if (p instanceof Long[]) {
				Long[] s = (Long[]) p;
				for (int i = 0; i < s.length; i++) {
					pstmt.setLong(idx, s[i]);
					++idx;
				}
				--idx;
			} else if (p instanceof String[]) {
				String[] s = (String[]) p;
				for (int i = 0; i < s.length; i++) {
					pstmt.setString(idx, s[i]);
					++idx;
				}
				--idx;
			} else if (p instanceof Byte[]) {
				Byte[] s = (Byte[]) p;
				for (int i = 0; i < s.length; i++) {
					pstmt.setByte(idx, s[i]);
					++idx;
				}
				--idx;
			} else if (p instanceof int[]) {
				int[] s = (int[]) p;
				for (int i = 0; i < s.length; i++) {
					pstmt.setInt(idx, s[i]);
					++idx;
				}
				--idx;
			} else {
				--idx;
			}
			++idx;
		}

		return pstmt;
	}
}

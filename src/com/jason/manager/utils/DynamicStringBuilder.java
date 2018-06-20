package com.jason.manager.utils;

import com.jason.manager.xutil.ConvertUtil;

/**
 * 字符串组装器，StringBuilder代理
 * 
 */
public class DynamicStringBuilder{
	/**
	 * StringBuilder代理对象
	 */
	private StringBuilder builder = null;
		 
	public DynamicStringBuilder(int capacity){
		builder = new StringBuilder(capacity);
	}
	
	/**
	 * 构造函数
	 * @param str
	 */
	public DynamicStringBuilder(String... str){
		builder = new StringBuilder();
		this.append((Object[])str);
	}
	
	/**
	 * 添加字符串
	 * 
	 * @param str
	 * @return
	 */
	public DynamicStringBuilder append(Object... str){
		for(int i=0; i<str.length; i++){
			builder.append(str[i]);
		}
		
		return this;
	}
	
	public DynamicStringBuilder appendFormat(String str, Object... params){
		this.builder.append(String.format(str, params));
		return this;
	}
	
	/**
	 * 将字符串中所有占位符:p替换成参数的值，替换过程按照strs中元素的先后
	 * 顺序依次替换字符串中对应位置的:p占位符
	 * 
	 * 例如：
	 * 字符串
	 * "select * from :p where :p"
	 * 
	 * 使用如下2个字符串替换
	 * table1, age>2
	 * 
	 * 最后的字符串将变成
	 * select * from table1 where age>2
	 * 
	 *  
	 * @param strs
	 */
	public void setParam(String... strs){
		for(String s : strs){
			int idx = this.builder.indexOf(":p");
			if(idx == -1)
				break;
			
			builder.replace(idx, idx + 2, s);
		}
	}
	
	/**
	 * 替换指定名称的占位符，参数strs需要按照key, value的先后顺序定义
	 * 如： :tableName table1 :where age>2
	 * 
	 * 例如：
	 * 字符串
	 * "select * from :tableName where :where"
	 * 
	 * 使用如下2个字符串替换
	 * :tableName table1, :where age>2
	 * 
	 * 最后的字符串将变成
	 * select * from table1 where age>2 
	 * 
	 * 占位符中的(:)不是必须的，只是为了不与字符串中其它字符冲突一种格式
	 * 
	 * @param strs
	 */
	public void setNamedParam(String... strs){
		for(int i=0; i<strs.length; i=i+2){
			String name = strs[i];			
			if(strs.length <= i){
				return;
			}
			String value = strs[i+1];
			
			int idx = this.builder.indexOf(name);
			if(idx == -1)
				return;
			this.builder.replace(idx, idx + name.length(), value);
		}
	}
	
	
	public void setParam(Object[] params){
		for(int i=0; i<params.length; i++){
			int idx = this.builder.indexOf(":p");
			if(idx == -1){
				break;
			}
			
			String p = ConvertUtil.obj2str(params[i]);
			builder.replace(idx, idx + 2, p);
		}		 
	}
	
	public void setParam(String paramName, String paramValue){
		int idx = this.builder.indexOf(paramName);
		if(idx == -1){
			return;
		}
		this.builder.replace(idx, idx + paramName.length(), paramValue);
	}
	
	
	public String toString(){
		return this.builder.toString();
	}
	
	public void insert(int offset, String v){
		this.builder.insert(offset, v);		
	}
	
	public int length(){
		return this.builder.length();
	}
	
	public String substring(int start){		
		return this.builder.substring(start);
	}
	
	public DynamicStringBuilder delete(int start, int end){
		this.builder.delete(start, end);
		return this;
	}
	
	public String substring(int start, int end){
		return this.builder.substring(start, end);
	}
	
	public static void main(String[] args){
		DynamicStringBuilder dsb = new DynamicStringBuilder(
				"select :p from :p where :p"
		);
		dsb.setParam(new Object[]{1, "hello", "ed_page"});
		System.out.println(dsb.toString());
		 
	}
}

package com.jason.manager.admin.dao;

import java.util.List;
import java.util.Map;

import com.jason.manager.admin.entity.IAdmin;


/**
 * 管理DAO实现接口定义
 * @author HDjc
 *
 */
public interface IUserDao 
{
	/**
	 * 插入一个用户
	 */
	public IAdmin findUserByName(String username);
	
	public List<Map> getUrlPower();
	
}

package com.jason.manager.admin.dao;

import java.util.List;

import com.jason.manager.resource.entity.TPlatRole;
import com.jason.manager.resource.entity.TPower;

public interface RoleDao 
{
	public List<TPlatRole> getAllPlatRole();

	public List<Integer> getRolePower(Integer roleId);
	
	public List<TPower> getMenuSource(int adminId,String agent);
}

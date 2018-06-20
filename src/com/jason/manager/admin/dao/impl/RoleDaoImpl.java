package com.jason.manager.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jason.manager.admin.dao.RoleDao;
import com.jason.manager.base.dao.BaseDao;
import com.jason.manager.resource.entity.TPlatRole;
import com.jason.manager.resource.entity.TPower;

/**
 * 角色DAO实现类
 */
@Component("roleDao")
public class RoleDaoImpl extends BaseDao<TPlatRole, Long> implements RoleDao {

	@Override
	public List<TPlatRole> getAllPlatRole() {
		return queryForList("i_platrole.getAllPlatRole");
	}

	@Override
	public List<Integer> getRolePower(Integer roleId) {
		return queryForList("i_platrole.getRolePower", roleId);
	}
	
	@Override
    public List<TPower> getMenuSource(int adminId,String agent){
	   return queryForList("i_power.getMenuSource", adminId);	
		
	}
	
	
}

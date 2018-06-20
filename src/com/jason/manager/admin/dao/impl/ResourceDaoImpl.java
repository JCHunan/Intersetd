package com.jason.manager.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jason.manager.admin.dao.ResourceDao;
import com.jason.manager.admin.entity.IResource;
import com.jason.manager.base.dao.BaseDao;
import com.jason.manager.resource.entity.TPower;

/**
 * 权限资源DAO实现类
 */
@Component("resourceDao")
public class ResourceDaoImpl extends BaseDao<IResource, Long> implements ResourceDao {

	@Override
	public List<TPower> getPowerList() {
		return queryForList("i_resource.selectAllPower");
	}

	@Override
	public List<IResource> getResourceList() {
		return queryForList("i_resource.selectAll");
	}

}

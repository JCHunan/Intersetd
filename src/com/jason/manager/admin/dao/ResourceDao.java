package com.jason.manager.admin.dao;

import java.util.List;

import com.jason.manager.admin.entity.IResource;
import com.jason.manager.resource.entity.TPower;

/**
 * 权限资源DAO实现接口定义
 */
public interface ResourceDao {


	/**
	 * 获取权限资源列表
	 * @return List<TResource>
	 */
	public List<IResource> getResourceList();




	public List<TPower> getPowerList();
}

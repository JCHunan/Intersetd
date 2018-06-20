package com.jason.manager.resource.entity;

import java.io.Serializable;
import java.util.List;

import com.jason.manager.admin.entity.IResource;

public class TPlatRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	
	private String roleName;
	
	private String roleMark;
	
	private Integer roleType;

	private List<Integer> rolePower;
	
	/** 资源列表 */
    private List<IResource> resources;
	
	public Integer getId() {
		return roleId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMark() {
		return roleMark;
	}

	public void setRoleMark(String roleMark) {
		this.roleMark = roleMark;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public List<Integer> getRolePower() {
		return rolePower;
	}

	public void setRolePower(List<Integer> rolePower) {
		this.rolePower = rolePower;
	}

	public List<IResource> getResources() {
		return resources;
	}

	public void setResources(List<IResource> resources) {
		this.resources = resources;
	}
}

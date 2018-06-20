package com.jason.manager.resource.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限菜单项
 */
public class MenuItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//自身按妞
	private TPower power;
	//自按钮列表
	private List<MenuItem> subMenus = new ArrayList<MenuItem>();
	

	public List<MenuItem> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuItem> subMenus) {
		this.subMenus = subMenus;
	}

	public TPower getPower() {
		return power;
	}

	public void setPower(TPower power) {
		this.power = power;
	}
	
}

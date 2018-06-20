package com.jason.manager.admin.entity;

import java.io.Serializable;

/**
 * 权限资源类
 */
public class IResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 权限资源ID */
	private Integer iResourceid;

	/** 权限名称 */
	private String cPermisname;

	/** 父权限ID */
	private Integer iParentid;

	/** 权限类型 */
	private String cPermistype;

	/** 权限备注 */
	private String cPermisremark;

	/** 排序字段 */
	private Integer iSort;

	/**  资源名称(URL或方法) */
	private String cResource;

	private String TUrl;
	/** 层次 */
	private Integer iLevel;
	
	/** 菜单类型(1菜单，2按钮)  */
	private Integer iMenutype;

	/** 权限类型(目前没用) */
	private Integer iUsertype;
	
	private Integer iType;
	
	private Integer company;
	
	public Integer getId()
	{
		return iResourceid;
	}

	public Integer getiResourceid() {
		return iResourceid;
	}

	public void setiResourceid(Integer iResourceid) {
		this.iResourceid = iResourceid;
	}

	public String getcPermisname() {
		return cPermisname;
	}

	public void setcPermisname(String cPermisname) {
		this.cPermisname = cPermisname;
	}

	public Integer getiParentid() {
		return iParentid;
	}

	public void setiParentid(Integer iParentid) {
		this.iParentid = iParentid;
	}

	public String getcPermistype() {
		return cPermistype;
	}

	public void setcPermistype(String cPermistype) {
		this.cPermistype = cPermistype;
	}

	public String getcPermisremark() {
		return cPermisremark;
	}

	public void setcPermisremark(String cPermisremark) {
		this.cPermisremark = cPermisremark;
	}

	public Integer getiSort() {
		return iSort;
	}

	public void setiSort(Integer iSort) {
		this.iSort = iSort;
	}

	public String getcResource() {
		return cResource;
	}

	public void setcResource(String cResource) {
		this.cResource = cResource;
	}

	public Integer getiLevel() {
		return iLevel;
	}

	public void setiLevel(Integer iLevel) {
		this.iLevel = iLevel;
	}

	public Integer getiMenutype() {
		return iMenutype;
	}

	public void setiMenutype(Integer iMenutype) {
		this.iMenutype = iMenutype;
	}

	public Integer getiUsertype() {
		return iUsertype;
	}
	public void setiUsertype(Integer iUsertype) {
		this.iUsertype = iUsertype;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public String getTUrl() {
		return TUrl;
	}
	public void setTUrl(String tUrl) {
		TUrl = tUrl;
	}
	public Integer getiType() {
		return iType;
	}
	public void setiType(Integer iType) {
		this.iType = iType;
	}
	
}
package com.jason.manager.resource.entity;
import java.io.Serializable;

public class TPower implements Serializable {
//	select rs.i_resourceid,rs.c_permisname,rs.c_resource,rs.i_parentid,rs.i_level,rs.i_type " +
//	"from r_userrole as ru INNER JOIN r_roleresource as rrs ON ru.i_roleid=rrs.i_roleid and ru.i_adminid=? " +
//	"INNER JOIN t_resource as rs ON rrs.i_resourceid=rs.i_resourceid and rs.i_menutype=1 order by rs.i_resourceid asc";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int resourceId;
	
	private String permisName;
	
	private String resource;
	
	private int parentId;
	
	private String permistype;
	
	private int level;
	
	private int type;
	
	private int sort;
	
	
	public int getId(){
		return resourceId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getPermisName() {
		return permisName;
	}

	public void setPermisName(String permisName) {
		this.permisName = permisName;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getPermistype() {
		return permistype;
	}

	public void setPermistype(String permistype) {
		this.permistype = permistype;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}

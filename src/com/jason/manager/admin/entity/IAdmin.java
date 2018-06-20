package com.jason.manager.admin.entity;

import java.io.Serializable;
import java.util.Date;

public class IAdmin implements Serializable
{
	private static final long serialVersionUID=1L;
	
	public static final int PROPERTY_ADMIN = 1;
	public static final int PROPERTY_USER = 2;
	
	/** 管理ID */
	private Integer Iid;
	
	/** 帐号名  */
	private String username;
	
	/** 帐号密码(MD5) */
	private String password;
	
	/** 帐号名称 */
	private String name;
	
	/** 帐号状态 */
	private Integer status;
	
	/**账户权限*/
	private String permission;
	
	/**用户手机号*/
	private String managerPhone;
	
	/**创建时间*/
	private Date createTime;
	
	/**修改密码时间*/
	private Date updateTime;
	
	/** 分辨身份 */
	private String merchantAgent;
	
	public String getMerchantAgent() {
		return merchantAgent;
	}
	public void setMerchantAgent(String merchantAgent) {
		this.merchantAgent = merchantAgent;
	}
	public Integer getIid() {
		return Iid;
	}
	public void setIid(Integer iid) {
		Iid = iid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

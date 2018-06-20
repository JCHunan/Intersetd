package com.jason.manager.utils;

import java.util.List;

import org.springframework.security.GrantedAuthority;

import com.jason.manager.resource.entity.MenuItem;
import com.jason.manager.resource.entity.TPower;

@SuppressWarnings("serial")
public class UserDetails implements
		org.springframework.security.userdetails.UserDetails {

	private int id;   
    private String username;   
    private String password; 
    private boolean disabled;
    private int open;
    //子商户
    private String idNo;
    private String authIdNo;
    private String smsIdNo;
    private GrantedAuthority[] authorities;
    private List<MenuItem> menu;
    
    public List<MenuItem> getMenu() {
        return menu;
    }
    
    /**
     * 排序，供前端正常显示
     * @param menu
     */
    public void setMenu(List<TPower> menu) {
    	this.menu = MenuTools.buildMenuItem(menu);
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public int getOpen() {
		return open;
	}
	
	public void setOpen(int open) {
		this.open = open;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GrantedAuthority[] getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return !this.disabled;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAuthIdNo() {
		return authIdNo;
	}

	public void setAuthIdNo(String authIdNo) {
		this.authIdNo = authIdNo;
	}

	public String getSmsIdNo() {
		return smsIdNo;
	}

	public void setSmsIdNo(String smsIdNo) {
		this.smsIdNo = smsIdNo;
	}
	
}

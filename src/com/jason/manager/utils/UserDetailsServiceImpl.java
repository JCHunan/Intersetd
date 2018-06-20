package com.jason.manager.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.jason.manager.admin.dao.IUserDao;
import com.jason.manager.admin.dao.RoleDao;
import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.resource.entity.TPower;

public class UserDetailsServiceImpl implements SecurityManager,
		UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		IAdmin admin = getUserByName(username);
		UserDetails userDetails = new UserDetails();
		if (admin != null) {
			userDetails.setMenu(getMenuSource(admin.getIid(),admin.getMerchantAgent()));
			userDetails.setId(admin.getIid());
			userDetails.setUsername(admin.getUsername());
			userDetails.setPassword(admin.getPassword());
			userDetails.setDisabled(false);
		} else {
			userDetails.setDisabled(true);
		}
		return userDetails;
	}

	/**得到帐号信息*/
	public IAdmin getUserByName(String username) {
		return userDao.findUserByName(username);
	}
	
	public GrantedAuthority[] getAuthorities(IAdmin chAdmin) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(1);
		grantedAuthorities.add(new GrantedAuthorityImpl(("ROLE_ADMINISTRATOR")));
		return grantedAuthorities.toArray(new GrantedAuthority[1]);
	}

	@Override
	public Map<String, String> loadUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		List<Map> urlResources = userDao.getUrlPower();
		for (Map resource : urlResources) {
			urlAuthorities.put(resource.get("i_name").toString(),resource.get("c_rolename").toString());
		}
		return urlAuthorities;
	}
	
	public List<TPower> getMenuSource(int userid ,String agent) {
		List<TPower> list = roleDao.getMenuSource(userid,agent); 
		return list;
	}
}

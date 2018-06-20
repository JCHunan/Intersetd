package com.jason.manager.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.manager.admin.dao.IAdminDao;
import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.base.dao.BaseDao;

@Component("adminDao")
public class AdminDaoImpl extends BaseDao<IAdmin,Integer> implements IAdminDao
{
//	@Autowired
//	private BaseDao<IAdmin,Integer> baseDao;
	
	@Override
	public boolean isMobileExists(String mobile){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("managerPhone", mobile);
			List<IAdmin> list = this.queryForList("i_admin.isMobileExists", map);
			if (list.size() == 1) {
				return true;
			} else {
				return false;
			}
	}
	
	@Override
	public boolean isUserVerification(String mobile,String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("managerPhone", mobile);
		map.put("userName", username);
		List<IAdmin> list = this.queryForList("i_admin.isUserVerification", map);
		if (list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<IAdmin> isByName(String username)
	    {
	    	List<IAdmin> query=new ArrayList<IAdmin>();
	    	try {
	    		query =this.queryForList("i_admin.isAccount",username);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return query;
	    }
	
	@Override
	public IAdmin findUserByName(String username) {
		return  (IAdmin) queryForObject("i_admin.getAccountByName",username);
	}
	
	@Override
	public void updatePwd(IAdmin admin) {
		Date currentDate = new Date(System.currentTimeMillis());
		admin.setUpdateTime(currentDate);
		update("i_admin.updatePwd", admin);
	}
	
}

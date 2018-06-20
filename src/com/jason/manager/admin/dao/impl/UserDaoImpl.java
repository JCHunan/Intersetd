package com.jason.manager.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;
import com.jason.manager.admin.dao.IUserDao;
import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.base.dao.BaseDao;

/**
 * 管理DAO实现类
 */
@Component("userDao")
public class UserDaoImpl extends BaseDao<IAdmin, Integer> implements IUserDao {

	@Override
	public IAdmin findUserByName(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
        Pattern p = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");    
        Matcher m = p.matcher(username); 
        
        if(m.matches()){
        	if(username !="" && username!=null){
        		 map.put("managerPhone", username);
        	}
        	IAdmin t = (IAdmin) queryForObject("i_admin.findUser", map);
 		   return t;
        }else{
        	IAdmin t = (IAdmin) queryForObject("i_admin.getAccountByAccount", username);
    		return t;
        }
	}

	@Override
	public List<Map> getUrlPower(){
		return queryForList("i_power.getUrlPower");
	}
}
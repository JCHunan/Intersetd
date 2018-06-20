package com.jason.manager.admin.dao;

import java.util.List;

import com.jason.manager.admin.entity.IAdmin;

public interface IAdminDao 
{
	//查询手机号是否存在
    public boolean isMobileExists(String mobile);
    
	//根据手机号与用户编号验证该商户是否存在
    public boolean isUserVerification(String mobile,String username);
    
    //验证用户名是否存在
    public List<IAdmin> isByName(String username);
    
    public IAdmin findUserByName(String username);
    
    public void updatePwd(IAdmin admin);
}

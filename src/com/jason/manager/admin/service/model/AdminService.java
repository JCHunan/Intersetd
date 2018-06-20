package com.jason.manager.admin.service.model;

import java.util.HashMap;
import java.util.List;

import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.resource.entity.TPlatRole;
import com.jason.manager.resource.entity.TPower;
import com.jason.manager.utils.PageBean;

/**
 * 管理服务实现接口定义
 */
public interface AdminService {

//	public ChMerchantInfo getByMid(String username);
//	
//	public ChMerchantInfo getChannelInfoByMid(String mid);
	public IAdmin findUserByName(String username);
	
	/**
	 * 获得所有的角色
	 * @return
	 */
	public List<TPlatRole> getAllPlatRole();
	
	/**
	 * 获得所有的资源列表
	 * @return
	 */
	public List<TPower> getPowerList();
	
	
	
//	public PageBean<ChMerchantInfo> getMerchantlist(PageBean<ChMerchantInfo> tpage,String appid);
//	public PageBean<ChMerchantInfo> getChMerchantByMids(PageBean<ChMerchantInfo> mPage, HashMap<String,Object> map);
	
	//查询手机号是否存在
    public boolean isMobileExists(String mobile);
    
	//根据手机号与用户编号验证该商户是否存在
	public boolean isUserVerification(String mobile,String username);
    
	public List<IAdmin> isByName(String username);
	
	public void updateAdminPwd(IAdmin admin);

//	public List<ChMerchantInfo> getAllChMerchantListByMids(HashMap<String, Object> map);
//
//	public List<ChMerchantInfo> confirmAuthUser(String subMerchant);
//
//	public List<ChMerchantInfo> confirmAuthUser(String mid, String money);
//
//	public List<ChMerchantInfo> confirmSmsUser(String subMerchant);
//
//	public List<ChMerchantInfo> getAllChMerchant();
}

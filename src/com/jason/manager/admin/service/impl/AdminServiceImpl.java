package com.jason.manager.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.manager.admin.dao.IAdminDao;
import com.jason.manager.admin.dao.ResourceDao;
import com.jason.manager.admin.dao.RoleDao;
import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.admin.service.model.AdminService;
import com.jason.manager.resource.entity.TPlatRole;
import com.jason.manager.resource.entity.TPower;
import com.jason.manager.utils.PageBean;

/**
 * 管理服务实现类
 */
@Component("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private IAdminDao adminDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
//	@Override
//	public ChMerchantInfo getByMid(String username) {
//		ChMerchantInfo user = new ChMerchantInfo();
//		user.setUsername(username);
//		return getUser(user);
//	}
//	public ChMerchantInfo getUser(ChMerchantInfo user) {
//		ChMerchantInfo admin = adminDao.getUser(user);
//		return admin;
//	}
//	@Override
//	public ChMerchantInfo getChannelInfoByMid(String mid) {
//		ChMerchantInfo user = new ChMerchantInfo();
//		user.setUsername(mid);
//		ChMerchantInfo chIF = adminDao.getChannelInfoByMid(user);
//		return chIF;
//	}
	
	@Override
	public IAdmin findUserByName(String username) {
		
		return adminDao.findUserByName(username);
	}
	
	@Override
	public List<TPlatRole> getAllPlatRole() {
		List<TPlatRole> platRoles = roleDao.getAllPlatRole();
		if(platRoles == null || platRoles.size() == 0){
			return null;
		}
		for(TPlatRole platRole : platRoles){
			platRole.setRolePower(roleDao.getRolePower(platRole.getRoleId()));
		}
		return platRoles;
	}
	@Override
	public List<TPower> getPowerList() {
		return resourceDao.getPowerList();
	}
	
//	@Override
//	public PageBean<ChMerchantInfo> getChMerchantByMids(PageBean<ChMerchantInfo> mPage, HashMap<String,Object> map) {
//		return adminDao.getMerchantByMids(mPage,map);
//	}
//	@Override
//	public PageBean<ChMerchantInfo> getMerchantlist(PageBean<ChMerchantInfo> tpage,String appid){
//		return adminDao.getMerchantlist(tpage,appid);
//	}
	@Override
	public boolean isMobileExists(String mobile) {
		boolean  flag = adminDao.isMobileExists(mobile);
		return flag;
	}
	
	@Override
	public boolean isUserVerification(String mobile,String username){
		boolean  flag = adminDao.isUserVerification(mobile,username);
		return flag;
	}
	
	@Override
	public void updateAdminPwd(IAdmin admin) {
		adminDao.updatePwd(admin);
	}
	@Override
	public List<IAdmin> isByName(String username){
		return adminDao.isByName(username);
	}
//	@Override
//	public List<ChMerchantInfo> getAllChMerchantListByMids(HashMap<String, Object> map) {
//		
//		return adminDao.getAllChMerchantListByMids(map);
//	}
//	@Override
//	public List<ChMerchantInfo> confirmAuthUser(String subMerchant) {
//		
//		return adminDao.confirmAuthUser(subMerchant);
//	}
//	@Override
//	public List<ChMerchantInfo> confirmAuthUser(String mid, String money) {
//		
//		return adminDao.confirmAuthUser(mid,money);
//	}
//	@Override
//	public List<ChMerchantInfo> confirmSmsUser(String subMerchant) {
//		// TODO Auto-generated method stub
//		return adminDao.confirmSmsUser(subMerchant);
//	}
//	@Override
//	public List<ChMerchantInfo> getAllChMerchant() {
//		
//		return adminDao.getAllChMerchant();
//	}
	
}
	

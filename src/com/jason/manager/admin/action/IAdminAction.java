package com.jason.manager.admin.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.admin.service.model.AdminService;
import com.jason.manager.base.action.BaseAction;
import com.jason.manager.utils.MD5;
import com.jason.manager.utils.UserDetails;

import net.sf.json.JSONObject;

public class IAdminAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(IAdminAction.class);
	
	@Autowired
	private AdminService adminService;
	
	private IAdmin admin;
	
	private String oldPwd;
	private String username;
	private String newPwd;
	private JSONObject json=null;
	private String newPlainPassword;
	
	//验证账号
		public void gainUsera(){
			String i="";
			List<IAdmin> er=adminService.isByName(username);
			
			if(er.size()==0){
				i="error";
			}else{
				i="success";
			}
			try {
				getResponse().getWriter().write(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//修改密码
		public void updatePwd(){
			String vp="";
//			System.out.println("编号:"+username+"修改密码:"+newPwd);
			IAdmin admin=new IAdmin();
			if(newPlainPassword.equals(newPwd)&&username!=""){
				admin.setUsername(username);
				admin.setPassword(MD5.calcMD5_2(newPwd));
				adminService.updateAdminPwd(admin);
				vp="success";
				log.error("编号 "+username+"修改密码:"+newPwd);//如:发生特殊情况,显示修改成功,数据未修改成功.可以通过日志找回
			}else{
				vp="error";
			}
			try {
				getResponse().getWriter().write(vp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void getPwd(){
			String op = MD5.calcMD5_2(oldPwd);
			String a="error";
			UserDetails user = getUserDetails();
			String password = user.getPassword();
			if(op.equals(password)){
				a="success";
			}
			try {
				getResponse().getWriter().write(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public AdminService getAdminService() {
			return adminService;
		}

		public void setAdminService(AdminService adminService) {
			this.adminService = adminService;
		}

		public IAdmin getAdmin() {
			return admin;
		}

		public void setAdmin(IAdmin admin) {
			this.admin = admin;
		}

		public String getOldPwd() {
			return oldPwd;
		}

		public void setOldPwd(String oldPwd) {
			this.oldPwd = oldPwd;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getNewPwd() {
			return newPwd;
		}

		public void setNewPwd(String newPwd) {
			this.newPwd = newPwd;
		}

		public JSONObject getJson() {
			return json;
		}

		public void setJson(JSONObject json) {
			this.json = json;
		}

		public String getNewPlainPassword() {
			return newPlainPassword;
		}

		public void setNewPlainPassword(String newPlainPassword) {
			this.newPlainPassword = newPlainPassword;
		}
	
}

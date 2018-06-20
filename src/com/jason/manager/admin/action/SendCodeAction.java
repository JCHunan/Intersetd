package com.jason.manager.admin.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.admin.service.model.AdminService;
import com.jason.manager.base.action.BaseAction;
import com.jason.manager.utils.HttpUtils;
import com.opensymphony.xwork2.ActionContext;

public class SendCodeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	 
	public String mobile;
	public String result;
	private Map<String, String> fieldErrorMap = new HashMap<String, String>();
	
	private IAdmin iAdmin;
	@Autowired
	private AdminService adminService;
	
	private String code;
	private String username;
	/**
     * 创建随机数
     *
     * @param numberFlag 是否是纯数字
     * @param length     生成的长度
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String result;
        String strTable = numberFlag ? "1234567890" : "123456789abcdefghijklmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            result = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                result += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return result;
    }

     /**
      * 发送验证码
     * @throws UnsupportedEncodingException 
      */
    public void sendCode() throws UnsupportedEncodingException{
    	
    	if(!adminService.isMobileExists(mobile)){
			return;
		}
    	
    	//随机产生6位数验证码
    	String code = SendCodeAction.createRandom(true, 6);
    	System.out.println("验证码是"+code);
    	
    	//将验证码存入到session中
    	ActionContext.getContext().getSession().put("codes", code);
    	String content = "【IMG】您的验证码为:"+code+"，有效期5分钟，请勿向他人泄露您的验证码，感谢您的信赖与支持。";
        Map<String, String> params = new HashMap<String, String>(0);
    	params.put("mobile",mobile );
    	params.put("content",URLEncoder.encode(content,"UTF-8"));
    	result="success";
    	System.out.println(content);
    	if(result.equals("success")){
    		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    		try {
    			Date time= sdf.parse(sdf.format(new Date()));
    			ActionContext.getContext().getSession().put("times", time);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    	try {
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 忘记密码_登入页面
     * 发送验证码
     * @throws UnsupportedEncodingException 
     */
    public void sendCodeUser() throws UnsupportedEncodingException{
    	
    	if(!adminService.isUserVerification(mobile,username)){
			return;
		}
    	
    	//随机产生6位数验证码
    	String code = SendCodeAction.createRandom(true, 6);
    	System.out.println("验证码是"+code);
    	
    	//将验证码存入到session中
    	ActionContext.getContext().getSession().put("codes", code);
    	String content = "【IMG】您的验证码为:"+code+"，有效期5分钟，请勿向他人泄露您的验证码，感谢您的信赖与支持。";
    	Map<String, String> params = new HashMap<String, String>(0);
    	params.put("mobile",mobile );
    	params.put("content",URLEncoder.encode(content,"UTF-8"));
    	result="success";
    	System.out.println(content);
    	if(result.equals("success")){
    		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    		try {
    			Date time= sdf.parse(sdf.format(new Date()));
    			ActionContext.getContext().getSession().put("times", time);
    		} catch (java.text.ParseException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	try {
    		getResponse().getWriter().write(result);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * 判断手机号是否存在
     * @return
     */
    public void isMobileExists(){
		String result;
		//手机号是否存在
		if(!adminService.isMobileExists(mobile)){
			result = "fail";//false
		}else{
			result = "success";
		}
		try {
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   
    /**
     * 检查验证是否正确和超时
     */
    public void checkCode(){
    	String result ;
    	Date time= null;
    	SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
     	String random =(String)ActionContext.getContext().getSession().get("codes");
     	Date time2 = (Date)ActionContext.getContext().getSession().get("times");
		try {
			time = sdf.parse(sdf.format(new Date()));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
     	if (time.getTime() -time2.getTime()  > 300000 ){ 
     		result = "out";
     	}else if(random.equals(this.getCode())){
    		result = "success";
        }else{
        	result = "fail";
        }
    	try {
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public Map<String, String> getFieldErrorMap(){
		return fieldErrorMap;
	}
	public void setFieldErrorMap(Map<String, String> fieldErrorMap) {
		this.fieldErrorMap = fieldErrorMap;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public IAdmin getiAdmin() {
		return iAdmin;
	}

	public void setiAdmin(IAdmin iAdmin) {
		this.iAdmin = iAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

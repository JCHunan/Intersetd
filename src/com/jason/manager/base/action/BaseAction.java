package com.jason.manager.base.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.context.SecurityContextHolder;


import com.jason.manager.admin.entity.IAdmin;
import com.jason.manager.utils.UserDetails;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础动作类定义
 * 
 */
public class BaseAction extends ActionSupport {
    
    private static final long serialVersionUID = 6937656694214177346L;
    
    /** 本Action的HttpSession(session)(一般情况下不允许使用) */
    protected HttpSession action_session;
    
    /** 本Action的HttpServletRequest(request)(一般情况下不允许使用) */
    protected HttpServletRequest action_request;
    
    /** 本Action的HttpServletResponse(response)(一般情况下不允许使用) */
    protected HttpServletResponse action_response;
    
    /** 已经实列的BaseDao(baseDao) */
    public String reMessage1 = "";
    public String reMessage2 = "";
    public String reMessage3 = "";
    public String reMessage4 = "";
    public String reMessage5 = "";
    
    public static HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }
    
    /**
     * 登陆用户信息
     * @return UserDetails
     */
	public UserDetails getUserDetails() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj == null || !(obj instanceof UserDetails)){
			try {
				String strPath = getRequest().getContextPath();
				getResponse().sendRedirect(strPath+"/admin/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return (UserDetails) obj;
	}
	
	public IAdmin getAdmin(){
		UserDetails user = getUserDetails();
		return  null;
	}
    
    public static HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }
    
    public static HttpServletResponse getResponse() {
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8"); 
        return ServletActionContext.getResponse();
    }
    
    public String getReMessage1() {
        return reMessage1;
    }
    
    public void setReMessage1(String reMessage1) {
        this.reMessage1 = reMessage1;
    }
    
    public String getReMessage2() {
        return reMessage2;
    }
    
    public void setReMessage2(String reMessage2) {
        this.reMessage2 = reMessage2;
    }
    
    public String getReMessage3() {
        return reMessage3;
    }
    
    public void setReMessage3(String reMessage3) {
        this.reMessage3 = reMessage3;
    }
    
    public String getReMessage4() {
        return reMessage4;
    }
    
    public void setReMessage4(String reMessage4) {
        this.reMessage4 = reMessage4;
    }
    
    public String getReMessage5() {
        return reMessage5;
    }
    
    public void setReMessage5(String reMessage5) {
        this.reMessage5 = reMessage5;
    }
    
    public HttpServletRequest getAction_request() {
        return action_request;
    }
    
    public void setAction_request(HttpServletRequest action_request) {
        this.action_request = action_request;
    }
    
    public HttpServletResponse getAction_response() {
        return action_response;
    }
    
    public void setAction_response(HttpServletResponse action_response) {
        this.action_response = action_response;
    }
    
    public HttpSession getAction_session() {
        return action_session;
    }
    
    public void setAction_session(HttpSession action_session) {
        this.action_session = action_session;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    /** 应用printWriter 打印对象 */
    public String responseWriter(Object printObject) {
        try {
            if (printObject != null)
                getResponse().getWriter().print(printObject);
        } catch (Exception ex) {
        }
        return null;
    }
    
    public void write(String content) {
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
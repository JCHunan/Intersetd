package com.jason.manager.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jason.manager.admin.action.IAdminAction;
import com.jason.manager.admin.action.SendCodeAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class VerifyLoginInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Object objAction = arg0.getAction();
		if(objAction!=null&&objAction instanceof SendCodeAction){
			return arg0.invoke();
		}
		if(objAction!=null&&objAction instanceof IAdminAction){
			return arg0.invoke();
		}
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj == null || !(obj instanceof UserDetails)){
			ActionContext ctx  = arg0.getInvocationContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
			
			if (request.getHeader("x-requested-with") != null  && request.getHeader("x-requested-with").equalsIgnoreCase( "XMLHttpRequest")) { // ajax 超时处理  
				response.setHeader("sessionstatus", "timeout");
	        } 
	        return "login";  
		}
		
		return arg0.invoke();
	}

}

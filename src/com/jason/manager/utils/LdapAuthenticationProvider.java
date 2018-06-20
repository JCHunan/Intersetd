package com.jason.manager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.UserDetailsService;

public class LdapAuthenticationProvider implements AuthenticationProvider {
	private static Logger logger = Logger.getLogger(LdapAuthenticationProvider.class);
	
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UserDetails userDetails = (UserDetails)this.userDetailsService.loadUserByUsername(authentication.getName());
		
		 //username
		logger.info("username: "+authentication.getName()+",password:"+authentication.getCredentials());
        //password
		logger.info("getPrincipal: "+authentication.getPrincipal());
		logger.info("getAuthorities: "+authentication.getAuthorities());
		logger.info("getDetails: "+authentication.getDetails());

		if(userDetails == null || userDetails.isDisabled()){
			return null;
		}
        String  username= authentication.getName();
		Pattern p = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$");    
        Matcher m = p.matcher(username); 
        
        if(m.matches()){
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
               userDetails, authentication.getCredentials(),userDetails.getAuthorities());
   		    String name = authentication.getName();
   		    return result;
        	
        }else{
    	   if(!userDetails.getPassword().equalsIgnoreCase(MD5.calcMD5_2(authentication.getCredentials().toString()))) 
			 return null;
             UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, authentication.getCredentials(),userDetails.getAuthorities());
		    String name = authentication.getName();
		    return result;
        }
		
			/*if(!userDetails.getPassword().equalsIgnoreCase(MD5.calcMD5_2(authentication.getCredentials().toString()))) 
			return null;
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, authentication.getCredentials(),userDetails.getAuthorities());
		    String name = authentication.getName();
		    return result;*/
	}

	@Override
	public boolean supports(Class arg0) {
		return true;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}

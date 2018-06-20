package com.jason.manager.utils;

public class ProjectException extends RuntimeException {

	public ProjectException() {
		super();		
	}

	public ProjectException(String msgFormat, Object... params){		 
		super(String.format(msgFormat, params));
	}
	
	public ProjectException(String tag, String msgFormat, Object... params){		 
		super("[" + tag + "]" + String.format(msgFormat, params));
	}
	
	public ProjectException(Throwable message, Throwable cause) {
		super(message==null ? "null" : message.toString(), cause);		
	}
	
	public ProjectException(String tag, Throwable message, Throwable cause) {
		super("[" + tag + "]" + message==null ? "null" : message.toString(), cause);		
	}
	
	public ProjectException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public ProjectException(String tag, String message, Throwable cause) {
		super("[" + tag + "]" + message, cause);		
	}

	public ProjectException(String message) {
		super(message);		
	}
	
	public ProjectException(String tag, String message) {
		super("[" + tag + "]" + message);		
	}

	public ProjectException(Throwable cause) {
		super(cause);		
	}
	
	 
}

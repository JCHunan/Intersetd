package com.jason.manager.xutil;

/**
 * 工具类异常
 * 
 * 修改记录
 */
public class UtilException extends RuntimeException {

	public UtilException() {
		super();
		 
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
		 
	}

	public UtilException(String message) {
		super(message);
		 
	}

	public UtilException(Throwable cause) {
		super(cause);
		 
	}

}
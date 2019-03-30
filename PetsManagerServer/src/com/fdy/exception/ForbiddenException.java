/**
 * 
 */
package com.fdy.exception;

/** 权限不够异常类
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class ForbiddenException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(Throwable th) {
		super(th);
	}
	
	public ForbiddenException(String message, Throwable th) {
		super(message,th);
	}
}

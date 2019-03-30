/**
 * 
 */
package com.fdy.exception;

/** Ȩ�޲����쳣��
 * @author fdy
 * @date 2019��3��27��
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

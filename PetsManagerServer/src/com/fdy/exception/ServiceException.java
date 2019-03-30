package com.fdy.exception;

/**服务层错误类
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable th) {
		super(th);
	}
	
	public ServiceException(String message, Throwable th) {
		super(message,th);
	}
}

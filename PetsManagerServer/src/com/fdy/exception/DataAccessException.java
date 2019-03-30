/**
 * 
 */
package com.fdy.exception;

/**数据存储异常类
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(Throwable th) {
		super(th);
	}
	
	public DataAccessException(String message, Throwable th) {
		super(message,th);
	}
	
}

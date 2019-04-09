package com.fdy.exception;

/**业务层的异常类
 * @author xq
 */
public class ServiceException extends RuntimeException{
    
    public ServiceException(){}
    
    public ServiceException(String message){
        super(message);
    }
    
    public ServiceException(Throwable th){
        super(th);
    }
    
    public ServiceException(String message,Throwable th){
        super(message,th);
    }
}

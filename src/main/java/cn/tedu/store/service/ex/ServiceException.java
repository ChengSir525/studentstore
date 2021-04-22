package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月21日 星期日  20:03:00
 * @Author: 程Sir
 * @Description: 该类标识 是一个业务层的异常处理基类
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -7323310431109356566L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

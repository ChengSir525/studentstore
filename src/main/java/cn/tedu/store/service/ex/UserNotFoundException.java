package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月21日 星期日  20:23:08
 * @Author: 程Sir
 * @Description: 该类标识 用户找不到异常
 */
public class UserNotFoundException extends ServiceException{
    private static final long serialVersionUID = -7974962108550588438L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

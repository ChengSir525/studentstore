package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月22日 星期一  19:18:08
 * @Author: 程Sir
 * @Description: 该类标识 用户密码不匹配异常
 */
public class PasswordMisMatchException extends ServiceException {
    private static final long serialVersionUID = 2953199994825877430L;

    public PasswordMisMatchException() {
        super();
    }

    public PasswordMisMatchException(String message) {
        super(message);
    }

    public PasswordMisMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordMisMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordMisMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

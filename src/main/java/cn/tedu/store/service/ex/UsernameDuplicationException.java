package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月21日 星期日  21:50:31
 * @Author: 程Sir
 * @Description: 该类标识 用户名重复异常
 */
public class UsernameDuplicationException extends ServiceException {

    private static final long serialVersionUID = 2843170577133733370L;

    public UsernameDuplicationException() {
        super();
    }

    public UsernameDuplicationException(String message) {
        super(message);
    }

    public UsernameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicationException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

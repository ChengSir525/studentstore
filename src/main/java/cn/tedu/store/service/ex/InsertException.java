package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月22日 星期一  20:14:21
 * @Author: 程Sir
 * @Description: 该类标识 数据插入时发生异常
 */
public class InsertException extends ServiceException {
    private static final long serialVersionUID = -1367405510279259174L;

    public InsertException() {

    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

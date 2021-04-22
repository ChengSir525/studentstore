package cn.tedu.store.service.ex;

/**
 * @Version: 2021年03月27日 星期六  18:20:51
 * @Author: 程Sir
 * @Description: 该类标识 数据更新过程中发生异常
 */
public class UpdateException extends ServiceException {

    private static final long serialVersionUID = 8580917140194657471L;

    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

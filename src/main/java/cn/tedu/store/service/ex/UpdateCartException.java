package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月25日 星期日  11:41:02
 * @Author: 程Sir
 * @Description: 该类标识 更新购物车发生异常
 */
public class UpdateCartException extends ServiceException {

    private static final long serialVersionUID = 5225980396586818057L;

    public UpdateCartException() {
        super();
    }

    public UpdateCartException(String message) {
        super(message);
    }

    public UpdateCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateCartException(Throwable cause) {
        super(cause);
    }

    protected UpdateCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

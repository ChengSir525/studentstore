package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月24日 星期六  18:25:51
 * @Author: 程Sir
 * @Description: 该类标识 从购物车中取消商品异常
 */
public class DeleteCartException extends ServiceException {

    private static final long serialVersionUID = 5169848786995174669L;

    public DeleteCartException() {
        super();
    }

    public DeleteCartException(String message) {
        super(message);
    }

    public DeleteCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteCartException(Throwable cause) {
        super(cause);
    }

    protected DeleteCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

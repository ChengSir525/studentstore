package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月24日 星期六  18:28:21
 * @Author: 程Sir
 * @Description: 该类标识 向购物车中添加商品时发生异常
 */
public class AddCartException extends ServiceException {

    private static final long serialVersionUID = -8661431582055014206L;

    public AddCartException() {
        super();
    }

    public AddCartException(String message) {
        super(message);
    }

    public AddCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddCartException(Throwable cause) {
        super(cause);
    }

    protected AddCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

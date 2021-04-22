package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月06日 星期二  10:23:07
 * @Author: 程Sir
 * @Description: 该类标识 收货地址信息找不到异常
 */
public class AddressNotFoundException extends ServiceException{

    private static final long serialVersionUID = 5028136186556743637L;

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

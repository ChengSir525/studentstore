package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月08日 星期四  17:13:52
 * @Author: 程Sir
 * @Description: 该类标识 商品数据找不到异常
 */
public class ProductNotFoundException extends ServiceException {

    private static final long serialVersionUID = -3878980845596969675L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String s) {
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

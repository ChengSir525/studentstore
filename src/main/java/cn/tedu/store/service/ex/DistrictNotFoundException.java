package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月06日 星期二  10:14:05
 * @Author: 程Sir
 * @Description: 该类标识 省市区数据不存在异常
 */
public class DistrictNotFoundException extends ServiceException{

    private static final long serialVersionUID = -5016600959163340137L;

    public DistrictNotFoundException() {
        super();
    }

    public DistrictNotFoundException(String message) {
        super(message);
    }

    public DistrictNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistrictNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DistrictNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

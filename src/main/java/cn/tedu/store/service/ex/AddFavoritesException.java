package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月13日 星期二  14:57:11
 * @Author: 程Sir
 * @Description: 该类标识 添加商品数据到购物车时发生异常
 */
public class AddFavoritesException extends ServiceException {

    private static final long serialVersionUID = 3459622454566002766L;

    public AddFavoritesException() {
        super();
    }

    public AddFavoritesException(String message) {
        super(message);
    }

    public AddFavoritesException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddFavoritesException(Throwable cause) {
        super(cause);
    }

    protected AddFavoritesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

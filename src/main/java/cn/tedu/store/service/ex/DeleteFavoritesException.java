package cn.tedu.store.service.ex;

/**
 * @Version: 2021年04月13日 星期二  14:55:14
 * @Author: 程Sir
 * @Description: 类标识 删除购物车数据时发生异常
 */
public class DeleteFavoritesException extends ServiceException {

    private static final long serialVersionUID = -6260457109520045620L;

    public DeleteFavoritesException() {
        super();
    }

    public DeleteFavoritesException(String message) {
        super(message);
    }

    public DeleteFavoritesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteFavoritesException(Throwable cause) {
        super(cause);
    }

    protected DeleteFavoritesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

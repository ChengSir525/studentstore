package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:31:27
 * @Author: 程Sir
 * @Description: 该类表示 上传的文件大小超出了限制异常
 */
public class FileSizeOutOfBoundsException extends FileUploadException {

    private static final long serialVersionUID = 8271547300157609765L;

    public FileSizeOutOfBoundsException() {
        super();
    }

    public FileSizeOutOfBoundsException(String message) {
        super(message);
    }

    public FileSizeOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    protected FileSizeOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

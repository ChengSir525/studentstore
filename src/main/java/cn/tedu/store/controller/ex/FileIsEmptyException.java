package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:32:27
 * @Author: 程Sir
 * @Description: 该类标识 上传的文件大小为 0，空文件异常
 */
public class FileIsEmptyException extends FileUploadException {

    private static final long serialVersionUID = -687579639945959390L;

    public FileIsEmptyException() {
        super();
    }

    public FileIsEmptyException(String message) {
        super(message);
    }

    public FileIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

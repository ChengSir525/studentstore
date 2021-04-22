package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:33:27
 * @Author: 程Sir
 * @Description: 该类标识 上传的文件类型非法异常
 */
public class FileTypeException extends FileUploadException {

    private static final long serialVersionUID = -4668299874374727452L;

    public FileTypeException() {
        super();
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    protected FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

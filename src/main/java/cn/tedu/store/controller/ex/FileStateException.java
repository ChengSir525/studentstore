package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:28:30
 * @Author: 程Sir
 * @Description: 该类标识文件上传时，文件状态异常
 *  上传时文件被移动或者删除诸如此类的异常。
 */
public class FileStateException extends FileUploadException {
    private static final long serialVersionUID = -3811509101144882649L;

    public FileStateException() {
        super();
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    protected FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

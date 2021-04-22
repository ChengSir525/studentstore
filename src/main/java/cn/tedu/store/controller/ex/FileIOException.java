package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:30:21
 * @Author: 程Sir
 * @Description: 该类标识 文件上传时，在读或写时发生了异常
 */
public class FileIOException extends FileUploadException {

    private static final long serialVersionUID = -5303896993031321082L;

    public FileIOException() {
        super();
    }

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }

    protected FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

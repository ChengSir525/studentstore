package cn.tedu.store.controller.ex;

/**
 * @Version: 2021年03月23日 星期二  17:27:29
 * @Author: 程Sir
 * @Description: 该类标识 文件上传时发生异常
 */
public class FileUploadException extends RuntimeException{
    private static final long serialVersionUID = -1200339599625842171L;

    public FileUploadException() {
        super();
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

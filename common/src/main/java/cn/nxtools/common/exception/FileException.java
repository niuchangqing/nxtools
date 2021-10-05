package cn.nxtools.common.exception;

/**
 * 文件处理异常
 * @author niuchangqing
 */
public class FileException extends RuntimeException {
    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}

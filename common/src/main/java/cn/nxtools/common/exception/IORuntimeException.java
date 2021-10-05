package cn.nxtools.common.exception;

/**
 * IOException异常包装为运行时异常
 */
public class IORuntimeException extends RuntimeException {
    public IORuntimeException() {
        super();
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IORuntimeException(Throwable cause) {
        super(cause);
    }
}

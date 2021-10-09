package cn.nxtools.jwt.exception;

/**
 * 权限不足异常
 * @author ncq
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}

package cn.nxtools.jwt.exception;

/**
 * 未登陆异常
 * @author ncq
 */
public class NotLoginException extends RuntimeException {
    public NotLoginException(String message) {
        super(message);
    }
}

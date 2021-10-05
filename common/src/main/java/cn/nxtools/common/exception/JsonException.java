package cn.nxtools.common.exception;

/**
 * @author niuchangqing
 * json工具异常类
 */
public class JsonException extends RuntimeException {

    /**
     * JsonException构造方法
     * @param message       异常信息描述
     */
    public JsonException(final String message) {
        super(message);
    }

    /**
     * JsonException构造方法
     * @param cause         cause exception
     */
    public JsonException(final Throwable cause) {
        super(cause);
    }

    /**
     * JsonException构造方法
     * @param message       异常信息描述
     * @param cause         cause exception
     */
    public JsonException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

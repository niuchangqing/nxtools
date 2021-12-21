package cn.nxtools.common.exception;

import cn.nxtools.common.StringUtil;

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

    /**
     * 异常
     * @param message           异常消息模版内容, {}为占位符
     * @param objects           异常消息模版参数
     */
    public IORuntimeException(String message, Object... objects) {
        super(StringUtil.format(message, objects));
    }
}

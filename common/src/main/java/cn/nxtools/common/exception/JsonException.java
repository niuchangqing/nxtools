package cn.nxtools.common.exception;

import cn.nxtools.common.StringUtil;

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


    /**
     * 异常
     * @param message           异常消息模版内容, {}为占位符
     * @param objects           异常消息模版参数
     */
    public JsonException(String message, Object... objects) {
        super(StringUtil.format(message, objects));
    }
}

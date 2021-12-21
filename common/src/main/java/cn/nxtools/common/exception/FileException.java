package cn.nxtools.common.exception;

import cn.nxtools.common.StringUtil;

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

    /**
     * 异常
     * @param message           异常消息模版内容, {}为占位符
     * @param objects           异常消息模版参数
     */
    public FileException(String message, Object... objects) {
        super(StringUtil.format(message, objects));
    }
}

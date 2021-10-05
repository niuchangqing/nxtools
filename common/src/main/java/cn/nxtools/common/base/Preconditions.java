package cn.nxtools.common.base;

/**
 * @author niuchangqing
 */
public final class Preconditions {
    private Preconditions(){}

    /**
     * 判断是否为空,为空抛出NullPointerException
     * @param t             参数
     * @param <T>           参数类型
     * @return              不为空,直接返回入参数
     * @throws NullPointerException if {@code t} is null
     */
    public static <T extends Object> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    /**
     * 判断是否为空,为空抛出NullPointerException
     * @param t             要校验的参数
     * @param errorMsg      错误信息
     * @param <T>           参数类型,object
     * @return              不为空,直接返回入参数
     * @throws NullPointerException if {@code t} is null
     */
    public static <T extends Object> T checkNotNull(T t, String errorMsg) {
        if (t == null) {
            throw new NullPointerException(errorMsg);
        }
        return t;
    }

    /**
     * 判断expression是不是false
     * @param expression        boolean类型表达式
     * @throws IllegalStateException    if {@code expression} is false
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * 判断expression是不是false
     * @param expression        boolean类型表达式
     * @param errorMsg          异常错误信息
     * @throws IllegalStateException    if {@code expression} is false
     */
    public static void checkState(boolean expression, String errorMsg) {
        if (!expression) {
            throw new IllegalStateException(errorMsg);
        }
    }
}

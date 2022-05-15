package cn.nxtools.common.base;

import java.util.function.Supplier;

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
     * 判断是否为空,目标为空抛出自定义异常<br>
     * Example:
     * <p>
     *     {@code String str = null;}<br>
     *     {@code Preconditions.checkNotNull(str, () -> new RuntimeException("str不能为空"));}
     * </p>
     * @param t             要校验的参数
     * @param exceptionSupplier 自定义异常
     * @param <T>           参数T
     * @param <X>           异常X
     * @return              非空返回入参目标参数
     * @throws X            目标参数为空抛出自定义异常
     * @since 1.0.5
     */
    public static <T extends Object, X extends Throwable> T checkNotNull(T t, Supplier<? extends X> exceptionSupplier) throws X {
        if (t == null) {
            throw exceptionSupplier.get();
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

    /**
     * 判断expression是否为false，false抛出异常<br>
     * Example:
     * <p>
     *     {@code int a = 1;}<br>
     *     {@code int b = 2;}<br>
     *     {@code Preconditions.checkState(a > b, () -> new RuntimeException("a不大于b"));}
     * </p>
     * @param expression        boolean类型表达式
     * @param exceptionSupplier 自定义异常
     * @param <X>               自定义异常X
     * @throws X                expression为false时抛出自定义异常
     * @since 1.0.5
     */
    public static <X extends Throwable> void checkState(boolean expression, Supplier<? extends X> exceptionSupplier) throws X {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }
}

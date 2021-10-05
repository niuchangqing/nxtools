package cn.nxtools.common;

/**
 * @author niuchangqing
 * 字符串工具类
 */
public class StringUtil {

    /**
     * "" 字符串
     */
    public static final String EMPTY = "";


    /**
     * 私有构造方法
     */
    private StringUtil() {
    }

    /**
     * 判断字符串是否为null
     * <p>
     *     null = true;
     *     "" = false;
     *     " " = false;
     * </p>
     * @param charSequence      字符串
     * @return                  是否为空
     */
    public static boolean isNull(CharSequence charSequence) {
        return  charSequence == null;
    }

    /**
     * 判断字符串是否为null或者""
     * <p>
     *     null = true;
     *     "" = true;
     *     " " = false;
     * </p>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || EMPTY.equals(charSequence);
    }

    /**
     * 判断字符串不是null
     * <p>
     *     null = false;
     *     "" = true;
     *     " " = true;
     * </p>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isNotNull(CharSequence charSequence) {
        return charSequence != null;
    }


    /**
     * 判断字符串不是null或""
     * <p>
     *     null = false;
     *     "" = false;
     *     " " = true;
     * </p>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && !EMPTY.equals(charSequence);
    }
}

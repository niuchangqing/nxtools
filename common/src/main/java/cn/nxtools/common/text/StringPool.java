package cn.nxtools.common.text;

/**
 * @author niuchangqing
 * 字符串常量池
 * @since 1.0.4
 */
public interface StringPool {
    /**
     * "" 字符串
     */
    String EMPTY = "";

    /**
     * "null" 字符串常量
     */
    String NULL = "null";

    /**
     * " " 空字符串
     */
    String SPACE = " ";

    /**
     * 空 JSON字符串 {@code "{}"}
     * @since 1.0.4
     */
    String EMPTY_JSON = "{}";

    /**
     * {@code "@"}符号字符
     * @since 1.0.4
     */
    String AT = "@";

    /**
     * {@code ","} 逗号字符串
     * @since 1.0.7
     */
    String COMMA = ",";

    /**
     * {@code ":"} 冒号字符串
     * @since 1.0.7
     */
    String COLON = ":";

    /**
     * {@code "_"} 下划线字符串
     * @since 1.0.7
     */
    String UNDER_LINE = "_";

    /**
     * {@code "[]} 空数组字符串
     * @since 1.0.7
     */
    String EMPTY_ARRAY = "[]";

    /**
     * 减号字符串
     */
    String DASHED = "-";
}

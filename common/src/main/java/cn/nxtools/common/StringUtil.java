package cn.nxtools.common;

import cn.nxtools.common.base.Objects;

import java.util.Arrays;

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
     * "null" 字符串常量
     */
    public static final String NULL = "null";

    /**
     * " " 空字符串
     */
    public static final String SPACE = " ";


    /**
     * 私有构造方法
     */
    private StringUtil() {
    }

    /**
     * 判断字符串是否为null
     * <pre>
     * null = true;
     * "" = false;
     * " " = false;
     * </pre>
     * @param charSequence      字符串
     * @return                  是否为空
     */
    public static boolean isNull(CharSequence charSequence) {
        return  charSequence == null;
    }

    /**
     * 判断字符串是否为null或者""
     * <pre>
     * null = true;
     * "" = true;
     * " " = false;
     * </pre>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || EMPTY.equals(charSequence);
    }

    /**
     * 判断字符串不是null
     * <pre>
     * null = false;
     * "" = true;
     * " " = true;
     * </pre>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isNotNull(CharSequence charSequence) {
        return charSequence != null;
    }


    /**
     * 判断字符串不是null或""
     * <pre>
     * null = false;
     * "" = false;
     * " " = true;
     * </pre>
     * @param charSequence      校验参数
     * @return                  true或者false
     */
    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && !EMPTY.equals(charSequence);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 如果自定义占位符请调用 {@link #format(String, String, Object...)} <br>
     * <pre>
     * 若参数多于占位符数量, 自动拼接到模版末尾处
     * </pre>
     * @param template                  文本模版
     * @param params                    文本参数值
     * @return                          如果template参为null,返回"null"字符串
     * @since 1.0.2
     */
    public static String format(String template, Object... params) {
        return format(template, "{}", params);
    }

    /**
     * 格式化文本<br>
     * <B>输入占位符</B>
     * <pre>
     * template参数为null时, 返回"null"字符串
     * 占位符(placeholder)为null时, 抛出 {@link IllegalArgumentException}异常
     * 占位符(placeholder)为""字符串时, 直接返回template字符串
     * 若参数多于占位符数量, 自动拼接到模版末尾处<br>
     * </pre>
     * @param template                  文本模版
     * @param placeholder               占位符
     * @param params                    文本参数值
     * @return                          如果template参为null,返回"null"字符串
     * @since 1.0.2
     */
    public static String format(String template, String placeholder, Object... params) {
        if (isNull(template)) {
            return NULL;
        }
        if (isNull(placeholder)) {
            throw new IllegalArgumentException("string format placeholder cannot be null or empty");
        }
        if (EMPTY.equals(placeholder)) {
            //替换字符串为""字符串,不做操作直接返回模版字符串
            return template;
        }
        if (ArrayUtil.isEmpty(params)) {
            //参数为空或空数组,直接返回模版字符串
            return template;
        }
        for (int i = 0; i < params.length; i++) {
            params[i] = objToString(params[i]);
        }
        //初始化长度,获得更好的性能
        StringBuilder builder = new StringBuilder(template.length() + 16 * params.length);
        int templateStart = 0;
        int i = 0;
        while (i < params.length) {
            int placeholderStart = template.indexOf(placeholder, templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template, templateStart, placeholderStart);
            builder.append(params[i++]);
            templateStart = placeholderStart + placeholder.length();
        }
        builder.append(template, templateStart, template.length());

        //如果还有未使用完的参数, 拼接到模版字符串后面
        if (i < params.length) {
            builder.append(" [");
            builder.append(params[i++]);
            while (i < params.length) {
                builder.append(", ");
                builder.append(params[i++]);
            }
            builder.append("]");
        }
        return builder.toString();
    }

    /**
     * Object转String<br>
     * 调用 {@link Object#toString()} <br>
     * <B>o为null 返回 "null" 字符串</B> <br>
     * @param o                         待转String字符串
     * @return                          结果字符串
     */
    public static String objToString(Object o) {
        if (Objects.isNull(o)) {
            return NULL;
        }
        return o.toString();
    }

    /**
     * 字符串长度 <br>
     * 参数为null 返回长度为0
     * <pre>
     * StringUtil.length(null) = 0
     * StringUtil.length("")   = 0
     * </pre>
     * @param charSequence              参数字符串
     * @return                          字符串长度
     */
    public static int length(final CharSequence charSequence) {
        return charSequence == null ? 0 : charSequence.length();
    }

    /**
     * 为null返回默认值,否则返回目标参数值<br>
     * <pre>
     * StringUtil.defaultIfNull(null, "默认值") = "默认值"
     * StringUtil.defaultIfNull("", "默认值")   = ""
     * StringUtil.defaultIfNull(" ", "默认值")  = " "
     * StringUtil.defaultIfNull("abc", "默认值")= "abc"
     * </pre>
     * @param string                    目标参数
     * @param defaultString             目标参数为null返回defaultString
     * @param <T>                       T
     * @return                          string为null返回defaultString, 否则返回string
     */
    public static <T extends CharSequence> T defaultIfNull(final T string, final T defaultString) {
        return isNull(string) ? defaultString : string;
    }

    /**
     * 为null或""返回默认值,否则返回目标参数<br>
     * <pre>
     * StringUtil.defaultIfEmpty(null, "默认值") = "默认值"
     * StringUtil.defaultIfEmpty("", "默认值")   = "默认值"
     * StringUtil.defaultIfEmpty(" ", "默认值")  = " "
     * StringUtil.defaultIfEmpty("abc", "默认值")= "abc"
     * </pre>
     * @param string                    目标参数
     * @param defaultString             目标参数为null或""返回defaultString
     * @param <T>                       T
     * @return                          string为null或""返回defaultString, 否则返回string
     */
    public static <T extends CharSequence> T defaultIfEmpty(final T string, final T defaultString) {
        return isEmpty(string) ? defaultString : string;
    }

    /**
     * 字符串左填充
     * <pre>
     * StringUtil.leftAppend(null, 5, 'a') = null
     * StringUtil.leftAppend("bc", -1, 'a')= "bc"
     * StringUtil.leftAppend("bc", 5, 'a') = "aaabc"
     * StringUtil.leftAppend("bc", 2, 'a') = "bc"
     * </pre>
     * @param str                       待填充字符串
     * @param size                      填充后字符串总长度
     * @param appendChar                填充的字符
     * @return                          填充后的字符串
     */
    public static String leftAppend(String str, final int size, final char appendChar) {
        if (isNull(str)) {
            return null;
        }
        final int append = size - str.length();
        if (append <= 0) {
            return str;
        }
        final char[] buf = new char[append];
        Arrays.fill(buf, appendChar);
        return new String(buf).concat(str);
    }

    /**
     * 字符串左填充<br>
     * 待填充字符串为null,填充后结果也为null
     * <pre>
     * StringUtil.leftAppend(null, 5, "0")   = null
     * StringUtil.leftAppend("bc", -1, "aa") = "bc"
     * StringUtil.leftAppend("bc", 5, "aa")  = "aaabc"
     * StringUtil.leftAppend("bc", 2, "aa")  = "bc"
     * StringUtil.leftAppend("bc", 5, "")    = "bc"
     * </pre>
     * @param str                       待填充字符串
     * @param size                      填充后字符串总长度
     * @param appendStr                 填充字符串,不能为空
     * @return                          填充后的字符串
     */
    public static String leftAppend(String str, final int size, String appendStr) {
        if (isNull(str)) {
            return null;
        }
        if (isNull(appendStr)) {
            throw new IllegalArgumentException("appendStr must not be null");
        }
        if (isEmpty(appendStr)) {
            return str;
        }

        final int appendStrLen = appendStr.length();
        final int strLen = str.length();
        final int append = size - strLen;
        if (append <= 0) {
            //不需要追加字符串,返回原字符串
            return str;
        }
        if (appendStrLen == 1) {
            //追加字符串长度为1,直接调用字符追加方法
            return leftAppend(str, strLen, appendStr.charAt(0));
        }
        if (append == appendStrLen) {
            //追加字符串长度和待追加长度一致,字符串直接拼接后返回
            return appendStr.concat(str);
        } else if (append < appendStrLen) {
            //待追加长度小于追加字符串长度。截取待追加字符串内容拼接后返回
            return appendStr.substring(0, append).concat(str);
        } else {
            final char[] padding = new char[append];
            final char[] padChars = appendStr.toCharArray();
            for (int i = 0; i < append; i++) {
                padding[i] = padChars[i % appendStrLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * 字符串右填充
     * <pre>
     * StringUtil.rightAppend(null, 5, 'a') = null
     * StringUtil.rightAppend("bc", -1, 'a')= "bc"
     * StringUtil.rightAppend("bc", 5, 'a') = "bcaaa"
     * StringUtil.rightAppend("bc", 2, 'a') = "bc"
     * </pre>
     * @param str                       待填充字符串
     * @param size                      填充后字符串总长度
     * @param appendChar                填充的字符
     * @return                          填充后的字符串
     */
    public static String rightAppend(String str, final int size, final char appendChar) {
        if (isNull(str)) {
            return null;
        }
        final int append = size - str.length();
        if (append <= 0) {
            return str;
        }
        final char[] buf = new char[append];
        Arrays.fill(buf, appendChar);
        return str.concat(new String(buf));
    }

    /**
     * 字符串左填充<br>
     * 待填充字符串为null,填充后结果也为null
     * <pre>
     * StringUtil.rightAppend(null, 5, "0")   = null
     * StringUtil.rightAppend("bc", -1, "aa") = "bc"
     * StringUtil.rightAppend("bc", 5, "aa")  = "bcaaa"
     * StringUtil.rightAppend("bc", 2, "aa")  = "bc"
     * StringUtil.rightAppend("bc", 5, "")    = "bc"
     * </pre>
     * @param str                       待填充字符串
     * @param size                      填充后字符串总长度
     * @param appendStr                 填充字符串,不能为空
     * @return                          填充后的字符串
     */
    public static String rightAppend(String str, final int size, String appendStr) {
        if (isNull(str)) {
            return null;
        }
        if (isNull(appendStr)) {
            throw new IllegalArgumentException("appendStr must not be null");
        }
        if (isEmpty(appendStr)) {
            return str;
        }
        final int appendStrLen = appendStr.length();
        final int strLen = str.length();
        final int append = size - strLen;
        if (append <= 0) {
            //不需要追加字符串,返回原字符串
            return str;
        }
        if (appendStrLen == 1) {
            //追加字符串长度为1,直接调用字符追加方法
            return rightAppend(str, strLen, appendStr.charAt(0));
        }
        if (append == appendStrLen) {
            //追加字符串长度和待追加长度一致,字符串直接拼接后返回
            return str.concat(appendStr);
        } else if (append < appendStrLen) {
            //待追加长度小于追加字符串长度。截取待追加字符串内容拼接后返回
            return str.concat(appendStr.substring(0, append));
        } else {
            final char[] padding = new char[append];
            final char[] padChars = appendStr.toCharArray();
            for (int i = 0; i < append; i++) {
                padding[i] = padChars[i % appendStrLen];
            }
            return str.concat(new String(padding));
        }
    }
}

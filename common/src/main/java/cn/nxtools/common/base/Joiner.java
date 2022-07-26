package cn.nxtools.common.base;

import cn.nxtools.common.collect.Iterators;
import cn.nxtools.common.collect.Lists;

import java.util.Iterator;
import java.util.StringJoiner;

import static cn.nxtools.common.StringUtil.EMPTY;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.collect.Iterables.size;
import static cn.nxtools.common.base.Preconditions.checkState;
import static cn.nxtools.common.StringUtil.format;

/**
 * @author niuchangqing
 * 数组/集合以指定字符串连接转字符串
 */
public class Joiner {

    private final String separator;

    /**
     * 字符串拼接是是否跳过null
     */
    private boolean skipNull = false;

    /**
     * 拼接的集合中如果出现null可以指定字符串进行替换
     */
    private String useForNull;

    /**
     * 配和{@link #useForNull}属性使用, 当该属性结果为true时, {@link #useForNull}生效
     */
    private boolean isUseForNull = false;

    /**
     * 私有构造方法
     */
    private Joiner(String separator) {
        this.separator = isNull(separator) ? EMPTY : separator;
    }

    /**
     * 返回一个以指定字符串的连接器
     * @param separator         指定连接字符串
     * @return                  Joiner
     */
    public static Joiner on(String separator) {
        return new Joiner(separator);
    }
    /**
     * 返回一个以指定字符的连接器
     * @param separator         指定连接字符
     * @return                  Joiner
     */
    public static Joiner on(char separator) {
        return new Joiner(String.valueOf(separator));
    }

    /**
     * 设置是否跳过(忽略)null
     * <pre>{@code
     * List<String> list = Lists.newArrayList("1", "2", null, "3");
     * Joiner.on(',').skipNull().join(list);
     * // 结果: "1,2,3"
     * }</pre>
     * @return                  Joiner
     * @since 1.0.7
     */
    public final Joiner skipNull() {
        // 已经设置了指定字符串替换空, 不能在设置跳过null
        checkState(!this.isUseForNull, () -> new UnsupportedOperationException("already specified useForNull"));
        // 已经设置过跳过null(skipNull), 不能重复设置
        checkState(!this.skipNull, () -> new UnsupportedOperationException("already specified skipNull"));
        this.skipNull = true;
        return this;
    }

    /**
     * 如果集合中出现null, 自动替换为{@code nullText}
     * <pre>{@code
     * List<String> list = Lists.newArrayList("1", "2", null, "3");
     * Joiner.on(',').useForNull("哈哈").join(list);
     * // 结果: "1,2,哈哈,3"
     * }</pre>
     * @param nullText                  null自动替换的字符串
     * @return                          Joiner
     * @since 1.0.7
     */
    public final Joiner useForNull(String nullText) {
        // 已经设置了自动跳过null, 不能在设置使用指定字符串进行替换
        checkState(!this.skipNull, () -> new UnsupportedOperationException("already specified skipNull"));
        // 已经设置了useForNull, 不能重复设置
        checkState(!this.isUseForNull, () -> new UnsupportedOperationException(format("already specified useForNull: {}", this.useForNull)));
        this.isUseForNull = true;
        this.useForNull = nullText;
        return this;
    }

    /**
     * 以指定字符拼接
     * <pre>{@code
     * String[] strings = new String[]{"2","3","1"}
     * Joiner.on(",").join(strings);
     * //结果:2,3,1
     * }</pre>
     * @param elements               数组
     * @return                      拼接结果字符串
     * @param <T>                   泛型参数
     */
    public final <T> String join(final T... elements) {
        if (isNull(elements)) {
            return EMPTY;
        }
        return join(elements, 0, elements.length);
    }
    /**
     * 以指定字符拼接
     * 样例:
     * String[] strings = new String[]{"2","3","1"}
     * Joiner.on(",").join(strings, 0, 2);
     * 结果:2,3
     * @param objects               数组
     * @param startIndex            指定下标开始进行处理(包含)
     * @param endIndex              指定下标截止终止处理(不包含)
     * @return                      拼接结果字符串
     */
    public final String join(final Object[] objects, final int startIndex, final int endIndex) {
        if (isNull(objects)) {
            return EMPTY;
        }
        return join(Lists.newArrayList(objects), startIndex, endIndex);
    }

    /**
     * 以指定字符拼接
     * 样例:
     * Lists.newLinkedList("3","2","1");
     * Joiner.on(",").join(list);
     * 结果:3,2,1
     * @param iterable              集合
     * @return                      拼接结果字符串
     */
    public final String join(final Iterable<?> iterable) {
        if (isNull(iterable)) {
            return EMPTY;
        }
        int size = size(iterable);
        return join(iterable, 0, size);
    }

    /**
     * 以指定字符拼接
     * 样例:
     * list = Lists.newLinkedList("3","2","1");
     * Joiner.on(",").join(list,1,3);
     * 结果:2,1
     * @param iterable              集合
     * @param startIndex            指定位置开始处理(包含)
     * @param endIndex              指定位置终止处理(不包含)
     * @return                      拼接结果字符串
     */
    public final String join(final Iterable<?> iterable, final  int startIndex, final int endIndex) {
        if (isNull(iterable)) {
            return EMPTY;
        }
        return join(iterable.iterator(), startIndex, endIndex);
    }

    /**
     * 以指定字符拼接<br>
     * @param iterator          迭代器
     * @return                  拼接的结果字符串
     * @since 1.0.7
     */
    public final String join(Iterator<?> iterator) {
        if (isNull(iterator)) {
            return EMPTY;
        }
        return join(iterator, 0, Iterators.size(iterator));
    }

    /**
     * 以指定字符拼接
     * 样例:
     * list = Lists.newLinkedList("3","2","1");
     * Joiner.on(",").join(list.iterator(), 0, Iterators.size(list.iterator()));
     * 结果:3,2,1
     * @param iterator              迭代器
     * @param startIndex            指定位置开始处理(包含)
     * @param endIndex              指定位置终止处理(不包含)
     * @return                      拼接结果字符串
     */
    public final String join(final Iterator<?> iterator, final int startIndex, final int endIndex) {
        if (isNull(iterator) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        int index = -1;
        while (iterator.hasNext()) {
            index++;
            Object obj = iterator.next();
            if (index < startIndex) {
                continue;
            }
            if (index >= endIndex) {
                break;
            }
            if (this.isUseForNull && isNull(obj)) {
                // 使用指定字符串替换null
                stringJoiner.add(toString(this.useForNull));
            } else if (this.skipNull && isNull(obj)) {
                // 跳过null
                continue;
            } else {
                stringJoiner.add(toString(obj));
            }
        }
        return stringJoiner.toString();
    }


    CharSequence toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return (obj instanceof CharSequence) ? (CharSequence) obj : obj.toString();
    }

    private StringJoiner newStringJoiner() {
        return new StringJoiner(separator);
    }

    /**
     * 以指定字符连接
     * @param array             int数组
     * @return                  拼接后的字符串
     */
    public final String join(final int[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 以指定字符连接
     * @param array             int数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含,如果)
     * @return                  拼接结果字符串
     */
    public final String join(final int[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 以指定字符连接
     * @param array             byte数组
     * @return                  拼接结果字符串
     */
    public final String join(final byte[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 以指定字符连接
     * @param array             byte数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含)
     * @return                  拼接结果字符串
     */
    public final String join(final byte[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             long数组
     * @return                  拼接结果字符串
     */
    public final String join(final long[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array                 long数组
     * @param startIndex            指定位置开始处理(包含)
     * @param endIndex              指定位置截止处理(不包含)
     * @return                      拼接结果字符串
     */
    public final String join(final long[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             double数组
     * @return                  拼接结果字符串
     */
    public final String join(final double[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array             double数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含)
     * @return                  拼接结果字符串
     */
    public final String join(final double[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             float数组
     * @return                  拼接结果字符串
     */
    public final String join(final float[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array             float数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含)
     * @return                  拼接结果字符串
     */
    public final String join(final float[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             short数组
     * @return                  拼接结果字符串
     */
    public final String join(final short[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array             short数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含)
     * @return                  拼接结果字符串
     */
    public final String join(final short[] array, final int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             boolean数组
     * @return                  拼接结果字符串
     */
    public final String join(final boolean[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array                 boolean数组
     * @param startIndex            指定位置开始处理(包含)
     * @param endIndex              指定位置截止处理(不包含)
     * @return                      拼接结果字符串
     */
    public final String join(final boolean[] array, final  int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    /**
     * 字符串拼接
     * @param array             char数组
     * @return                  拼接结果字符串
     */
    public final String join(final char[] array) {
        if (isNull(array)) {
            return EMPTY;
        }
        return join(array, 0, array.length);
    }

    /**
     * 字符串拼接
     * @param array             char数组
     * @param startIndex        指定位置开始处理(包含)
     * @param endIndex          指定位置截止处理(不包含)
     * @return                  拼接结果字符串
     */
    public final String join(final char[] array, final  int startIndex, final int endIndex) {
        if (isNull(array) || endIndex - startIndex <= 0) {
            return EMPTY;
        }
        final StringJoiner stringJoiner = newStringJoiner();
        for (int i = startIndex; i < endIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }
}

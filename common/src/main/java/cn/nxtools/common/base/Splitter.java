package cn.nxtools.common.base;

import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static cn.nxtools.common.CollectionUtil.isNotEmpty;
import static cn.nxtools.common.StringUtil.isNotEmpty;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Preconditions.checkState;
import static cn.nxtools.common.StringUtil.format;

/**
 * @author niuchangqing
 * 字符串拆分工具
 */
public class Splitter {
    /**
     * 拆分字符串
     */
    private final String separator;

    /**
     * 拆分后集合最大size
     */
    private int limit;

    /**
     * 私有构造方法
     */
    private Splitter(String separator) {
        checkState(isNotEmpty(separator), "The separator may not be null or empty string");
        this.separator = separator;
    }

    /**
     * 返回一个以指定字符的分割器
     * @param separator                 指定分割字符串
     * @return                          Splitter
     */
    public static Splitter on(final String separator) {
        return new Splitter(separator);
    }

    /**
     * 返回一个以指定字符的分割器
     * @param separator                 指定分割字符
     * @return                          Splitter
     */
    public static Splitter on(final char separator) {
        return new Splitter(String.valueOf(separator));
    }

    /**
     * 拆分后集合最大保留长度, 返回的集合{@link List#size()}可能小于等于{@code limit}但是不会大于{@code limit}
     * <pre>{@code
     * String str1 = "1,2,3,4,5,";
     * List<String> list1 = Splitter.on(',').limit(3).splitToList(str1);
     * // 结果: ["1","2","3"]
     * String str2 = "1,2";
     * List<String> list1 = Splitter.on(',').limit(5).splitToList(str1);
     * // 结果: ["1","2"]
     * }</pre>
     * 注意: 如果{@link List#size()}已达到{@code limit}限制, 剩余字符串将不在加入集合中
     * @param limit                     拆分后集合最大长度, 必须大于0
     * @return                          Splitter
     * @since 1.0.7
     * @throws UnsupportedOperationException    limit小于或等于0时抛出,或者已经设置过limit值再次设置会抛出异常
     */
    public final Splitter limit(int limit) {
        // limit参数必须大于0
        checkState(limit > 0, () -> new UnsupportedOperationException(format("must be greater than zero: {}", limit)));
        // 如果当前对象limit属性值已经大于0, 抛出请勿重复设置limit属性值错误
        checkState(this.limit <= 0, () -> new UnsupportedOperationException(format("already specified limit: {}", this.limit)));
        this.limit = limit;
        return this;
    }

    /**
     * 字符串分割 <br>
     * 样例: <br>
     * {@code Splitter.on(',').splitToList("1,2,3");} <br>
     * 结果:["1","2","3"]
     * @param str                   待分割字符串
     * @return                      分割后字符串集合, {@link java.util.ArrayList}集合
     */
    public final List<String> splitToList(final CharSequence str) {
        return splitWork(str);
    }

    /**
     * 字符串分割
     * 样例:
     * Splitter.on(',').split("1,2,3");
     * 结果:["1","2","3"]
     * @param str                   待分割字符串
     * @return                      分割后字符串数组
     */
    public final String[] split(final CharSequence str) {
        List<String> list = splitWork(str);
        return list.toArray(new String[]{});
    }

    /**
     * 字符串分割
     * 样例:
     * Splitter.on(",").split("1,2,3");
     * 结果:["1","2","3"]
     * @param str                   待分割字符串
     * @return                      分割后的字符串
     */
    public final Stream<String> splitToStream(final CharSequence str) {
        List<String> list = splitWork(str);
        return StreamSupport.stream(list.spliterator(),false);
    }


    /**
     * 字符串拆分逻辑,返回List集合
     */
    private final List<String> splitWork(final CharSequence str) {
        if (isNull(str)) {
            return Lists.newArrayList();
        }
        int length = str.length();
        if (length == 0) {
            return Lists.newArrayList();
        }
        final List<String> list = Lists.newArrayList();
        int startIndex = 0;
        int i = 0;
        if (this.separator.length() == 1) {
            final char sep = this.separator.charAt(0);
            while (i < length) {
                if (this.limit > 0 && list.size() >= this.limit) {
                    break;
                }
                if (str.charAt(i) == sep) {
                    list.add(str.subSequence(startIndex, i).toString());
                    startIndex = ++i;
                    continue;
                }
                i++;
            }
        }else {
            int seqLength = this.separator.length();
            while (i < length) {
                if (this.limit > 0 && list.size() >= this.limit) {
                    break;
                }
                boolean match = true;
                for (int j = 0; j < seqLength; j++) {
                    if (this.separator.charAt(j) == str.charAt(i+j)) {
                        continue;
                    }else {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    list.add(str.subSequence(startIndex, i).toString());
                    startIndex = i + seqLength;
                }
                i++;
            }
        }
        if (this.limit > 0 && list.size() >= this.limit) {
            // 设置了limit, 并且集合size已经满足limit限制,直接返回
            return list;
        } else {
            list.add(str.subSequence(startIndex, length).toString());
        }
        return list;
    }

    /**
     * 获取一个MapSplitter分割器,将指定字符串分割为Map
     * 样例:
     * {@code
     * String str = "page=1&pageSize=10&name=zhangsan&age=18";
     * Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(str);
     * 结果:{"page":"1","pageSize":"10","name":"zhangsan","age":"18"}
     * }
     * @param separator         第二次拆分的标记字符串,对应上面样例代码中的=字符
     * @return                  MapSplitter
     */
    public final MapSplitter withKeyValueSeparator(final String separator) {
        return new MapSplitter(this, on(separator));
    }

    /**
     * 获取一个MapSplitter分割器,将指定字符串分割为Map
     * 样例:
     * {@code
     * String str = "page=1&pageSize=10&name=zhangsan&age=18";
     * Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(str);
     * 结果:{"page":"1","pageSize":"10","name":"zhangsan","age":"18"}
     * }
     * @param separator         第二次拆分的标记字符
     * @return                  MapSplitter
     */
    public final MapSplitter withKeyValueSeparator(final char separator) {
        return new MapSplitter(this, on(separator));
    }

    public static final class MapSplitter {
        /**
         * 外层分割器
         */
        private final Splitter outerSplitter;

        /**
         * 内存分割器
         */
        private final Splitter innerSplitter;

        private MapSplitter(Splitter outerSplitter, Splitter innerSplitter) {
            this.outerSplitter = outerSplitter;
            this.innerSplitter = innerSplitter;
        }

        /**
         * 字符串分割转Map
         * 样例:
         * {@code
         * String str = "page=1&pageSize=10&name=zhangsan&age=18";
         * Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(str);
         * 结果:{"page":"1","pageSize":"10","name":"zhangsan","age":"18"}
         * }
         * @param str               待分割字符串
         * @return                  Map。注意返回的map不能进行put操作
         */
        public Map<String,String> split(CharSequence str) {
            Map<String,String> map = Maps.newLinkedHashMap();
            List<String> list = outerSplitter.splitToList(str);
            for (String s : list) {
                List<String> mapList = innerSplitter.splitToList(s);
                checkState(isNotEmpty(mapList) && mapList.size() == 2, String.format("data [%s] is not a valid entry",s));
                String key = mapList.get(0);
                checkState(!map.containsKey(key), String.format("Duplicate key [%s] found.",key));
                map.put(key, mapList.get(1));
            }
            return Collections.unmodifiableMap(map);
        }
    }
}

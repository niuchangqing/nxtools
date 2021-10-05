package cn.nxtools.common.base;

import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static cn.nxtools.common.CollectionUtil.isEmpty;
import static cn.nxtools.common.CollectionUtil.isNotEmpty;
import static cn.nxtools.common.StringUtil.isNotEmpty;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Preconditions.checkState;

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
     * 字符串分割
     * 样例:
     * Splitter.on(',').splitToList("1,2,3");
     * 结果:["1","2","3"]
     * @param str                   待分割字符串
     * @return                      分割后字符串集合
     */
    public final List<String> splitToList(final String str) {
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
    public final String[] split(final String str) {
        List<String> list = splitWork(str);
        if (isNull(list)) {
            return null;
        }
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
    public final Stream<String> splitToStream(final String str) {
        List<String> list = splitWork(str);
        if (isNull(list)) {
            return null;
        }
        return StreamSupport.stream(list.spliterator(),false);
    }


    /**
     * 字符串拆分逻辑,返回List集合
     */
    private final List<String> splitWork(final String str) {
        if (isNull(str)) {
            return null;
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
                if (str.charAt(i) == sep) {
                    list.add(str.substring(startIndex, i));
                    startIndex = ++i;
                    continue;
                }
                i++;
            }
        }else {
            int seqLength = this.separator.length();
            while (i < length) {
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
                    list.add(str.substring(startIndex, i));
                    startIndex = i + seqLength;
                }
                i++;
            }
        }
        list.add(str.substring(startIndex, length));
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
        public Map<String,String> split(String str) {
            Map<String,String> map = Maps.newLinkedHashMap();
            List<String> list = outerSplitter.splitToList(str);
            if (isEmpty(list)) {
                return Collections.unmodifiableMap(map);
            }
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

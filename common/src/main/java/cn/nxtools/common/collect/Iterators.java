package cn.nxtools.common.collect;

import java.util.Collection;
import java.util.Iterator;

import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 */
public final class Iterators {
    private Iterators() {}

    /**
     * iterator to collection
     * {@code
     * Set<String> set = new HashSet();
     * set.add("1");
     * List<String> list = new ArrayList<>();
     * addAll(list,set.iterator());
     * }
     * @param collection                集合对象
     * @param iterator                  Iterator迭代器参数
     * @param <E>                       E
     * @return                          存在add失败的返回就为true
     */
    public static <E> boolean addAll(Collection<E> collection, Iterator<? extends E> iterator) {
        checkNotNull(collection);
        checkNotNull(iterator);
        boolean flag = false;
        while (iterator.hasNext()) {
            flag |= collection.add(iterator.next());
        }
        return flag;
    }

    /**
     * 获取Iterator size
     * 注意一旦调用该方法之后iterator.hasNext()结果就为false,再次使用需要重新获取迭代器
     * @param iterator          集合迭代器
     * @return                  迭代器数据size
     */
    public static int size(Iterator<?> iterator) {
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        return size;
    }
}

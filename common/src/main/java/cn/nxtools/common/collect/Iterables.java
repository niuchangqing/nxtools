package cn.nxtools.common.collect;

import java.util.Collection;

/**
 * @author niuchangqing
 */
public final class Iterables {

    /**
     * 私有构造函数
     */
    private Iterables() {}

    /**
     * 将iterable中的数据赋值到collection
     * @param collection                集合(Set,List,Vector等等)
     * @param iterable                  所有集合顶级接口
     * @param <E>                       E
     * @return                          有add失败的返回结果为true
     */
    public static <E> boolean addAll(Collection<E> collection, Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection<? extends E> c = (Collection<? extends E>) iterable;
            return collection.addAll(c);
        }
        return Iterators.addAll(collection,iterable.iterator());
    }

    /**
     * 获取Iterable size
     * @param iterable              Iterable
     * @return                      集合大小
     */
    public static int size(Iterable<?> iterable) {
        return (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : Iterators.size(iterable.iterator());
    }
}

package cn.nxtools.common.tuple;

import java.io.Serializable;

/**
 * 支持俩个参数的元组
 */
public class Tuple2<T1, T2> implements Serializable {

    protected T1 first;

    protected T2 second;

    public static <T1, T2> Tuple2<T1, T2> of(T1 first, T2 second) {
        return new Tuple2Impl<>(first, second);
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + ")";
    }
}

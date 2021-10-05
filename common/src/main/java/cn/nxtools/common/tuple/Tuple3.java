package cn.nxtools.common.tuple;

/**
 * 支持3个参数的元组
 */
public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {

    protected T3 third;

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 first, T2 second, T3 third) {
        return new Tuple3Impl<>(first, second, third);
    }

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + ")";
    }
}

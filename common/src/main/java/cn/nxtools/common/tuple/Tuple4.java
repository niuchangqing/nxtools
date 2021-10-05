package cn.nxtools.common.tuple;

/**
 * 支持4个参数的元组
 */
public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {

    protected T4 fourth;

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 first, T2 second, T3 third, T4 fourth) {
        return new Tuple4Impl<>(first, second, third, fourth);
    }

    public T4 getFourth() {
        return fourth;
    }

    public void setFourth(T4 fourth) {
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + ")";
    }
}

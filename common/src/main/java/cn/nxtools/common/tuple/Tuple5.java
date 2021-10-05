package cn.nxtools.common.tuple;

/**
 * 支付5个参数的元组
 */
public class Tuple5<T1, T2, T3, T4, T5> extends Tuple4<T1, T2, T3, T4> {
    protected T5 fifth;

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 first, T2 second, T3 third, T4 fourth, T5 fifth) {
        return new Tuple5Impl<>(first, second, third, fourth, fifth);
    }

    public T5 getFifth() {
        return fifth;
    }
    public void setFifth(T5 fifth) {
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + "," + getFifth() + ")";
    }
}

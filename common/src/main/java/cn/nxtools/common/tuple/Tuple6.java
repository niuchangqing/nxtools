package cn.nxtools.common.tuple;

/**
 * 支持6个参数的元组
 */
public class Tuple6<T1, T2, T3, T4, T5, T6> extends Tuple5<T1, T2, T3, T4, T5> {

    protected T6 sixth;

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth) {
        return new Tuple6Impl<>(first, second, third, fourth, fifth, sixth);
    }

    public T6 getSixth() {
        return sixth;
    }

    public void setSixth(T6 sixth) {
        this.sixth = sixth;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + "," + getFifth() + "," + getSixth() + ")";
    }
}

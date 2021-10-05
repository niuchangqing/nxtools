package cn.nxtools.common.tuple;

/**
 * 支持7个参数的元组
 */
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> extends Tuple6<T1, T2, T3, T4, T5, T6> {
    protected T7 seventh;

    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth, T7 seventh) {
        return new Tuple7Impl<>(first, second, third, fourth, fifth, sixth, seventh);
    }

    public T7 getSeventh() {
        return seventh;
    }
    public void setSeventh(T7 seventh) {
        this.seventh = seventh;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + "," + getFifth() + "," + getSixth() + "," + getSeventh() + ")";
    }
}

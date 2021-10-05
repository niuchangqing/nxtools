package cn.nxtools.common.tuple;

/**
 * 支持8个参数的元组
 */
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> extends Tuple7<T1, T2, T3, T4, T5, T6, T7> {
    protected T8 eighth;

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth, T7 seventh, T8 eighth) {
        return new Tuple8Impl<>(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }

    public T8 getEighth() {
        return eighth;
    }

    public void setEighth(T8 eighth) {
        this.eighth = eighth;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + "," + getFifth() + "," + getSixth() + "," + getSeventh() + "," + getEighth() + ")";
    }
}

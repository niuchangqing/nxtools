package cn.nxtools.common.tuple;

/**
 * 支付9个参数的元组
 */
public class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {
    protected T9 ninth;

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth, T7 seventh, T8 eighth, T9 ninth) {
        return new Tuple9Impl<>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }

    public T9 getNinth() {
        return this.ninth;
    }

    public void setNinth(T9 ninth) {
        this.ninth = ninth;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + "," + getFourth() + "," + getFifth() + "," + getSixth() + "," + getSeventh() + "," + getEighth() + "," + getNinth() + ")";
    }
}

package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支付9个参数的元组
 */
public class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {
    protected T9 t9;

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
        return new Tuple9Impl<>(t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    public T9 getT9() {
        return this.t9;
    }

    public void setT9(T9 t9) {
        this.t9 = t9;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + "," + getT5() + "," + getT6() + "," + getT7() + "," + getT8() + "," + getT9() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple9)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple9 tuple9 = (Tuple9) object;
            return Objects.equals(this.t9, tuple9.t9);
        }
    }
}

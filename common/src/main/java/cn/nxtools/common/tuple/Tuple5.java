package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支付5个参数的元组
 */
public class Tuple5<T1, T2, T3, T4, T5> extends Tuple4<T1, T2, T3, T4> {
    protected T5 t5;

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return new Tuple5Impl<>(t1, t2, t3, t4, t5);
    }

    public T5 getT5() {
        return t5;
    }
    public void setT5(T5 fifth) {
        this.t5 = t5;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + "," + getT5() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple5)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple5 tuple5 = (Tuple5) object;
            return Objects.equals(this.t5, tuple5.t5);
        }
    }
}

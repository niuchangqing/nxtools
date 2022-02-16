package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支持8个参数的元组
 */
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> extends Tuple7<T1, T2, T3, T4, T5, T6, T7> {
    protected T8 t8;

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
        return new Tuple8Impl<>(t1, t2, t3, t4, t5, t6, t7, t8);
    }

    public T8 getT8() {
        return t8;
    }

    public void setT8(T8 t8) {
        this.t8 = t8;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + "," + getT5() + "," + getT6() + "," + getT7() + "," + getT8() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple8)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple8 tuple8 = (Tuple8) object;
            return Objects.equals(this.t8, tuple8.t8);
        }
    }
}

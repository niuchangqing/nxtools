package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支持7个参数的元组
 */
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> extends Tuple6<T1, T2, T3, T4, T5, T6> {
    protected T7 t7;

    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
        return new Tuple7Impl<>(t1, t2, t3, t4, t5, t6, t7);
    }

    public T7 getT7() {
        return t7;
    }
    public void setT7(T7 t7) {
        this.t7 = t7;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + "," + getT5() + "," + getT6() + "," + getT7() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple7)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple7 tuple7 = (Tuple7) object;
            return Objects.equals(this.t7, tuple7.t7);
        }
    }
}

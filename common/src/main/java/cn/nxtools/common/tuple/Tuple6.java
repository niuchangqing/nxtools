package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支持6个参数的元组
 */
public class Tuple6<T1, T2, T3, T4, T5, T6> extends Tuple5<T1, T2, T3, T4, T5> {

    protected T6 t6;

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return new Tuple6Impl<>(t1, t2, t3, t4, t5, t6);
    }

    public T6 getT6() {
        return t6;
    }

    public void setT6(T6 sixth) {
        this.t6 = t6;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + "," + getT5() + "," + getT6() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple6)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple6 tuple6 = (Tuple6) object;
            return Objects.equals(this.t6, tuple6.t6);
        }
    }
}

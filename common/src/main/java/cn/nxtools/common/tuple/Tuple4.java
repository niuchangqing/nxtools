package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支持4个参数的元组
 */
public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {

    protected T4 t4;

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4Impl<>(t1, t2, t3, t4);
    }

    public T4 getT4() {
        return t4;
    }

    public void setT4(T4 t4) {
        this.t4 = t4;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + "," + getT4() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple4)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple4 tuple4 = (Tuple4) object;
            return Objects.equals(this.t4, tuple4.t4);
        }
    }
}

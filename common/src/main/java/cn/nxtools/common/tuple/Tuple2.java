package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

import java.io.Serializable;

/**
 * 支持俩个参数的元组
 */
public class Tuple2<T1, T2> implements Serializable {

    protected T1 t1;

    protected T2 t2;

    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2Impl<>(t1, t2);
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }

    public void setT1(T1 t1) {
        this.t1 = t1;
    }

    public void setT2(T2 t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Tuple2<?, ?> tuple2 = (Tuple2) object;
            return Objects.equals(this.t1, tuple2.t1) && Objects.equals(this.t2, tuple2.t2);
        } else {
            return false;
        }
    }
}

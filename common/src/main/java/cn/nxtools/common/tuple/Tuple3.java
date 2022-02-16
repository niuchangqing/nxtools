package cn.nxtools.common.tuple;

import cn.nxtools.common.base.Objects;

/**
 * 支持3个参数的元组
 */
public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {

    protected T3 t3;

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3Impl<>(t1, t2, t3);
    }

    public T3 getT3() {
        return t3;
    }

    public void setT3(T3 t3) {
        this.t3 = t3;
    }

    @Override
    public String toString() {
        return "(" + getT1() + "," + getT2() + "," + getT3() + ")";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Tuple3)) {
            return false;
        } else if (!super.equals(object)) {
            return false;
        } else {
            Tuple3 tuple3 = (Tuple3) object;
            return Objects.equals(this.t3, tuple3.t3);
        }
    }
}

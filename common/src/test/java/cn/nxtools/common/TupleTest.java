package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.tuple.*;

import java.math.BigDecimal;

public final class TupleTest {

    @Test
    public void tupleTest() {
        Tuple2<String, BigDecimal> of = Tuple.of("a", BigDecimal.ZERO);
        System.out.println(of.toString());
        System.out.println(of.getFirst());
        System.out.println(of.getSecond());

        Tuple3<Integer, BigDecimal, Double> of1 = Tuple.of(1, new BigDecimal("10"), 12.3D);
        System.out.println(of1.getThird());

        Tuple4<String, String, String, String> of2 = Tuple.of("a", "b", "c", "d");
        System.out.println(of2.getFirst());

        Tuple5<String, String, String, String, String> of3 = Tuple.of("a", "b", "c", "d", "5");
        System.out.println(of3.getFifth());

        Tuple6<String, String, String, String, String, String> of4 = Tuple.of("1", "2", "3", "4", "5", "6");
        System.out.println(of4.getFirst());

        Tuple7<String, String, String, String, String, String, String> of5 = Tuple.of("1", "2", "3", "4", "5", "6", "7");
        System.out.println(of5.getSeventh());

        Tuple8<String, String, String, String, String, String, String, String> of6 = Tuple.of("1", "2", "3", "4", "5", "6", "7", "8");
        System.out.println(of6.getEighth());

        Tuple9<String, String, String, String, String, String, String, String, String> of7 = Tuple.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        System.out.println(of7.getNinth());
    }
}

package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.tuple.*;

import java.math.BigDecimal;

public final class TupleTest {

    @Test
    public void tupleTest() {
        Tuple2<String, BigDecimal> of = Tuple.of("a", BigDecimal.ZERO);
        System.out.println(of.toString());
        System.out.println(of.getT1());
        System.out.println(of.getT2());

        Tuple3<Integer, BigDecimal, Double> of1 = Tuple.of(1, new BigDecimal("10"), 12.3D);
        System.out.println(of1.getT3());

        Tuple4<String, String, String, String> of2 = Tuple.of("a", "b", "c", "d");
        System.out.println(of2.getT1());

        Tuple5<String, String, String, String, String> of3 = Tuple.of("a", "b", "c", "d", "5");
        System.out.println(of3.getT5());

        Tuple6<String, String, String, String, String, String> of4 = Tuple.of("1", "2", "3", "4", "5", "6");
        System.out.println(of4.getT1());

        Tuple7<String, String, String, String, String, String, String> of5 = Tuple.of("1", "2", "3", "4", "5", "6", "7");
        System.out.println(of5.getT7());

        Tuple8<String, String, String, String, String, String, String, String> of6 = Tuple.of("1", "2", "3", "4", "5", "6", "7", "8");
        System.out.println(of6.getT8());

        Tuple9<String, String, String, String, String, String, String, String, String> of7 = Tuple.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        System.out.println(of7.getT9());
        Tuple2<String, String> c = of7;
        System.out.println(c.getT1());
    }

    @Test
    public void testEquals() {
        Tuple2<Object, String> of = Tuple.of(null, "123");
        Tuple2<Object, String> of1 = Tuple.of(null, "123");
        System.out.println(of.equals(of1));//true

        Tuple2<Object, String> of2 = Tuple.of(new Object(), "123");
        Tuple2<Object, String> of3 = Tuple.of(null, "123");
        System.out.println(of2.equals(of3));//false

        Tuple3<Object, String, BigDecimal> of4 = Tuple.of(new Object(), "123", BigDecimal.ZERO);
        Tuple2<Object, String> of5 = Tuple.of(null, "123");
        System.out.println(of4.equals(of5));//false

        Tuple3<Integer, String, BigDecimal> of6 = Tuple.of(1, "123", BigDecimal.ZERO);
        Tuple2<Object, String> of7 = Tuple.of(new Object(), "123", BigDecimal.ZERO);
        System.out.println(of6.equals(of7));//false


        Tuple3<Integer, String, BigDecimal> of8 = Tuple.of(1, "123", BigDecimal.ZERO);
        Tuple2<Object, String> of9 = Tuple.of(1, "123", BigDecimal.ZERO);
        System.out.println(of8.equals(of9));//true


        Tuple9<BigDecimal, Integer, String, String, String, String, String, Long, Double> of10 = Tuple.of(BigDecimal.ZERO, 1, "1", "2", "3", "4", "5", 10000L, 23918D);
        Tuple9<BigDecimal, Integer, String, String, String, String, String, Long, Double> of11 = Tuple.of(BigDecimal.ZERO, 1, "1", "2", "3", "4", "5", 10000L, 23918D);
        System.out.println(of10.equals(of11));//true
    }
}

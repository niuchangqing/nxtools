package cn.nxtools.common.tuple;

/**
 * 可以直接使用该类进行new支持对于个数参数的对象
 * {@code
 * Tuple2<String, String> of = Tuple.of("", "");
 * Tuple3<Integer, BigDecimal, Double> of = Tuple.of(1, new BigDecimal("10"), 12.3D);
 * }
 */
public class Tuple extends Tuple9 {
    private Tuple() {}
}

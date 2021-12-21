package cn.nxtools.common;

/**
 * 数组工具
 */
public class ArrayUtil {
    /**
     * 判断数组是不是null
     * <pre>
     * null = true
     * [] = false
     * ["a"] = false
     * </pre>
     * @param array             array参数
     * @param <T>               数组元素类型
     * @return                  null=true, 其他为false
     * @since 1.0.2
     */
    public static <T> boolean isNull(T[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(boolean[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(float[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(double[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(byte[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(long[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(int[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(short[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null
     * @param array             校验参数
     * @return                  是否为null, null=true,其他=false
     */
    public static boolean isNull(char[] array) {
        return array == null;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new boolean[0]) = true
     * ArrayUtil.isEmpty(new boolean[]{true}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new float[0]) = true
     * ArrayUtil.isEmpty(new float[]{0.2f}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new double[0]) = true
     * ArrayUtil.isEmpty(new double[]{0.2d}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new byte[0]) = true
     * ArrayUtil.isEmpty(new byte[]{0}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new long[0]) = true
     * ArrayUtil.isEmpty(new long[]{0}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new int[0]) = true
     * ArrayUtil.isEmpty(new int[]{0}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new short[0]) = true
     * ArrayUtil.isEmpty(new short[]{0}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为null或[]
     * <pre>
     * ArrayUtil.isEmpty(null) = true
     * ArrayUtil.isEmpty(new char[0]) = true
     * ArrayUtil.isEmpty(new char[]{'a'}) = false
     * </pre>
     * @param array             校验参数
     * @return                  是否为null或[], null或[]=true,其他=false
     */
    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(boolean[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(float[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(double[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(byte[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(long[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(int[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(short[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于null
     * @param array             校验参数
     * @return                  null=false,其他=true
     */
    public static boolean isNotNull(char[] array) {
        return array != null;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new boolean[0]) = false
     * ArrayUtil.isEmpty(new boolean[]{true}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(boolean[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new float[0]) = false
     * ArrayUtil.isEmpty(new float[]{0.2f}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(float[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new double[0]) = false
     * ArrayUtil.isEmpty(new double[]{0.2d}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(double[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new byte[0]) = false
     * ArrayUtil.isEmpty(new byte[]{0}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(byte[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new long[0]) = false
     * ArrayUtil.isEmpty(new long[]{0}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(long[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new int[0]) = false
     * ArrayUtil.isEmpty(new int[]{0}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(int[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new short[0]) = false
     * ArrayUtil.isEmpty(new short[]{0}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(short[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不等于空并且长度大于0
     * <pre>
     * ArrayUtil.isEmpty(null) = false
     * ArrayUtil.isEmpty(new char[0]) = false
     * ArrayUtil.isEmpty(new char[]{'a'}) = true
     * </pre>
     * @param array             校验参数
     * @return                  null或[]=false,其他=true
     */
    public static boolean isNotEmpty(char[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断数组不是null
     * <pre>
     * null = false
     * [] = true
     * ["a"] = true
     * </pre>
     * @param array             array参数
     * @param <T>               数组元素类型
     * @return                  null=false, 其他为true
     * @since 1.0.2
     */
    public static <T> boolean isNotNull(T[] array) {
        return array != null;
    }

    /**
     * 判断数组是不是null或数组长度等于0
     * <pre>
     * null = true
     * [] = true
     * ["a"] = false
     * </pre>
     * @param array             array参数
     * @param <T>               数组元素类型
     * @return                  null或[] =true, 其他为true
     * @since 1.0.2
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组不是null或数组长度等于0
     * <pre>
     * null = false
     * [] = false
     * ["a"] = true
     * </pre>
     * @param array             array参数
     * @param <T>               数组元素类型
     * @return                  null或[] =false, 其他为true
     * @since 1.0.2
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 数组填充数据<br>
     * 其他基本数据类型填充可使用 {@link java.util.Arrays#fill(int[], int)}
     * @param array             数组参数
     * @param val               数组元素填充值
     * @param <T>               数组元素类型
     */
    public static <T> void fill(T[] array, T val) {
        if (isEmpty(array)) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = val;
        }
    }

    /**
     * 指定数组的位置开始填充数据
     * 其他基本数据类型填充可使用 {@link java.util.Arrays#fill(int[], int, int, int)}
     * @param array             数组参数
     * @param fromIndex         从下标的第几位开始填充
     * @param toIndex           填充截止的下标位置
     * @param val               填充值
     * @param <T>               T
     */
    public static <T> void fill(T[] array, int fromIndex, int toIndex, T val) {
        rangeCheck(array.length, fromIndex, toIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = val;
        }
    }


    // ---------------------------------------------------------------- private method start

    /**
     * 校验数组范围
     * @param arrayLength               数组总长度
     * @param fromIndex                 操作数组的开始位置
     * @param toIndex                   操作数组的截止位置
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(StringUtil.format("fromIndex {} > toIndex {}", fromIndex, toIndex));
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    // ---------------------------------------------------------------- private method end
}

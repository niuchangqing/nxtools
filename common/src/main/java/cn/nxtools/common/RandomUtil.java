package cn.nxtools.common;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static cn.nxtools.common.StringUtil.*;

/**
 * 随机相关工具类
 * @author niuchangqing
 */
public class RandomUtil {

    /**
     * 0～9数字字符串
     */
    public static final String NUMBER = "0123456789";

    /**
     * a~z小写字母
     */
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * A~Z大写字母
     */
    public static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 0～9,a～z,A～Z字符串
     */
    public static final String LOWER_UPPER_CASE_NUMBER = NUMBER + LOWER_CASE + UPPER_CASE;

    /**
     * RGB颜色范围上限
     */
    public static final int RGB_LIMIT = 256;

    /**
     * 私有构造方法
     */
    private RandomUtil(){
    }

    /**
     * 获取随机对象
     * @return          随机对象ThreadLocalRandom
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取随机数字,范围[0,Integer.MAX_VALUE],不包含Integer.MAX_VALUE
     * @return          随机数
     */
    public static int randomInt() {
        return randomInt(0, Integer.MAX_VALUE);
    }

    /**
     * 获取随机数字,范围[0,max]。不包含max
     * <p>
     *     max参数不能小于或等于0
     * </p>
     * @param max       随机数最大范围,不包含max
     * @return          随机数
     */
    public static int randomInt(final int max) {
        return randomInt(0, max);
    }

    /**
     * 获取随机数据,范围[min,max]。不包含max
     * @param min       随机数最小范围
     * @param max       随机数最大范围,不包含max。
     * @return          返回int类型随机数字
     */
    public static int randomInt(final int min, final int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * 随机获取boolean
     * @return          返回true或者false
     */
    public static boolean randomBoolean() {
        return getRandom().nextBoolean();
    }

    /**
     * 随机获取指定长度的字符串
     * 取样字符串 {@link RandomUtil#LOWER_UPPER_CASE_NUMBER}
     * @param length    随机获取字符串的长度
     * @return          随机字符串
     */
    public static String randomString(final int length) {
        return randomString(length, LOWER_UPPER_CASE_NUMBER);
    }


    /**
     * 随机从指定的取样字符串中获取指定长度的字符串
     * @param length    随机获取字符串的长度
     * @param str       取样字符串
     * @return          随机字符串
     */
    public static String randomString(final int length, final String str){
        if (length <= 0 || isEmpty(str)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int randomLength = str.length();
        for (int i = 0; i < length; i++) {
            int index = randomInt(randomLength);
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 随机获取long类型随机数,范围[0,Long.MAX_VALUE],不包含Long.MAX_VALUE
     * @return          随机数
     */
    public static long randomLong() {
        return randomLong(0,Long.MAX_VALUE);
    }

    /**
     * 随机获取long类型随机数,范围[0,max],不包含max
     * @param max       随机数最大值,不能小于或等于0,最大值不包含max
     * @return          返回long类型随机数
     */
    public static long randomLong(final long max) {
        return randomLong(0, max);
    }

    /**
     * 随机获取long类型随机数,范围[min,max],不包含max
     * max不能小于等于min
     * @param min       随机数最小值
     * @param max       随机数最大值,最大值不包含max
     * @return          long类型随机数
     */
    public static long randomLong(final long min, final long max) {
        return getRandom().nextLong(min, max);
    }

    /**
     * 随机获取一种颜色
     * @return      随机颜色
     */
    public static Color randomColor() {
        return new Color(randomInt(RGB_LIMIT), randomInt(RGB_LIMIT), randomInt(RGB_LIMIT));
    }
}
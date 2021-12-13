package cn.nxtools.common;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组测试方法
 */
public final class ArrayUtilTest {

    @Test
    public void testFillStr() {
        String[] arrays = new String[10];
        ArrayUtil.fill(arrays, "张");
        System.out.println(JsonUtil.toString(arrays));
        //["张","张","张","张","张","张","张","张","张","张"]
        byte[] bytes = new byte[0];
        System.out.println(ArrayUtil.isEmpty(bytes));
        //true
        int[] ints = new int[]{1};
        System.out.println(ArrayUtil.isEmpty(ints));
        //false
    }
}

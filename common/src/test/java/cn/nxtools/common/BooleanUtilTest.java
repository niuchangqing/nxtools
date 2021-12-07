package cn.nxtools.common;

import org.junit.Test;

/**
 * @description Boolean测试
 */
public final class BooleanUtilTest {

    @Test
    public void toBoolean() {
        Long a = null;
        System.out.println(BooleanUtil.toBoolean(a, true));
        Long b = 10000L;
        System.out.println(BooleanUtil.toBoolean(b, false));
    }
}

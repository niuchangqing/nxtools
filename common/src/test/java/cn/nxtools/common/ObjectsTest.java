package cn.nxtools.common;

import cn.nxtools.common.base.Objects;
import org.junit.Test;

/**
 * @description Objects工具测试
 */
public final class ObjectsTest {

    @Test
    public void testIsNullOrDefault() {
        String a = null;
        String a1 = Objects.defaultIfNull(a, "默认值");
        System.out.println(a1);
    }

    @Test
    public void testEquals() {
        String o1 = "123";
        String o2 = null;
        System.out.println(Objects.equals(o1, o2));
    }
}

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

    @Test
    public void testMap() {
        CollectionUtilTest.User user = null;
        String name = Objects.map(user, u -> u.getName());
        System.out.println(name);// null

        String name1 = Objects.map(user, u -> u.getName(), "lisi");
        System.out.println(name1);// lisi
    }

    @Test
    public void testMap1() {
        CollectionUtilTest.User user = new CollectionUtilTest.User();
        user.setId(1L);
        user.setName("zhangsan");
        String name = Objects.map(user, u -> u.getName());
        System.out.println(name);// zhangsan

        String name1 = Objects.map(user, u -> u.getName(), "lisi");
        System.out.println(name1);// zhangsan
    }
}

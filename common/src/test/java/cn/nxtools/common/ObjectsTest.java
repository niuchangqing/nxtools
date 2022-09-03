package cn.nxtools.common;

import cn.nxtools.common.base.Objects;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

        String a = null;
        String s = Objects.defaultIfNull(a, () -> "");
        Assert.assertEquals(s, "");

        Set<String> set = null;
        Set<String> objects = Objects.defaultIfNull(set, () -> new HashSet<>());
        Assert.assertNotNull(objects);
    }

    @Test
    public void testNonNullExec() {
        CollectionUtilTest.User user = null;
        Objects.nonNullExec(user, b -> b.setUpdateTime(LocalDateTime.now()));
        CollectionUtilTest.User user1 = new CollectionUtilTest.User();
        Long millis = 1662205523073L;
        Objects.nonNullExec(user1, e -> e.setCreateTime(LocalDateTimeUtil.ofMillis(millis)));
        Assert.assertTrue(user1.getCreateTime().compareTo(LocalDateTimeUtil.ofMillis(millis)) == 0);
    }
}

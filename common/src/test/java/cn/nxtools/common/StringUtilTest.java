package cn.nxtools.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niuchangqing
 */
public final class StringUtilTest {

    @Test
    public void testString() {
        String s = null;
        System.out.println(StringUtil.isNull(s));
        System.out.println(StringUtil.isEmpty(s));
        System.out.println(StringUtil.isNotEmpty(s));
        System.out.println(StringUtil.isNotNull(s));
        System.out.println("---------------------");
        String s1 = "";
        System.out.println(StringUtil.isNull(s1));
        System.out.println(StringUtil.isEmpty(s1));
        System.out.println(StringUtil.isNotEmpty(s1));
        System.out.println(StringUtil.isNotNull(s1));
        System.out.println("---------------------");
        String s2 = " ";
        System.out.println(StringUtil.isNull(s2));
        System.out.println(StringUtil.isEmpty(s2));
        System.out.println(StringUtil.isNotEmpty(s2));
        System.out.println(StringUtil.isNotNull(s2));
    }

    @Test
    public void testMap() {
        Map<String,String> map = null;
        System.out.println(CollectionUtil.isNull(map));
        System.out.println(CollectionUtil.isNotNull(map));
        System.out.println(CollectionUtil.isEmpty(map));
        System.out.println(CollectionUtil.isNotEmpty(map));
        System.out.println("---------------------");

        map = new HashMap<>();
        System.out.println(CollectionUtil.isNull(map));
        System.out.println(CollectionUtil.isNotNull(map));
        System.out.println(CollectionUtil.isEmpty(map));
        System.out.println(CollectionUtil.isNotEmpty(map));
        System.out.println("---------------------");

        map.put("name","zhang");
        System.out.println(CollectionUtil.isNull(map));
        System.out.println(CollectionUtil.isNotNull(map));
        System.out.println(CollectionUtil.isEmpty(map));
        System.out.println(CollectionUtil.isNotEmpty(map));
        System.out.println("---------------------");
    }
}

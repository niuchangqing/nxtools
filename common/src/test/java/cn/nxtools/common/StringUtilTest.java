package cn.nxtools.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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


    @Test
    public void testFormat() {
        String template = "iwoahut{}jowiajra{}}jjnja{}";
        System.out.println(StringUtil.format(template, "{}", "张张","111", "222", "333", "444"));
        //iwoahut张张jowiajra111}jjnja222 [333, 444]
    }

    @Test
    public void testFormatWith() {
        String template = "iwoahut%sjowiajra%s}jjnja%s";
        System.out.println(StringUtil.formatWith(template, "%s", "张张","111", "222", "333", "444"));
        //iwoahut张张jowiajra111}jjnja222 [333, 444]
    }

    @Test
    public void testLeftAppendChar() {
        String str = "100";
        System.out.println(StringUtil.leftAppend(str, 10, '0'));
        System.out.println(StringUtil.leftAppend(null, 5, 'a'));// = null;
        System.out.println(StringUtil.leftAppend("bc", -1, 'a'));//= "bc";
        System.out.println(StringUtil.leftAppend("bc", 5, 'a'));// = "aaabc";
        System.out.println(StringUtil.leftAppend("bc", 2, 'a'));// = "bc";
    }

    @Test
    public void testLeftAppendStr() {
        String str = "100";
        System.out.println(StringUtil.leftAppend(str, 10, "100"));//1001001100
        System.out.println(StringUtil.leftAppend(null, 5, "0"));//null
        System.out.println(StringUtil.leftAppend("bc", -1, "aa"));//bc
        System.out.println(StringUtil.leftAppend("bc", 5, "aa"));//aaabc
        System.out.println(StringUtil.leftAppend("bc", 2, "aa"));//bc
        System.out.println(StringUtil.leftAppend("bc", 5, ""));//bc
    }

    @Test
    public void testRightAppendChar() {
        String str = "100";
        System.out.println(StringUtil.rightAppend(str, 10, '0'));//1000000000
        System.out.println(StringUtil.rightAppend(null, 5, 'a'));// = null;
        System.out.println(StringUtil.rightAppend("bc", -1, 'a'));//= "bc";
        System.out.println(StringUtil.rightAppend("bc", 5, 'a'));// = "bcaaa";
        System.out.println(StringUtil.rightAppend("bc", 2, 'a'));// = "bc";
    }

    @Test
    public void testRightAppendStr() {
        String str = "100";
        System.out.println(StringUtil.rightAppend(str, 10, "100"));//1001001001
        System.out.println(StringUtil.rightAppend(null, 5, "0"));//null
        System.out.println(StringUtil.rightAppend("bc", -1, "aa"));//bc
        System.out.println(StringUtil.rightAppend("bc", 5, "aa"));//bcaaa
        System.out.println(StringUtil.rightAppend("bc", 2, "aa"));//bc
        System.out.println(StringUtil.rightAppend("bc", 5, ""));//bc
        String s = StringUtil.defaultIfEmpty("", "1");
        Assert.assertEquals(s, "1");
    }
}

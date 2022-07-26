package cn.nxtools.common;

import cn.nxtools.common.collect.Maps;
import cn.nxtools.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import cn.nxtools.common.base.Joiner;
import cn.nxtools.common.collect.Iterators;
import cn.nxtools.common.collect.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author niuchangqing
 */
public final class JoinerTest {

    @Test
    public void arrayToStringTest() {
        List<String> list = Lists.newLinkedList("2","3","1");
        String join = Joiner.on(",").join(list, 2, 100);
        System.out.println(join);

        List<User> userList = Lists.newArrayList();
        User user = new User("张三");
        userList.add(user);
        user = new User("李四");
        userList.add(user);
        String join1 = Joiner.on("|").join(userList);
        System.out.println(join1);

        String[] strings = new String[]{"2","3","1"};
        String join2 = Joiner.on(',').join(strings, 0, 2);
        System.out.println(join2);
        System.out.println(Joiner.on(",").join(userList.get(0),userList.get(1)) );
    }

    @Test
    public void arrayToStringTest1() {
        String[] strings = new String[]{"2","3","1"};
        System.out.println(Joiner.on(",").join(strings));

        List<String> list = Lists.newLinkedList("3","2","1");
        System.out.println(Joiner.on("-").join(list));
        System.out.println(Joiner.on(",").join(list,1,3));

        System.out.println(Joiner.on(",").join(list.iterator(), 0, Iterators.size(list.iterator())));
    }

    @Test
    public void dataTypeToStringTest() {
        int[] ints = new int[]{1,2,3};
        System.out.println(Joiner.on(',').join(ints));
        System.out.println(Joiner.on(",").join(ints,0,2));
        byte[] bytes = new byte[]{3,2,3,4};
        System.out.println(Joiner.on(",").join(bytes));
        System.out.println(Joiner.on(",").join(bytes,1,3));

        short[] shorts = new short[]{2,5,1,3};
        System.out.println(Joiner.on(",").join(shorts));
        System.out.println(Joiner.on(",").join(shorts,2,3));

        long[] longs = new long[]{2,3,100,2981};
        System.out.println(Joiner.on(',').join(longs));
        System.out.println(Joiner.on(',').join(longs,0,3));

        float[] floats = new float[]{12.3f,98.1f,9873.1f};
        System.out.println(Joiner.on(",").join(floats));
        System.out.println(Joiner.on(",").join(floats,1,2));

        double[] doubles = new double[]{98,983,9173,881};
        System.out.println(Joiner.on(",").join(doubles));
        System.out.println(Joiner.on(",").join(doubles,1,4));

        char[] chars = new char[]{'a','b','d'};
        System.out.println(Joiner.on(",").join(chars));
        System.out.println(Joiner.on(",").join(chars,0,2));

        boolean[] booleans = new boolean[]{true,false,false,true};
        System.out.println(Joiner.on("-").join(booleans));
        System.out.println(Joiner.on("-").join(booleans,1,3));
    }

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return JsonUtil.toString(this);
        }
    }


    @Test
    public void testJoiner() {
        String empty = "";

        String join1 = Joiner.on(",").join(null, null);
        Assert.assertEquals("null,null", join1);
        String join2 = Joiner.on(",").join("1", "2");
        Assert.assertEquals("1,2", join2);
        Integer[] integers = null;
        String join3 = Joiner.on(",").join(integers);
        Assert.assertNotNull(join3);
        Assert.assertEquals(empty, join3);
        Integer[] integers1 = new Integer[]{1,2};
        String join = Joiner.on(",").join(integers1, 0, integers1.length);
        Assert.assertEquals("1,2", join);
        User[] users = new User[]{new User("张三"), null};
        String join4 = Joiner.on(",").join(users);
        Assert.assertEquals("{\"name\":\"张三\"},null", join4);

        Iterable<?> iterable = null;
        String join5 = Joiner.on(",").join(iterable);
        Assert.assertNotNull(join5);
        Assert.assertEquals(empty, join5);
        String join6 = Joiner.on(",").join(Lists.newArrayList("1", "2"), 1, 2);
        Assert.assertEquals("2", join6);

        Iterator<?> iterator = null;
        String join7 = Joiner.on(',').join(iterator);
        Assert.assertNotNull(join7);
        Assert.assertEquals(empty, join7);
        String join8 = Joiner.on(",").join(Sets.newHashSet("1", "2").iterator(), 1, 2);
        Assert.assertEquals("2", join8);

        int[] ints = null;
        String join9 = Joiner.on(",").join(ints);
        Assert.assertNotNull(join9);
        Assert.assertEquals(empty, join9);
        int[] ints1 = new int[]{1,2};
        String join10 = Joiner.on(",").join(ints1);
        Assert.assertEquals(join10, "1,2");
        String join11 = Joiner.on(",").join(ints1, 1, 2);
        Assert.assertEquals(join11, "2");

        double[] doubles = null;
        String join12 = Joiner.on(',').join(doubles);
        Assert.assertNotNull(join12);
        Assert.assertEquals(empty, join12);
        double[] doubles1 = new double[]{1.2,3.2};
        String join13 = Joiner.on(',').join(doubles1);
        Assert.assertEquals("1.2,3.2", join13);
        String join14 = Joiner.on(',').join(doubles1, 1, 2);
        Assert.assertEquals("3.2", join14);
    }

    @Test
    public void testJoiner1() {
        List<String> list1 = Lists.newArrayList("1", "2", "3", "4");
        String str1 = Joiner.on(',').join(list1, 1, list1.size() - 1);
        System.out.println(str1);
        Assert.assertEquals("2,3", str1);

        List<String> list2 = Lists.newArrayList("1", "2", null, "3");
        String str2 = Joiner.on(',').skipNull().join(list2);
        Assert.assertEquals("1,2,3", str2);

        List<String> list3 = Lists.newArrayList("1", "2", null, "3");
        String str3 = Joiner.on(',').useForNull("哈哈").join(list3);
        Assert.assertEquals("1,2,哈哈,3", str3);

        Assert.assertThrows(UnsupportedOperationException.class,
                () -> Joiner.on(',').skipNull().useForNull("哈哈").join(list3));

        int[] ints = new int[]{1,2,3};
        String str4 = Joiner.on(',').join(ints, 2, 2);
        Assert.assertEquals("", str4);

        Assert.assertThrows(UnsupportedOperationException.class,
                () -> Joiner.on(",").useForNull("噢").skipNull().join(ints));

        Assert.assertThrows(UnsupportedOperationException.class,
                () -> Joiner.on(',').skipNull().skipNull().join(ints));
        Assert.assertThrows(UnsupportedOperationException.class,
                () -> Joiner.on(',').useForNull("哈哈").useForNull("嗯嗯").join(ints));
    }
}

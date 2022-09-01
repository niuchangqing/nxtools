package cn.nxtools.common;

import org.junit.Assert;
import org.junit.Test;
import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niuchangqing
 */
public final class SetsTest {
    @Test
    public void newConcurrentHashSetTest() {
        ConcurrentHashMap<String, Boolean> stringBooleanConcurrentHashMap = new ConcurrentHashMap<>();
        Set<String> strings = Collections.newSetFromMap(stringBooleanConcurrentHashMap);
        strings.add("1");
        strings.add("2");
        System.out.println(JsonUtil.toString(strings));
        System.out.println(JsonUtil.toString(stringBooleanConcurrentHashMap));

        Set<String> set1 = Sets.newConcurrentHashSet(10);
        set1.add("3");
        set1.add("4");
        set1.add("5");
        System.out.println(JsonUtil.toString(set1));

        Set<String> strings1 = Sets.newConcurrentHashSet("3","2","1");
        System.out.println(JsonUtil.toString(strings1));

        List<String> list = Lists.newArrayList("9","7","8");
        Set<String> strings2 = Sets.newConcurrentHashSet(list);
        System.out.println(JsonUtil.toString(strings2));
    }

    @Test
    public void treeSetTest() {
        TreeSet<String> treeSet = Sets.newTreeSet();
        treeSet.add("2");
        treeSet.add("1");
        System.out.println(JsonUtil.toString(treeSet));
    }

    @Test
    public void testListToSet() {
        List<String> list = Lists.newArrayList("1","1","2");
        Set<String> sets = Sets.newHashSet(list);
        System.out.println(JsonUtil.toString(sets));//["1","2"]

        List<String> l = null;
        HashSet<String> sets1 = Sets.newHashSet(l);
        Assert.assertNotNull(sets1);
        Assert.assertEquals(0, sets1.size());

        Iterator<String> iterator = null;
        HashSet<String> set2 = Sets.newHashSet(iterator);
        Assert.assertNotNull(set2);
        Assert.assertEquals(0, set2.size());
    }

    @Test
    public void testIntersection() {
        Set<String> set1 = null;
        Set<String> set2 = null;
        Set<String> result1 = Sets.intersection(set1, set2);
        Assert.assertTrue(result1.size() == 0);
        set1 = Sets.newHashSet("1", "2", "3");
        set2 = Sets.newHashSet("2", "3", "4", "5");
        Set<String> result2 = Sets.intersection(set1, set2);
        Assert.assertEquals(result2, Sets.newHashSet("2", "3"));
        Set<String> set3 = Sets.newHashSet("3", "4", "5", "6");
        Set<String> result3 = Sets.intersection(set1, set2, set3);
        Assert.assertEquals(result3, Sets.newHashSet("3"));
    }

    @Test
    public void testDifference() {
        Set<String> set1 = null;
        Set<String> set2 = null;
        Set<String> result1 = Sets.difference(set1, set2);
        Assert.assertTrue(result1.size() == 0);

        set1 = Sets.newHashSet("1", "2", "3", "4");
        Set<String> result2 = Sets.difference(set1, set2);
        Assert.assertEquals(result2, set1);

        set2 = Sets.newHashSet("3", "4", "5");
        Set<String> result3 = Sets.difference(set1, set2);
        Assert.assertEquals(result3, Sets.newHashSet("1", "2"));
    }

    @Test
    public void testUnion() {
        Set<String> set1 = null;
        Set<String> set2 = null;
        Set<String> union = Sets.union(set1, set2);
        Assert.assertTrue(union instanceof LinkedHashSet);
        Assert.assertTrue(union.size() == 0);

        set1 = Sets.newHashSet("1", "2");
        set2 = null;
        Set<String> result1 = Sets.union(set1, set2);
        Assert.assertEquals(result1, set1);

        set1 = null;
        set2 = Sets.newTreeSet("1", "2");
        Set<String> result2 = Sets.union(set1, set2);
        Assert.assertEquals(result2, set2);

        set1 = Sets.newHashSet("1", "2", "3");
        set2 = Sets.newTreeSet("3", "4", "5");
        Set<String> result3 = Sets.union(set1, set2);
        Assert.assertEquals(result3, Sets.newHashSet("1", "2", "3", "4", "5"));

        Set<String> set3 = null;
        Set<String> result4 = Sets.union(set1, set2, set3);
        Assert.assertEquals(result4, Sets.newHashSet("1", "2", "3", "4", "5"));

        set1 = Sets.newHashSet("1", "2", "3");
        set2 = Sets.newTreeSet("3", "4", "5");
        set3 = Sets.newLinkedHashSet("6");
        Set<String> result5 = Sets.union(set1, set2, set3, null);
        Assert.assertEquals(result5, Sets.newHashSet("1", "2", "3", "4", "5", "6"));
    }
}

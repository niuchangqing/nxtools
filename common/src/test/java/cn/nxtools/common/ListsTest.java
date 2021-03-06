package cn.nxtools.common;

import cn.nxtools.common.collect.Maps;
import cn.nxtools.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import cn.nxtools.common.collect.Lists;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author niuchangqing
 */
public final class ListsTest {

    @Test
    public void newArrayListTest() {
        Set<String> set = new HashSet<>();
        set.add("1");
        ArrayList<String> strings = Lists.newArrayList(set);
        System.out.println(JsonUtil.toString(strings));

        Vector<String> vector = new Vector<>();
        vector.add("1");

        ArrayList<String> strings1 = Lists.newArrayList(vector);
        System.out.println(JsonUtil.toString(strings1));

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("2");
        ArrayList<String> strings2 = Lists.newArrayList(treeSet);
        System.out.println(JsonUtil.toString(strings2));

        LinkedList<String> linkedList = Lists.newLinkedList();
        ArrayList<String> arrayList = Lists.newArrayList(linkedList);
        System.out.println(JsonUtil.toString(arrayList));
    }

    @Test
    public void newLinkedListTest() {
        LinkedList<String> strings = Lists.newLinkedList("1", "2", "3");
        System.out.println(JsonUtil.toString(strings));

        Set<String> set = new HashSet<>();
        set.add("1");
        LinkedList<String> strings1 = Lists.newLinkedList(set);
        System.out.println(JsonUtil.toString(strings1));

        Vector<String> vector = new Vector<>();
        vector.add("1");
        LinkedList<String> strings2 = Lists.newLinkedList(vector);
        System.out.println(JsonUtil.toString(strings2));

        ArrayList<String> arrayList = Lists.newArrayList("5","6","7");
        LinkedList<String> strings3 = Lists.newLinkedList(arrayList);
        System.out.println(JsonUtil.toString(strings3));

        Set<String> sets = null;
        LinkedList<String> linkedList = Lists.newLinkedList(sets);
        Assert.assertNotNull(linkedList);
        Assert.assertEquals(0, linkedList.size());
    }

    @Test
    public void partitionTest() {
        List<String> list = Lists.newArrayList();
        List<List<String>> partition1 = Lists.partition(list, 1);
        Assert.assertEquals(0, partition1.size());
        List<String> list1 = Lists.newLinkedList();
        List<List<String>> partition2 = Lists.partition(list1, 1);
        Assert.assertEquals(0, partition2.size());
        List<String> list2 = Lists.newArrayList("1","2","3");
        List<List<String>> partition3 = Lists.partition(list2, 2);
        Assert.assertEquals(2, partition3.size());
        List<String> list3 = Lists.newLinkedList("1","2","3");
        List<List<String>> partition4 = Lists.partition(list3, 2);
        Assert.assertEquals(2, partition4.size());

        List<String> list4 = Lists.newArrayList("1","2","3","4","5","6");
        List<List<String>> partition5 = Lists.partition(list4, 3);
        Assert.assertEquals(2, partition5.size());
        partition5.forEach(System.out::println);

        List<String> list5 = null;
        List<List<String>> partition6 = Lists.partition(list5, 10);
        Assert.assertEquals(partition6.size(), 0);
    }

    @Test
    public void copyOnWriteArrayListTest() {
        CopyOnWriteArrayList<String> list = Lists.newCopyOnWriteArrayList();
        Assert.assertEquals(0, list.size());
        Assert.assertEquals("[]", list.toString());
        ArrayList<String> list1 = Lists.newArrayList(list);
        Assert.assertEquals(0, list1.size());
        Assert.assertEquals("[]", list1.toString());
        LinkedList<String> list2 = Lists.newLinkedList(list);
        Assert.assertEquals(0, list2.size());
        Assert.assertEquals("[]", list2.toString());

        CopyOnWriteArrayList<Integer> list3 = Lists.newCopyOnWriteArrayList(Lists.newArrayList(1, 2));
        Assert.assertEquals(2, list3.size());
        CopyOnWriteArrayList<Integer> list4 = Lists.newCopyOnWriteArrayList(Lists.newLinkedList(1, 2));
        Assert.assertEquals(2, list4.size());

        HashSet<Integer> sets = Sets.newHashSet(1, 2);
        CopyOnWriteArrayList<Integer> list5 = Lists.newCopyOnWriteArrayList(sets.iterator());
        Assert.assertEquals(2, list5.size());
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.add("1");
        queue.add("2");
        CopyOnWriteArrayList<String> list6 = Lists.newCopyOnWriteArrayList(queue);
        Assert.assertEquals(2, list6.size());

        CopyOnWriteArrayList<String> list7 = Lists.newCopyOnWriteArrayList("1", "2", "3");
        Assert.assertEquals(3, list7.size());

        List<String> l = null;
        CopyOnWriteArrayList<String> list8 = Lists.newCopyOnWriteArrayList(l);
        Assert.assertNotNull(list8);
        Assert.assertEquals(0, list8.size());
    }
}

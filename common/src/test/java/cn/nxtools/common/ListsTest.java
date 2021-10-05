package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.collect.Lists;

import java.util.*;

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
    }
}

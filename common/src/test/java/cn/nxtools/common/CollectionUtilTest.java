package cn.nxtools.common;

import cn.nxtools.common.base.Objects;
import cn.nxtools.common.base.Preconditions;
import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;
import cn.nxtools.common.collect.Sets;
import cn.nxtools.common.exception.FileException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author niuchangqing
 */
public final class CollectionUtilTest {
    @Test
    public void testToMap() {
        List<CollectionUtilTest.User> list = Lists.newArrayList();
        list.add(new User(1L, "张三"));
        list.add(new User(2L, "李四"));
        Map<Long, User> map = Maps.newHashMap();
        map = CollectionUtil.toMap(map, list, User::getId, user -> user);
        System.out.println(JsonUtil.toString(map));
        //结果字符串: {"1":{"id":1,"name":"张三"},"2":{"id":2,"name":"李四"}}
    }

    @Test
    public void testToMap1() {
        List<CollectionUtilTest.User> list = Lists.newArrayList();
        list.add(new User(1L, "张三"));
        list.add(new User(2L, "李四"));
        Map<Long, User> map = CollectionUtil.toMap(list, k -> k.getId(), v -> v);
        System.out.println(JsonUtil.toString(map));
        //结果字符串: {"1":{"id":1,"name":"张三"},"2":{"id":2,"name":"李四"}}
    }


    public static class User {
        private Long id;

        private String name;

        private LocalDateTime createTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime updateTime;

        public User() {
        }

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        public LocalDateTime getCreateTime() {
            return this.createTime;
        }

        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
        }
        public LocalDateTime getUpdateTime() {
            return this.updateTime;
        }
    }

    @Test
    public void testCollectionUtil() {
        HashSet<Integer> set = null;
        HashSet<Integer> set1 = CollectionUtil.defaultIfNull(set, () -> new HashSet<>());
        Assert.assertNotNull(set1);
        Assert.assertEquals(0, set1.size());

        HashSet<String> hashSet = Sets.newHashSet();
        HashSet<String> objects = CollectionUtil.defaultIfEmpty(hashSet, () -> Sets.newHashSet("1"));
        Assert.assertEquals(objects.size(), 1);

        HashMap<String, Integer> map = null;
        HashMap<String, Integer> map1 = CollectionUtil.defaultIfNull(map, () -> Maps.newHashMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(map1.size(), 0);

        LinkedHashMap<String, String> map2 = Maps.newLinkedHashMap();
        LinkedHashMap<String, String> map4 = Maps.newLinkedHashMap();
        map4.put("1","1");
        HashMap<String, String> map3 = CollectionUtil.defaultIfEmpty(map2, () -> map4);
        Assert.assertEquals(map3.size(), 1);

        String object = null;
        String s1 = Objects.defaultIfNull(object, () -> StringUtil.EMPTY);
        Assert.assertNotNull(s1);
        Assert.assertEquals(s1, StringUtil.EMPTY);
        String s2 = Objects.defaultIfNull(object, StringUtil.EMPTY);
        Assert.assertNotNull(s2);
        Assert.assertEquals(s2, StringUtil.EMPTY);
    }


    @Test
    public void testSub() {
        List<String> list = null;
        List<String> sub1 = CollectionUtil.sub(list, 0, 10);
        Assert.assertNotNull(sub1);
        Assert.assertEquals(sub1.size(), 0);

        Set<String> set = null;
        List<String> sub2 = CollectionUtil.sub(set, 0, 10);
        Assert.assertNotNull(sub2);
        Assert.assertEquals(sub2.size(), 0);

        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<Integer> sub3 = CollectionUtil.sub(list1, -10, 3);
        Assert.assertNotNull(sub3);
        Assert.assertEquals(sub3.size(), 3);
        List<Integer> sub4 = CollectionUtil.sub(list1, 0, 6);
        Assert.assertEquals(sub4.size(), 6);
        Assert.assertEquals("[1, 2, 3, 4, 5, 6]", sub4.toString());
        List<Integer> sub5 = CollectionUtil.sub(list1, 0, 100);
        Assert.assertEquals(sub5.size(), 6);
        Assert.assertEquals("[1, 2, 3, 4, 5, 6]", sub5.toString());

        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        List<Integer> sub6 = CollectionUtil.sub(set1, -10, 3);
        Assert.assertNotNull(sub6);
        Assert.assertEquals(sub6.size(), 3);
        List<Integer> sub7 = CollectionUtil.sub(set1, 0, 6);
        Assert.assertEquals(sub7.size(), 6);
        Assert.assertEquals("[1, 2, 3, 4, 5, 6]", sub7.toString());
        List<Integer> sub8 = CollectionUtil.sub(set1, 0, 100);
        Assert.assertEquals(sub8.size(), 6);
        Assert.assertEquals("[1, 2, 3, 4, 5, 6]", sub8.toString());
    }
}

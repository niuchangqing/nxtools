package cn.nxtools.common;

import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
}

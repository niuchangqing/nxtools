package cn.nxtools.common;

import cn.nxtools.common.collect.Maps;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author niuchangqing
 */
public final class JsonUtilTest {

    @Test
    public void objToString() {
        Map<String,String> map = null;
        System.out.println(JsonUtil.toString(map) == null);;
    }

    @Test
    public void localDateTimeTest() {
        Map<Long, Object> map = Maps.newHashMap();
        map.put(1L, LocalDateTime.now());
        System.out.println(JsonUtil.toString(map));//{"1":"2022-05-09 16:01:53.417"}
    }

    @Test
    public void localDateTimeTest2() {
        CollectionUtilTest.User user = new CollectionUtilTest.User();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        String str = JsonUtil.toString(user);
        System.out.println(str);//{"id":null,"name":null,"createTime":"2022-05-09 16:08:00.417","updateTime":"2022-05-09 16:08:00"}
        CollectionUtilTest.User user1 = JsonUtil.toObj(str, new TypeReference<CollectionUtilTest.User>() {
        });
        System.out.println(user1.getCreateTime());//2022-05-09T16:08:00.417
        System.out.println(user1.getUpdateTime());//2022-05-09T16:08:00
    }
}

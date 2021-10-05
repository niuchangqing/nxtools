package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niuchangqing
 */
public final class MapsTest {
    @Test
    public void hashMapTest() {
        Map<String,String> map = new TreeMap<>();
        map.put("1","1");
        HashMap<String, String> hashMap = Maps.newHashMap(map);
        hashMap.put("1","2");
        System.out.println(JsonUtil.toString(map));
        System.out.println(JsonUtil.toString(hashMap));
    }

    @Test
    public void concurrentMapTest() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("1","1");
        map.put("2","2");
        ConcurrentMap<String, String> concurrentMap = Maps.newConcurrentMap(map);
        System.out.println(JsonUtil.toString(concurrentMap));
    }
}

package cn.nxtools.common;

import org.junit.Test;

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
}

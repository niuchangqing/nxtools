package cn.nxtools.common;

import org.junit.Test;

import java.awt.*;

/**
 * @author niuchangqing
 */
public final class RandomUtilTest {

    @Test
    public void randomTest(){
        int i1 = RandomUtil.randomInt(10);
        System.out.println(i1);
    }

    @Test
    public void randomLongTest() {
        long l = RandomUtil.randomLong(1);
        System.out.println(l);
    }

    @Test
    public void randomColorTest() {
        Color color = RandomUtil.randomColor();
    }

    @Test
    public void randomStrTest() {
        String s = RandomUtil.randomString(1,"");
        System.out.println(s);
    }
}

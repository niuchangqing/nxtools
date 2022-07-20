package cn.nxtools.common;

import org.junit.Assert;
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

    @Test
    public void randomDoubleTest() {
        double d1 = RandomUtil.randomDouble();
        Assert.assertTrue(d1 >= 0);
        double d2 = RandomUtil.randomDouble(10);
        Assert.assertTrue(d2 >= 0 && d2 < 10);
        double d3 = RandomUtil.randomDouble(1, 100);
        Assert.assertTrue(d3 >= 1 && d3 < 100);
    }
}

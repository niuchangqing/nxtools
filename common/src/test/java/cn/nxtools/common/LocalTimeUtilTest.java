package cn.nxtools.common;

import org.junit.Test;

import java.time.LocalTime;

/**
 * @author niuchangqing
 */
public final class LocalTimeUtilTest {

    @Test
    public void localTimeTest() {
        LocalTime now = LocalTimeUtil.now();
        String s = LocalTimeUtil.toString(now);
        String s1 = LocalTimeUtil.toString(now, LocalDateTimeUtil.HH_MM_SS);
        String s2 = LocalTimeUtil.toString(now, "HH:mm:ss.SSS");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        LocalTime localTime = LocalTimeUtil.ofString(s, LocalDateTimeUtil.HH_MM_SS);
        LocalTime localTime1 = LocalTimeUtil.ofString(s1, "HH:mm:ss");
        System.out.println(localTime.toString());
        System.out.println(localTime1.toString());
    }
}

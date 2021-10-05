package cn.nxtools.common;

import org.junit.Test;

import java.time.LocalDate;

/**
 * @author niuchangqing
 */
public final class LocalDateUtilTest {

    @Test
    public void LocalDateTest() {
        LocalDate now = LocalDateUtil.now();
        System.out.println(now.toString());
        String s = LocalDateUtil.toString(now);
        System.out.println(s);
        String s1 = LocalDateUtil.toString(now, LocalDateTimeUtil.YYYY_MM_DD_FORMAT);
        System.out.println(s1);
        LocalDate localDate = LocalDateUtil.ofString(s, LocalDateTimeUtil.YYYY_MM_DD);
        System.out.println(localDate.toString());
        LocalDate localDate1 = LocalDateUtil.ofString(s1, "yyyy/MM/dd");
        System.out.println(localDate1.toString());
    }
}

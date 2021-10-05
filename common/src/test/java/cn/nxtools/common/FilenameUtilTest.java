package cn.nxtools.common;

import org.junit.Test;

public final class FilenameUtilTest {
    @Test
    public void getNameTest() {
        String a = "/wahak/jiahwe/jia.jpg";
        String name = FilenameUtil.getName(a);
        System.out.println(name);
    }
}

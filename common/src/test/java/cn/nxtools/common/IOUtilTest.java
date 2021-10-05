package cn.nxtools.common;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IOUtilTest {

    @Test
    public void testToByte() throws IOException {
        String t = "测试";
        byte[] bytes = t.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        byte[] bytes1 = IOUtil.toByteArray(inputStream);
        String t1 = new String(bytes1);
        System.out.println(t1);
    }

    @Test
    public void toByteArrayTest() throws IOException {
        String test = "测试测试";
        byte[] bytes = test.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        InputStreamReader reader = new InputStreamReader(inputStream);

        byte[] bytes1 = IOUtil.toByteArray(reader);
        System.out.println(new String(bytes1));
    }
}

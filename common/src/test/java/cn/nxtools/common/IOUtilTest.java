package cn.nxtools.common;

import org.junit.Test;

import java.io.*;

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

    @Test
    public void testCopy() throws IOException {
        File file = new File("/Users/xuweihong/logs/raw.jpeg");
        byte[] bytes = FileUtil.readFileToByteArray(file);
        InputStream in = new ByteArrayInputStream(bytes);

        byte[] bytes1 = new byte[bytes.length];
        OutputStream out = new ByteArrayOutputStream(bytes.length);
        IOUtil.copy(in, out, 100);
        out.write(bytes1);
        System.out.println(bytes1.length == bytes.length);
        //true
    }
}

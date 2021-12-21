package cn.nxtools.common;

import cn.nxtools.common.compress.ZipWriter;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

/**
 * 压缩测试
 */
public final class ZipUtilTest {
    @Test
    public void testZip() {
        ZipUtil.zip("/Users/xuweihong/logs/20211028");
    }

    @Test
    public void testZip1() {
        ZipUtil.zip(new File("/Users/xuweihong/logs/20211028"),new File("/Users/xuweihong/logs/20211028.zip"));
    }

    @Test
    public void testZip2() {
        File zipFile = new File("/Users/xuweihong/logs/20211028.zip");
        File sourceFile = new File("/Users/xuweihong/logs/20211028");

        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //把111.jpeg文件添加到待压缩文件中
                return pathname.isDirectory() || pathname.getName().endsWith("111.jpeg");
            }
        };
        ZipUtil.zip(zipFile, Charset.defaultCharset(), false, filter, sourceFile);
    }

    @Test
    public void testZip3() {
        File zipFile = new File("/Users/xuweihong/logs/20211028.zip");

        FileInputStream in = FileUtil.openInputStream(new File("/Users/xuweihong/logs/WechatIMG24.jpeg"));
        ZipUtil.zip("WechatIMG241.jpeg", in, zipFile);
    }

    @Test
    public void testZip4() {
        File zipFile = new File("/Users/xuweihong/logs/20211028.zip");
        FileInputStream in = FileUtil.openInputStream(new File("/Users/xuweihong/logs/WechatIMG24.jpeg"));
        FileInputStream in1 = FileUtil.openInputStream(new File("/Users/xuweihong/logs/test.txt"));
        String[] paths = new String[]{"123.jpeg", "test1.txt"};
        ZipUtil.zip(paths, new InputStream[]{in, in1}, zipFile);
    }

    @Test
    public void testZip5() {
        File zipFile = new File("/Users/xuweihong/logs/20211028.zip");
        ZipOutputStream zipOutput = ZipWriter.open(zipFile, Charset.defaultCharset()).getZipOutput();
        FileInputStream in = FileUtil.openInputStream(new File("/Users/xuweihong/logs/WechatIMG24.jpeg"));
        ZipUtil.zip(zipOutput, new String[]{"111.jpeg"}, new InputStream[]{in});
    }

    @Test
    public void testZip6() {
        File zipFile = new File("/Users/xuweihong/logs/20211028.zip");
        OutputStream output = FileUtil.openOutputStream(zipFile,false);
        InputStream in = FileUtil.readFileToInputStream(new File("/Users/xuweihong/logs/WechatIMG24.jpeg"));
        ZipUtil.zip(output, new String[]{"111.jpeg"}, new InputStream[]{in});
    }
}

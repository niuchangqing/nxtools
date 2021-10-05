package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.base.Stopwatch;
import cn.nxtools.common.collect.Lists;

import java.io.File;
import java.util.List;

public final class FileUtilTest {
    @Test
    public void testMoveFile() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.moveFile(new File("/Users/xuweihong/logs/raw.jpg"), new File("/Users/xuweihong/logs1/raw1.jpg"));
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testMoveDirectory() {
        File source = new File("/Users/xuweihong/logs/raw");
        File target = new File("/Users/xuweihong/logs1/raw");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.moveDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testMoveFileToDirectory() {
        File source = new File("/Users/xuweihong/logs/raw.jpg");
        File target = new File("/Users/xuweihong/logs/raw_copy");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.moveFileToDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testMoveToDirectory() {
        File source = new File("/Users/xuweihong/logs/raw1.jpg");
        File target = new File("/Users/xuweihong/logs/raw_copy");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.moveToDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testMoveDirectoryToDirectory() {
        File source = new File("/Users/xuweihong/logs/raw");
        File target = new File("/Users/xuweihong/logs/raw_copy");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.moveDirectoryToDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testCopyFile() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.copyFile(new File("/Users/xuweihong/logs/raw.jpg"), new File("/Users/xuweihong/logs1/raw.jpg"));
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testDelete() {
        boolean flag = FileUtil.deleteQuietly(new File("/Users/xuweihong/logs/test1"));
        System.out.println(flag);
    }

    @Test
    public void testCleanDirectory() {
        boolean b = FileUtil.cleanDirectory(new File("/Users/xuweihong/logs/test1"));
        System.out.println(b);
    }

    @Test
    public void testCopyDirectory() {
        File source = new File("/Users/xuweihong/logs/raw");
        File target = new File("/Users/xuweihong/logs/raw_copy");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.copyDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testCopyFileToDirectory() {
        File source = new File("/Users/xuweihong/logs/raw1.jpg");
        File target = new File("/Users/xuweihong/logs/raw_copy");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.copyFileToDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testCopyDirectoryToDirectory() {
        File source = new File("/Users/xuweihong/logs/raw");
        File target = new File("/Users/xuweihong/logs/raw1/raw1");
        Stopwatch stopwatch = Stopwatch.createStarted();
        FileUtil.copyDirectoryToDirectory(source, target);
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }


    @Test
    public void testWriteStringToFile() {
        File file = new File("/Users/xuweihong/logs/test.txt");
        FileUtil.writeString("测试写入字符串",file, false);
        FileUtil.writeString("测试写入追加字符串",file, true);
        FileUtil.writeString("测试写入不一样的编码格式",file, "GBK");
        FileUtil.writeString("测试写入不一样的编码格式",file,true, "GBK");
    }

    @Test
    public void testWriteLinesToFile() {
        File file = new File("/Users/xuweihong/logs/test.txt");
        List<String> list = Lists.newArrayList();
        list.add("第一行数据");
        list.add("第二行数据");
//        FileUtil.writeLines(list, file);
//        FileUtil.writeLines(list, file, "GBK");
//        FileUtil.writeLines(list, file, true);
        FileUtil.writeLines(list, file, true,"UTF-8", "\n");
    }

    @Test
    public void testWriteByteToFile() {
        File file = new File("/Users/xuweihong/logs/test.txt");
        byte[] bytes = "输入数据".getBytes();
        FileUtil.write(bytes,file);
    }

    @Test
    public void testReadFileToByte() {
        File file = new File("/Users/xuweihong/logs/test.txt");
        byte[] bytes = FileUtil.readFileToByteArray(file);
        System.out.println(bytes.length);
    }

    @Test
    public void testReadFileToString() {
        File file = new File("/Users/xuweihong/logs/test.txt");
        String str = FileUtil.readFileToString(file);
        System.out.println(str);
    }
}

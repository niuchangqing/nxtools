package cn.nxtools.common;

import org.junit.Assert;
import org.junit.Test;
import cn.nxtools.common.base.Splitter;
import cn.nxtools.common.base.Stopwatch;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author niuchangqing
 */
public final class SplitterTest {

    @Test
    public void splitTest() {
        Stopwatch started = Stopwatch.createStarted();
        List<String> list = Splitter.on(",").splitToList("1,,,2");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(list));

        started.resetAndStart();
        String[] split = Splitter.on(",").split("1,2,3");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(split));

        started.resetAndStart();
        List<String> l = Splitter.on(',').splitToList("1,2,3");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(l));

        started.resetAndStart();
        List<String> list1 = Splitter.on("sapasaw").splitToList("qwsaiuqsaisapasawuqwesakdasapasawqwsaiuqsaiuqwesapasawsakdaqwsaiuqsapasawsaiuqwesakdasapasaw");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(list1));

        started.resetAndStart();
        String[] sas = "qwsaiuqsaisapasawuqwesakdasapasawqwsaiuqsaiuqwesapasawsakdaqwsaiuqsapasawsaiuqwesakdasapasaw".split("sapasaw");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(sas));

        started.resetAndStart();
        Stream<String> stringStream = Splitter.on("sapasaw").splitToStream("qwsaiuqsaisapasawuqwesakdasapasawqwsaiuqsaiuqwesapasawsakdaqwsaiuqsapasawsaiuqwesakdasapasaw");
        System.out.println(started.toString());
        System.out.println(JsonUtil.toString(stringStream.toArray()));
    }

    @Test
    public void mapSplitterTest() {
        String str = "page=1&pageSize=10&name=zhangsan&age=18&=3";
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(str);
        System.out.println(stopwatch.toString());
        System.out.println(JsonUtil.toString(split));
    }


    @Test
    public void testSplitter() {
        String str = "taw|asji|ji|ste";
        String[] split1 = Splitter.on('|').split(str);
        Assert.assertEquals(4, split1.length);
        Stream<String> split2 = Splitter.on('|').splitToStream(str);
        Assert.assertEquals(split2.count(), 4);
        List<String> split3 = Splitter.on('|').splitToList(str);
        Assert.assertEquals(split3.size(), 4);

        String str1 = null;
        String[] split4 = Splitter.on("|").split(str1);
        Assert.assertNotNull(split4);
        Assert.assertEquals(split4.length, 0);
        Stream<String> split5 = Splitter.on("|").splitToStream(str1);
        Assert.assertNotNull(split5);
        Assert.assertEquals(split5.count(), 0);
        List<String> split6 = Splitter.on("|").splitToList(str1);
        Assert.assertNotNull(split6);
        Assert.assertEquals(split6.size(), 0);

        StringBuilder stringBuilder = new StringBuilder("taw|asji|ji|ste");
        List<String> split7 = Splitter.on("|").splitToList(stringBuilder);
        Assert.assertNotNull(split7);
        Assert.assertEquals(split7.size(), 4);

        CharSequence str2 = "taw|asji|ji|ste";
        List<String> split8 = Splitter.on("|").splitToList(str2);
        Assert.assertNotNull(split8);
        Assert.assertEquals(split8.size(), 4);
    }
}

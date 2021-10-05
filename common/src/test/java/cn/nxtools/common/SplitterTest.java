package cn.nxtools.common;

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
}

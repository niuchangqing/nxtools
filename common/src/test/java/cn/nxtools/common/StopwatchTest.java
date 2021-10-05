package cn.nxtools.common;

import org.junit.Test;
import cn.nxtools.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author niuchangqing
 * 计时器测试
 */
public final class StopwatchTest {

    @Test
    public void stopwatchTest() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1500);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.stop();
        Thread.sleep(1000);
        stopwatch.start();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
//        System.out.println(stopwatch.toString());
        System.out.println(Stopwatch.createStarted().toString());
        System.out.println(Stopwatch.createStarted().toString());
        System.out.println(Stopwatch.createStarted().toString());
        System.out.println(Stopwatch.createStarted().toString());
        Thread.sleep(1000);
        System.out.println(Stopwatch.createStarted().toString());
    }

    @Test
    public void stopwatchTest1() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        stopwatch.stop();
        stopwatch.start();
        System.out.println(stopwatch.toString());
        stopwatch.reset();
        stopwatch.start();
        System.out.println(stopwatch.toString());
        stopwatch.resetAndStart();
        System.out.println(stopwatch.toString());
    }
}

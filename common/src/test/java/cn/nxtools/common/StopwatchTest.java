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

    @Test
    public void stopwatchTestTotal() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1000);
        stopwatch.resetAndStart();
        Thread.sleep(1000);
        stopwatch.stop();
        Thread.sleep(3000);
        stopwatch.start();
        stopwatch.stop();
//        stopwatch.resetAndStart();

        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));//结果: 1000
        System.out.println(stopwatch.totalElapsed(TimeUnit.MILLISECONDS));//结果: 2000
    }

    @Test
    public void stopwatchTestTotal1() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1000);
        stopwatch.stop();
        Thread.sleep(1000);
        stopwatch.start();
        Thread.sleep(2000);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));//结果: 3000
        System.out.println(stopwatch.totalElapsed(TimeUnit.MILLISECONDS));//结果: 3000

        stopwatch.reset();
        stopwatch.start();
        Thread.sleep(1000);
        System.out.println("==================");
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));//结果: 1000
        System.out.println(stopwatch.totalElapsed(TimeUnit.MILLISECONDS));//结果: 4000


        System.out.println("------------------");
        stopwatch.resetAndStart();
        Thread.sleep(1000);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));//结果: 1000
        System.out.println(stopwatch.totalElapsed(TimeUnit.MILLISECONDS));//结果: 5000
    }


    @Test
    public void stopwatchTestTotal2() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Thread.sleep(1000);
        stopwatch.resetAndStart();

        Thread.sleep(2000);
        stopwatch.resetAndStart();

        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));//0
        System.out.println(stopwatch.totalElapsed(TimeUnit.MILLISECONDS));// 3000
    }
}

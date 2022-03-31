package cn.nxtools.common.base;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import static cn.nxtools.common.base.Preconditions.checkState;

/**
 * @author niuchangqing
 * 计时器
 */
public final class Stopwatch {

    private final Ticker ticker;

    /**
     * 是否正在运行
     */
    private boolean isRunning;

    /**
     * 已过去的纳秒数
     */
    private long elapsedNanos;

    /**
     * 开始纳秒数
     */
    private long startNanos;

    /**
     * 总共运行纳秒数<br>
     * stop -> start 期间耗时不计算在内
     */
    private long totalElapsedNanos;

    private Stopwatch() {
        this.ticker = Ticker.nanoTicker();
    }

    /**
     * 开始计时器
     * @return          Stopwatch
     */
    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    /**
     * 计时器开始
     * @return          Stopwatch(计时器对象)
     */
    public Stopwatch start() {
        checkState(!isRunning, "This Stopwatch is already running");
        this.isRunning = true;
        this.startNanos = ticker.read();
        return this;
    }

    /**
     * 计时器停止
     * @return          Stopwatch
     */
    public Stopwatch stop() {
        long nanos = ticker.read();
        checkState(isRunning, "This Stopwatch is already stopped");
        this.isRunning = false;
        this.elapsedNanos += nanos - this.startNanos;
        return this;
    }

    /**
     * 重置计时器
     * 该方法重置计时器,当前计时器状态会处于stop状态
     * {@link #resetAndStart()}  resetAndStart()会重置计时器并开始
     * @return              Stopwatch(计时器对象)
     */
    public Stopwatch reset() {
        if (this.isRunning) {
            //计算耗时时间加入总耗时中
            this.totalElapsedNanos += ticker.read() - this.startNanos + this.elapsedNanos;
        }
        this.isRunning = false;
        this.elapsedNanos = 0;
        return this;
    }

    /**
     * 重置计时器并开始
     * {@link #reset()}     rest()会重置计时器,但是计时器会处于stop状态
     * @return              Stopwatch(计时器对象)
     */
    public Stopwatch resetAndStart() {
        reset().start();
        return this;
    }

    /**
     * 返回耗时时间,指定时间单位
     * @param timeUnit          时间单位
     * @return                  指定时间单位的数量
     */
    public long elapsed(TimeUnit timeUnit) {
        return timeUnit.convert(elapsedNanos(), NANOSECONDS);
    }

    private long elapsedNanos() {
        return isRunning ? ticker.read() - startNanos + elapsedNanos : elapsedNanos;
    }

    /**
     * <pre>
     *     总耗时
     *     返回耗时总时间,指定时间单位
     *     {@code stop -> start} 之间的耗时不计算在内
     * </pre>
     * @param timeUnit          时间单位
     * @return                  指定时间单位的数量
     * @since 1.0.4
     */
    public long totalElapsed(TimeUnit timeUnit) {
        return timeUnit.convert(this.totalElapsedNanos + elapsedNanos(), NANOSECONDS);
    }

    public String toString() {
        long nanos = elapsedNanos();
        TimeUnit timeUnit = chooseUnit(nanos);
        double v = (double) nanos / NANOSECONDS.convert(1,timeUnit);
        return String.format("%.6f%s",v,timeUnitAbbreviate(timeUnit));
    }


    private static TimeUnit chooseUnit(long nanos) {
        if (DAYS.convert(nanos, NANOSECONDS) > 0) {
            return DAYS;
        }
        if (HOURS.convert(nanos, NANOSECONDS) > 0) {
            return HOURS;
        }
        if (MINUTES.convert(nanos, NANOSECONDS) > 0) {
            return MINUTES;
        }
        if (SECONDS.convert(nanos, NANOSECONDS) > 0) {
            return SECONDS;
        }
        if (MILLISECONDS.convert(nanos, NANOSECONDS) > 0) {
            return MILLISECONDS;
        }
        if (MICROSECONDS.convert(nanos, NANOSECONDS) > 0) {
            return MICROSECONDS;
        }
        return NANOSECONDS;
    }

    private static String timeUnitAbbreviate(TimeUnit timeUnit) {
        switch (timeUnit) {
            case NANOSECONDS:
                return "ns";
            case MICROSECONDS:
                return "us";
            case MILLISECONDS:
                return "ms";
            case SECONDS:
                return "s";
            case MINUTES:
                return "min";
            case HOURS:
                return "h";
            case DAYS:
                return "d";
            default:
                throw new AssertionError();
        }
    }
}

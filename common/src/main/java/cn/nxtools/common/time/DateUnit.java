package cn.nxtools.common.time;

/**
 * @author niuchangqing
 * 日期单位
 * @since 1.0.4
 */
public enum DateUnit {
    /**
     * 毫秒
     */
    MILLISECOND(1),
    /**
     * 秒
     */
    SECOND(MILLISECOND.getMillis() * 1000),

    /**
     * 分钟
     */
    MINUTE(SECOND.getMillis() * 60),
    /**
     * 小时
     */
    HOUR(MINUTE.getMillis() * 60),

    /**
     * 天
     */
    DAY(HOUR.getMillis() * 24),

    /**
     * 周
     */
    WEEK(DAY.getMillis() * 7),
    ;

    /**
     * 毫秒数
     */
    private long millis;

    DateUnit(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return this.millis;
    }
}

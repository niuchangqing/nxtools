package cn.nxtools.common.base;

/**
 * @author niuchangqing
 * 计时器
 */
public abstract class Ticker {

    /**
     * 纳秒Ticker
     */
    private static final Ticker NANO_TICKER = new Ticker() {
        @Override
        protected long read() {
            return System.nanoTime();
        }
    };

    /**
     * 读取当前时间
     * {@link #NANO_TICKER}
     * @return          long
     */
    protected abstract long read();

    public static Ticker nanoTicker() {
        return NANO_TICKER;
    }
}

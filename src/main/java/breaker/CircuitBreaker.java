package breaker;

/**
 * @author: zhangyachong
 * @date: 2020-09-17
 * @description:
 */
public abstract class CircuitBreaker {

    protected int interval;
    protected int failTimes;
    protected long delay;

    protected volatile boolean open = false;

    public CircuitBreaker(int interval, int failTimes, long delay) {
        this.interval = interval;
        this.failTimes = failTimes;
        this.delay = delay;
    }
    public abstract void fallback();

    public int getInterval() {
        return interval;
    }

    public int getFailTimes() {
        return failTimes;
    }

    public boolean open() {
        return open;
    }

    public long getDelay() {
        return delay;
    }

    protected void setInterval(int interval) {
        this.interval = interval;
    }

    protected void setFailTimes(int failTimes) {
        this.failTimes = failTimes;
    }

    protected void setDelay(long delay) {
        this.delay = delay;
    }

    protected void setOpen(boolean open) {
        this.open = open;
    }
}

package breaker;

/**
 * @author: zhangyachong
 * @date: 2020-09-17
 * @description:
 */
public class DefaultCircuitBreaker extends CircuitBreaker{

    public DefaultCircuitBreaker(int interval, int failTimes, long delay) {
        super(interval, failTimes, delay);
    }

    @Override
    public void fallback() {

    }
}

package breaker;

import java.util.concurrent.Callable;

/**
 * @author: zhangyachong
 * @date: 2020-09-17
 * @description:
 */
public class CircuitBreakerRunner {

    public static void run(CircuitBreaker circuitBreaker, Callable<Boolean> callable) throws Exception {
        int recentFailTimes = 0;
        int recentCallTimes = 0;
        long curTimeStamp = System.currentTimeMillis();
        while (true) {
            while (circuitBreaker.open()) {
                if (System.currentTimeMillis() - curTimeStamp > circuitBreaker.getDelay()) {
                    circuitBreaker.setOpen(false);
                    recentFailTimes = 0;
                    recentCallTimes = 0;
                    continue;
                }
                circuitBreaker.fallback();
            }
            if (recentCallTimes >= circuitBreaker.getInterval() && recentFailTimes >= circuitBreaker.getFailTimes()) {
                circuitBreaker.setOpen(true);
                recentFailTimes = 0;
                recentCallTimes = 0;
                curTimeStamp = System.currentTimeMillis();
            } else {
                if (!callable.call()) {
                    recentFailTimes++;
                } else {
                    recentCallTimes++;
                }
            }
        }
    }
}
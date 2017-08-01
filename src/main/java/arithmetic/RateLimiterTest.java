package arithmetic;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	@org.junit.Test
	public void test() {
		RateLimiter rateLimiter1 = RateLimiter.create(1000L);
		System.err.println(rateLimiter1.getClass().getName());
		RateLimiter rateLimiter = RateLimiter.create(1000L, 100L, TimeUnit.MILLISECONDS);
		System.err.println(rateLimiter.getClass().getName());
	}
}

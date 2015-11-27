package j.ee.ejb.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class B {
	@AroundInvoke
	public Object aoptest(InvocationContext ic) throws Exception {
		long startTime = System.currentTimeMillis();
		try {
			Object[] obj = ic.getParameters();
			if (obj != null && obj.length >= 1) {
				StringBuilder sb = new StringBuilder();
				String s = String.valueOf(obj[0]);
				sb.append(s);
				sb.append("aop�Ѿ�������");
				return sb.toString();
			} else
				return ic.proceed();
		} finally {
			long endTime = System.currentTimeMillis() - startTime;
			System.out.println("Method " + ic.getMethod().getName() + " took " + endTime + " (ms)");
		}
	}
}

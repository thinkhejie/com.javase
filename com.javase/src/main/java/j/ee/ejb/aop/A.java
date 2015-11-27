package j.ee.ejb.aop;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless(mappedName = "ejb/aoptest", name = "ejbaop", description = "myaop")
@Remote(IA.class)
public class A implements IA {
	@Override
	@Interceptors(B.class)
	public String bookPassage(String arg) throws Exception {
		String s = "ejbaop";
		StringBuilder sb = new StringBuilder();
		sb.append(arg);
		sb.append(s);
		return sb.toString();
	}
}

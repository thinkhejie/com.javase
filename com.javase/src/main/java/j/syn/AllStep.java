package j.syn;

/*一个人爬楼梯，一步可以迈一级，二级，三级台阶，如果楼梯有N级，编写程序，输出所有走法。*/
/*
 * 斐波纳契
 f(n) = 1 (n = 1);  
 f(n) = 2 (n = 2);
 f(n) = 4 (n = 3);
 f(n) = f(n-3) + f(n-2) + f(n-1) (n>3); 
*/
public class AllStep {

	public static void main(String[] args) {
		System.out.println(new AllStep().goStep(5));
	}

	public int goStep(int args) {
		if (args == 0) {
			return 0;
		} else if (args == 1) {
			return 1;
		} else if (args == 2) {
			return 2;
		} else if (args == 3) {
			return 4;
		} else {
			return goStep(args - 1) + goStep(args - 2) + goStep(args - 3);
		}
	}
}

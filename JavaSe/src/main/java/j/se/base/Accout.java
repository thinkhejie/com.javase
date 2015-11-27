package j.se.base;

public class Accout {
	/** 
	* 账户名 
	*/
	String name;
	/** 
	* 账号余额 
	*/
	double money;
	/** 
	* 存钱操作常量 
	*/
	static final int save = 0;
	/** 
	* 取钱操作常量 
	*/
	static final int sub = 1;
	/** 
	* 账号操作方式 
	*/
	int x;

	/** 
	* 要操作的数 
	*/
	double number;//要操作的数 

	Accout() {
	};//无参数的构造函数 

	Accout(String name, double x) {
		this.name = name;
		money = x;
	}

	/** 
	* 设置要操作的钱的值 
	* @param number 对账号操作的数目 
	*/
	public void setNumber(double number) {
		this.number = number;
	}

	/** 
	* 获取操作账号的数目 
	* @return  操作账号的数目 
	*/
	public double getNumber() {
		return number;
	}

	/** 
	* 设置操作账号的方式 
	* @param x 设置操作账号的方式
	* x :1 取钱 
	* x: 0 存钱 
	*/
	public void setOp(int x) {
		this.x = x;
	}

	/** 
	* 获得账号操作方式 
	* @return 返回操作账号的方式 
	*/
	public int getOp() {
		return x;
	}

	/** 
	* 返回账号余额 
	* @return 账号余额 
	*/
	public double getMoney() {
		return money;
	}

	/** 
	* 设置账号余额 
	* @param money  账号余额 
	*/
	public void setMoney(double money) {
		this.money = money;
	}

	/** 
	* 获取账号名 
	* @return  开户名 
	*/
	public String getName() {
		return name;
	}

	/** 
	* 设置账号名 
	* @param name 账号名 
	*/
	public void setName(String name) {
		this.name = name;
	}

	/** 
	* 相应账号的操作过程 
	*/
	public synchronized void op() {
		//如果是存钱，那么就把存的钱加入到账户 
		if (getOp() == save) {
			setMoney(getMoney() + getNumber());
			System.out.println("存钱成功，现在账号上有:" + getMoney());
		}
		//取钱 
		if (getOp() == sub) {
			//1.选判断账号是否有足够余额 
			if (number > getMoney())
				throw new ArithmeticException("账户余额不足账户余额:" + getMoney());
			else {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//扣除相应操作数 
				setMoney(getMoney() - getNumber());
				System.out.println(Thread.currentThread().getName() + "你取到" + getNumber() + "元\n" + "帐户余额:" + getMoney());
			}
		}
	}
}

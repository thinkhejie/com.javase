package j.se.base;

public class RegexData {

	//非负整数（正整数 + 0）
	public static final String number = "\\d+";
	//正整数
	public static final String s1 = "^[0-9]*[1-9][0-9]*$";
	//非正整数（负整数 + 0）
	//public static final String s2   ="^((-\d+)|(0+))$";
	//负整数
	public static final String s4 = "^-[0-9]*[1-9][0-9]*$";
	//整数
	//public static final String s5 = "^-?\d+$";　　　
	//非负浮点数（正浮点数 + 0）
	//public static final String s6  = "^\d+(\.\d+)?$";
	//public static final String s7  = "^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$";　　//正浮点数
	//public static final String s8  = "^((-\d+(\.\d+)?)|(0+(\.0+)?))$";　　//非正浮点数（负浮点数 + 0）
	//public static final String s9  = "^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";　　//负浮点数
	//public static final String s10 = "^(-?\d+)(\.\d+)?$";　　//浮点数
	//public static final String s11 = "^[A-Za-z]+$";　　//由26个英文字母组成的字符串
	//public static final String s12 = "^[A-Z]+$";　　//由26个英文字母的大写组成的字符串
	//public static final String s33 = "^[a-z]+$"　;　//由26个英文字母的小写组成的字符串
	//public static final String s323 = "^[A-Za-z0-9]+$"　　//由数字和26个英文字母组成的字符串
	// public static final String s844 = "^\w+$";　　//由数字、26个英文字母或者下划线组成的字符串
	//public static final String s434 = "^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$";　　　　//email地址
	//public static final String s433 = "^[a-zA-z]+://(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$";　　//url 

}

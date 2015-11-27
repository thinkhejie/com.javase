package queue.test;

public class Cow {
	int born_year; //母牛的出生年 

	public Cow(int _born_year) {
		born_year = _born_year;
	}

	public boolean bred(int current_year) {
		//如果母牛已经4岁了，它就会生一头小母牛 
		if (current_year >= (born_year + 4))
			return true;
		else
			return false;
	}
}
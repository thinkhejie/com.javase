package ailk.oa.item;

public class AccInfo {
	private String acc = "";
	private boolean autoLogin;
	private int id;
	private String latestLoginTime = "";
	private String pwd = "";

	public String getAcc() {
		return this.acc;
	}

	public int getId() {
		return this.id;
	}

	public String getLatestLoginTime() {
		return this.latestLoginTime;
	}

	public String getPwd() {
		return this.pwd;
	}

	public boolean isAutoLogin() {
		return this.autoLogin;
	}

	public void setAcc(String paramString) {
		this.acc = paramString;
	}

	public void setAutoLogin(boolean paramBoolean) {
		this.autoLogin = paramBoolean;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setLatestLoginTime(String paramString) {
		this.latestLoginTime = paramString;
	}

	public void setPwd(String paramString) {
		this.pwd = paramString;
	}

	@Override
	public String toString() {
		StringBuilder localStringBuilder1 = new StringBuilder("acc=");
		String str1 = this.acc;
		StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(",pwd=");
		String str2 = this.pwd;
		return str2;
	}
}

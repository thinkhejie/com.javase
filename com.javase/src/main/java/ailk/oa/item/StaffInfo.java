package ailk.oa.item;

public class StaffInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4820842616078726303L;
	private String branch = "";
	private String email = "";
	private String employeeNumber = "";
	private int id;
	private String lastName = "";
	private String local = "";
	private String mobile = "";
	private String ntAccount = "";
	private String office = "";
	private String orgName = "";
	private String position = "";

	public String getBranch() {
		return this.branch;
	}

	public String getEmail() {
		return this.email;
	}

	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	public int getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getLocal() {
		return this.local;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getNtAccount() {
		return this.ntAccount;
	}

	public String getOffice() {
		return this.office;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setBranch(String paramString) {
		this.branch = paramString;
	}

	public void setEmail(String paramString) {
		this.email = paramString;
	}

	public void setEmployeeNumber(String paramString) {
		this.employeeNumber = paramString;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setLastName(String paramString) {
		this.lastName = paramString;
	}

	public void setLocal(String paramString) {
		this.local = paramString;
	}

	public void setMobile(String paramString) {
		this.mobile = paramString;
	}

	public void setNtAccount(String paramString) {
		this.ntAccount = paramString;
	}

	public void setOffice(String paramString) {
		this.office = paramString;
	}

	public void setOrgName(String paramString) {
		this.orgName = paramString;
	}

	public void setPosition(String paramString) {
		this.position = paramString;
	}

	@Override
	public String toString() {
		StringBuilder localStringBuilder1 = new StringBuilder("name=");
		String str1 = this.lastName;
		StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(",number=");
		String str2 = this.employeeNumber;
		StringBuilder localStringBuilder3 = localStringBuilder2.append(str2).append(",orgNmae=");
		String str3 = this.orgName;
		StringBuilder localStringBuilder4 = localStringBuilder3.append(str3).append(",local=");
		String str4 = this.local;
		StringBuilder localStringBuilder5 = localStringBuilder4.append(str4).append("office=");
		String str5 = this.office;
		StringBuilder localStringBuilder6 = localStringBuilder5.append(str5).append(",mobile=");
		String str6 = this.mobile;
		StringBuilder localStringBuilder7 = localStringBuilder6.append(str6).append(",account=");
		String str7 = this.ntAccount;
		StringBuilder localStringBuilder8 = localStringBuilder7.append(str7).append(",email=");
		String str8 = this.email;
		StringBuilder localStringBuilder9 = localStringBuilder8.append(str8).append(",position=");
		String str9 = this.position;
		StringBuilder localStringBuilder10 = localStringBuilder9.append(str9).append(",branch=");
		String str10 = this.branch;
		return str10;
	}
}

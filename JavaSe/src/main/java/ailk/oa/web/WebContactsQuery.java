package ailk.oa.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebContactsQuery {
	private String acc = "";
	AilkWebHunter awh;
	private String pageParam = "&txtPage=";
	private String paramBase = "?txtCmd=search&txtCondition=WHERE ";
	private String paramExt = "";
	private String pwd = "";
	private int totalPages = 0;

	public WebContactsQuery(String paramString1, String pwd) {
		String str = "ailk\\" + paramString1;
		this.acc = str;
		this.pwd = pwd;
		AilkWebHunter localAilkWebHunter = new AilkWebHunter(paramString1, pwd);
		this.awh = localAilkWebHunter;
	}

	public String formatUrl(String paramString1, String paramString2) throws UnsupportedEncodingException {
		this.paramExt = "";
		//if (paramString1.equals("0")) {
		//if (!StringOperate.isContainsChinese(paramString2))
		//String str1 = String.valueOf(this.paramExt);
		StringBuilder sb = new StringBuilder(paramString2).append("EI.LAST_NAME like '%25");
		String str2 = URLEncoder.encode(paramString2, "GB2312");
		String str3 = str2 + "%25'";
		this.paramExt = str3;
		//}
		String valueString = "maym2";
		//String txtCondition = "WHERE EI.EMPLOYEE_NUMBER LIKE '%"+ valueString+"%' OR  EI.LAST_NAME  LIKE '%" + valueString + "%'  OR EI.OFFICE LIKE '%" + valueString + "%' OR EI.MOBILE LIKE '%" + valueString + "%' OR EI.NT_ACCOUNT LIKE '%" + valueString + "%' OR EI.ORG_NAME   LIKE '%" + valueString + "%'";
		String txtCondition = "UPPER(EI.NT_ACCOUNT) LIKE '" + valueString + "%'";//+ " upper(EI.EMPLOYEE_NUMBER) LIKE '%"+ + "%'";
		StringBuilder localStringBuilder2 = new StringBuilder("http://intra.asiainfo-linkage.com/misonline/address/employee_search.asp");
		localStringBuilder2.append(this.paramBase);
		localStringBuilder2.append(txtCondition);
		String str5 = this.paramExt;
		str5.replaceAll(" ", "%20");
		System.out.println(localStringBuilder2.toString());
		return localStringBuilder2.toString();
		//			label126: if (StringOperate.isNumeric(paramString2)) {
		//				if (paramString2.length() > 7) {
		//					String str6 = String.valueOf(this.paramExt);
		//					String str7 = str6 + "EI.MOBILE like'%25" + paramString2 + "%25'";
		//					this.paramExt = str7;
		//					continue;
		//				}
		//				String str8 = String.valueOf(this.paramExt);
		//				String str9 = str8 + "EI.EMPLOYEE_NUMBER like'%25" + paramString2 + "%25'";
		//				this.paramExt = str9;
		//				continue;
		//			}
		//			String str10 = String.valueOf(this.paramExt);
		//			StringBuilder localStringBuilder4 = new StringBuilder(str10).append("upper(EI.NT_ACCOUNT) like '%25");
		//			String str11 = paramString2.toUpperCase();
		//			String str12 = str11 + "%25'";
		//			this.paramExt = str12;
		//return localStringBuilder3.toString();
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	/**
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 * @throws Exception
	 */
	//	public ArrayList parsePage(String paramString1, String paramString2, String paramString3) throws Exception {
	//		ArrayList localArrayList = new ArrayList();
	//		try {
	//			String str1 = String.valueOf(formatUrl(paramString1, paramString2));
	//			String str2 = this.pageParam + paramString3;
	//			//String str3 = "url_string=" + str1 + str2;
	//			String str3 = str1 + str2;
	//			InputStream localInputStream = this.awh.getWebStream(str3.replaceAll(" ", "%20"));
	//			if (localInputStream == null) {
	//				return new ArrayList(0);
	//			}
	//			ContactsPageParse localContactsPageParse = new ContactsPageParse();
	//			boolean bool = paramString3.equals("1");
	//			if (bool) {
	//				localArrayList = localContactsPageParse.getStaffInfoViaFirstPage(localInputStream);
	//				int j = localContactsPageParse.getTotalpage();
	//				this.totalPages = j;
	//			}
	//			localArrayList = localContactsPageParse.getStaffInfoViaPage(localInputStream);
	//		} catch (MalformedURLException localMalformedURLException) {
	//			throw localMalformedURLException;
	//		} catch (IOException localIOException) {
	//			throw localIOException;
	//		}
	//		return localArrayList;
	//	}
	//
	//	public void setTotalPages(int paramInt) {
	//		this.totalPages = paramInt;
	//	}
}

package ailk.oa.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import ailk.oa.item.StaffInfo;

public class ContactsPageParse {
	private int totalpage = 0;

	public ArrayList<StaffInfo> getStaffInfoViaPage(InputStream paramInputStream) throws Exception {
		ArrayList<StaffInfo> localArrayList = new ArrayList<StaffInfo>();
		try {
			//这里的编码规则要与上面的相对应
			BufferedReader br = new BufferedReader(new InputStreamReader(paramInputStream, "GB18030"));
			Source localSource1 = new net.htmlparser.jericho.Source(br);
			paramInputStream.close();
			//1.0
			List<Element> localList1 = localSource1.getAllElements("table");
			//循环table
			Element table = null;
			for (int i = 0; i < localList1.size(); i++) {
				Element temp = localList1.get(i);
				//border="1" cellpadding="2" cellspacing="0"  width="95%" class="tableDataCell"
				String border = temp.getAttributeValue("border");
				String cellpadding = temp.getAttributeValue("cellpadding");
				String cellspacing = temp.getAttributeValue("cellspacing");
				String width = temp.getAttributeValue("width");
				String css = temp.getAttributeValue("class");
				if ("1".equals(border) && "2".equals(cellpadding) && "0".equals(cellspacing) && "95%".equals(width) && "tableDataCell".equals(css)) {
					table = temp;
					break;
				}
			}
			if (table == null) {
				return localArrayList;
			}
			Segment localSegment1 = table.getContent();
			String str1 = "tr";
			String str2 = "td";
			List<Element> trs = localSegment1.getAllElements(str1);
			if (trs == null || trs.size() < 3) {
				return localArrayList;
			}
			for (int m = 1; m < trs.size() - 1; m++) {
				Segment localSegment2 = trs.get(m).getContent();
				List<Element> localList5 = localSegment2.getAllElements(str2);
				//for (int n = 0; n < localList5.size(); n++) {
				StaffInfo localStaffInfo = new StaffInfo();
				String str3 = localList5.get(0).getContent().toString().replaceAll("&nbsp;", "");
				String str5 = getValue(str3);
				localStaffInfo.setLastName(str5);
				//
				String str7 = localList5.get(1).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setEmployeeNumber(getValue(str7));
				//
				String str9 = localList5.get(2).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setOrgName(getValue(str9));
				//
				String str11 = localList5.get(3).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setLocal(getValue(str11));

				//分机
				String str13 = localList5.get(4).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setOffice(getValue(str13));

				//
				String str15 = localList5.get(5).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setMobile(getValue(str15));

				//
				String str17 = localList5.get(6).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setNtAccount(getValue(str17));

				//
				String str21 = localList5.get(7).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setEmail(getValue(str21));

				//
				String str25 = localList5.get(8).getContent().toString().replaceAll("-", "").replaceAll("&nbsp;", "");
				localStaffInfo.setPosition(getValue(str25));

				//
				String str27 = localList5.get(9).getContent().toString().replaceAll("&nbsp;", "");
				localStaffInfo.setBranch(getValue(str27));

				System.out.println(localStaffInfo.toString());
				//localStaffInfo.setId(m);

				localArrayList.add(localStaffInfo);
				//}

			}
		} catch (MalformedURLException localMalformedURLException) {
			throw localMalformedURLException;
		} catch (IOException localIOException) {
			throw localIOException;
		}
		return localArrayList;
	}

	public int getTotalpage() {
		return this.totalpage;
	}

	public String getValue(String paramString) {
		String str = paramString;
		if (paramString.indexOf("</") > 0) {
			int i = paramString.indexOf("</");
			str = paramString.substring(0, i);
			int j = str.lastIndexOf(">");
			int m = str.length();
			str = str.substring(j + 1, m);
		}
		return str;
	}

	public void setTotalpage(int paramInt) {
		this.totalpage = paramInt;
	}
}

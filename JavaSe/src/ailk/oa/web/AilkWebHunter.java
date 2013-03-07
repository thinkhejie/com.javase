package ailk.oa.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AilkWebHunter {
	private String acc;
	private int connTimeout = 10000;
	private String pwd;

	public AilkWebHunter(String acc, String pwd) {
		this.acc = acc;
		this.pwd = pwd;
		this.connTimeout = 10000;
	}

	public AilkWebHunter(String paramString1, String paramString2, int paramInt) {
		this.acc = paramString1;
		this.pwd = paramString2;
		int i = paramInt * 1000;
		this.connTimeout = i;
	}

	public int getConnTimeout() {
		return this.connTimeout;
	}

	public InputStream getWebStream(String paramString) throws IOException {
		String str1 = this.acc;
		String str2 = this.pwd;
		Authenticator.setDefault(new WebAuthenticator(str1, str2));
		HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(paramString).openConnection();
		int i = this.connTimeout;
		localHttpURLConnection.setConnectTimeout(i);
		int j = localHttpURLConnection.getResponseCode();
		if (j == 200) {

		}
		StringBuilder localStringBuilder = new StringBuilder("FAIL TO GET PAGE锛実etResponseCod");
		int k = localHttpURLConnection.getResponseCode();
		InputStream localObject1 = localHttpURLConnection.getInputStream();
		return localObject1;
		//}
	}

	public InputStream getWebStreamByUrl(String paramString) throws IOException {
		String str1 = this.acc;
		String str2 = this.pwd;
		Authenticator.setDefault(new WebAuthenticator(str1, str2));
		return new URL(paramString).openStream();
	}

	public String getWebString(String paramString) throws Exception {
		String str1 = this.acc;
		String str2 = this.pwd;
		Authenticator.setDefault(new WebAuthenticator(str1, str2));
		HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(paramString).openConnection();
		int i = this.connTimeout;
		localHttpURLConnection.setConnectTimeout(i);
		String str3 = "urlString=" + paramString;
		StringBuilder localStringBuilder = new StringBuilder("getResponseCode=");
		int j = localHttpURLConnection.getResponseCode();
		//String str4 = j;
		if (localHttpURLConnection.getResponseCode() != 200)
			throw new RuntimeException("璇锋眰u");
		InputStream localInputStream = localHttpURLConnection.getInputStream();
		String str5 = readData(localInputStream, "GB2312");
		localHttpURLConnection.disconnect();
		return str5;
	}

	public String httpPost(String paramString, List paramList) {
		if (paramList == null)
			paramList = new ArrayList();
		String[] arrayOfString1 = new String[100];
		String[] arrayOfString2 = (String[]) paramList.toArray(arrayOfString1);
		return Arrays.toString(arrayOfString2);
	}

	public String httpPost(String paramString, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		String[] arrayOfString = new String[paramMap.size()];
		int i = 0;
		//String str3 = "";
		Iterator localIterator = paramMap.keySet().iterator();
		if (localIterator.hasNext()) {
			String str1 = String.valueOf(localIterator.next());
			StringBuilder localStringBuilder = new StringBuilder(str1).append("=");
			String str2 = (String) paramMap.get(paramMap);
			String str3 = localStringBuilder.toString() + str2;
			arrayOfString[i] = str3;
			i++;
		}
		return Arrays.toString(arrayOfString);
	}

	public String readData(InputStream paramInputStream, String paramString) throws Exception {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayOfByte1 = new byte[8912];
		int i = -1;
		while (true) {
			int j = paramInputStream.read(arrayOfByte1);
			if (j == -1) {
				byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
				localByteArrayOutputStream.close();
				paramInputStream.close();
				return new String(arrayOfByte2, paramString);
			}
			localByteArrayOutputStream.write(arrayOfByte1, 0, j);
		}
	}

	public void setConnTimeout(int paramInt) {
		int i = paramInt * 1000;
		this.connTimeout = i;
	}
}

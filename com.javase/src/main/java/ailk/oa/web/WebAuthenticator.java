package ailk.oa.web;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class WebAuthenticator extends Authenticator {
	private String acc = "";
	private String pwd = "";

	public WebAuthenticator(String paramString1, String paramString2) {
		String str = null;
		if ((paramString1 != null) && (!paramString1.equals("")) && (!paramString1.toUpperCase().startsWith("ailk\\")))
			acc = "ailk\\" + paramString1;
		else {
			acc = paramString1;
		}
		//for (this.acc = str;;) {
		this.pwd = paramString2;
		//return;
		//}
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		String str = this.acc;
		char[] arrayOfChar = this.pwd.toCharArray();
		return new PasswordAuthentication(str, arrayOfChar);
	}
}
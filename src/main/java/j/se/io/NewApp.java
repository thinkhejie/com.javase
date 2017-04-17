package j.se.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class NewApp {

	private static String BUILDIN_COMMANDS_CLASS_FINENAME = "META-INF/MANIFEST.MFL";
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		try {
			//System/Library/Java/Extensions/MRJToolkit.jar!/META-INF/MANIFEST.MF
			//{Created-By=1.6.0_65-b14-466-11M4802 (Apple Inc.), Manifest-Version=1.0, Ant-Version=Apache Ant 1.8.4}
			//Properties prop = new Properties();
			//prop.load(NewApp.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"));
			//System.err.println(prop.getProperty("Manifest-Version"));
			//Properties prop1 = new Properties();
			//prop1.load(NewApp.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
			//System.err.println(prop1.getProperty("Manifest-Version"));
			InputStream is = NewApp.class.getClassLoader().getResourceAsStream(BUILDIN_COMMANDS_CLASS_FINENAME);
			prop.load(is);
			System.err.println(prop);
			BufferedReader reader = new BufferedReader(new InputStreamReader(NewApp.class.getClassLoader().getResourceAsStream(BUILDIN_COMMANDS_CLASS_FINENAME)));
	        String line = null;
            while ((line = reader.readLine()) != null) {
            	System.err.println(line);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

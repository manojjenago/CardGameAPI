
package com.cucumber.framework.configreader;

import java.util.Properties;

import org.apache.log4j.Level;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.utility.ResourceHelper;


/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public class PropertyFileReader implements IconfigReader {
	
	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			prop.load(ResourceHelper
					.getResourcePathInputStream("configfile/"
							+ "config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public PropertyFileReader(String fileName) {

		prop = new Properties();
		try {
			prop.load(ResourceHelper
					.getResourcePathInputStream("configfile/"
							+ fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public String getUserName(String Username) {return prop.getProperty(Username);
	}

	public String getPassword(String Password) {
		return prop.getProperty(Password);
	}

	public String getWebsite(String WebsiteName) {
		return prop.getProperty(WebsiteName);
	}

	public String getBinaryPath(String driverPath) {return prop.getProperty(driverPath);}
	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}
	public String getBinaryPath() { return prop.getProperty("driverPath");}

	@Override
	public String getHeadLess() {
		return prop.getProperty("headLess");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}
	
	public String getDbType() {
		return prop.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return prop.getProperty("DtaBase.ConnectionStr");
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}

//	@Override
//	public String BrowserBinaryPath() {
//		return prop.getProperty("driverPath");
//	}

	public Level getLoggerLevel() {
		
		switch (prop.getProperty("Logger.Level")) {
		
		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}

}

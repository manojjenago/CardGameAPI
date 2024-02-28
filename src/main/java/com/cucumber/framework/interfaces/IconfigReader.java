
package com.cucumber.framework.interfaces;

import com.cucumber.framework.configuration.browser.BrowserType;
/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public interface IconfigReader {

	public String getUserName(String Username);
	public String getPassword(String Password);
	public String getWebsite(String WebsiteName);
	public String getBinaryPath(String binaryPath);

	public String getBinaryPath();

	public String getHeadLess();
	public String getUserName();
	public String getPassword();
	public String getWebsite();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public BrowserType getBrowser();

}

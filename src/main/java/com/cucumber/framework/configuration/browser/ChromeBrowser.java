
package com.cucumber.framework.configuration.browser;

import com.cucumber.framework.settings.ObjectRepo;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.framework.utility.ResourceHelper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public class ChromeBrowser {

	public Capabilities getChromeCapabilities() {
		ChromeOptions option = new ChromeOptions();

		String driverbinPath = ObjectRepo.reader.getBinaryPath();
		option.setBinary(driverbinPath);
		option.addArguments("start-maximized");
//		System.out.println("Driver Binary Path::="+ResourceHelper.getResourcePath("driver/") );

		System.out.println("Driver Binary Path::="+ driverbinPath );
//		System.out.println("path::="+ResourceHelper.getResourcePath("driver/") );
		String headless = ObjectRepo.reader.getHeadLess();
		if (headless.equalsIgnoreCase("TRUE"))
		{
			option.addArguments("--headless");
		}
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		return chrome;
	}

	public WebDriver getChromeDriver(Capabilities cap) {
		System.out.println("path::="+ResourceHelper.getResourcePath("driver/") );
		System.setProperty("webdriver.chrome.driver",
				ResourceHelper.getResourcePath("driver/chromedriver.exe"));
		System.setProperty("webdriver.chrome.logfile",
				ResourceHelper.getResourcePath("logs/chromelogs/")
						+ "chromelog" + DateTimeHelper.getCurrentDateTime()
						+ ".log");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

		return new ChromeDriver(cap);
	}
	
	public WebDriver getChromeDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}

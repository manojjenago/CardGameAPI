package com.cucumber.framework.helper;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.configuration.browser.*;
import com.cucumber.framework.exception.NoSutiableDriverFoundException;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.settings.ObjectRepo;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */

public class InitializeWebDrive {

	private Logger oLog = LoggerHelper.getLogger(InitializeWebDrive.class);

	public InitializeWebDrive(PropertyFileReader reader) {
		ObjectRepo.reader = reader;
	}

	public WebDriver standAloneStepUp(BrowserType bType) throws Exception {
		try {
			oLog.info(bType);

			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox
						.getFirefoxCapabilities());

			case HtmlUnitDriver:
				HtmlUnitBrowser htmlUnit = HtmlUnitBrowser.class.newInstance();
				return htmlUnit.getHtmlUnitDriver(htmlUnit
						.getHtmlUnitDriverCapabilities());

			case Iexplorer:
				IExploreBrowser iExplore = IExploreBrowser.class.newInstance();
				return iExplore.getIExplorerDriver(iExplore
						.getIExplorerCapabilities());

			case PhantomJs:
				PhantomJsBrowser jsBrowser = PhantomJsBrowser.class
						.newInstance();
				return jsBrowser.getPhantomJsDriver(
						jsBrowser.getPhantomJsService(),
						jsBrowser.getPhantomJsCapability());

			default:
				throw new NoSutiableDriverFoundException(" Driver Not Found : "
						+ ObjectRepo.reader.getBrowser());
			}
		} catch (Exception e) {
			oLog.equals(e);
			throw e;
		}
	}
	
	@Before({"~@firefox","~@chrome","~@phantomjs","~@iexplorer"})
	public void before() throws Exception {
		setUpDriver(ObjectRepo.reader.getBrowser());
		oLog.info(ObjectRepo.reader.getBrowser());
	}

	@After({"~@firefox","~@chrome","~@phantomjs","~@iexplorer"})
	public void after(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}
	
	@Before(order=4,value={"@iexplorer"})
	public void beforeExplorer() throws Exception {
		setUpDriver(BrowserType.Iexplorer);
		oLog.info(BrowserType.Iexplorer);
	}

	@After(order=4,value={"@iexplorer"})
	public void afterExplorer(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	@Before(order=3,value={"@firefox"})
	public void beforeFirefox() throws Exception {
		setUpDriver(BrowserType.Firefox);
		oLog.info(BrowserType.Firefox);
	}

	@After(order=3,value={"@firefox"})
	public void afterFirefox(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	@Before(order=2,value={"@chrome"})
	public void beforeChrome() throws Exception {
		setUpDriver(BrowserType.Chrome);
		oLog.info(BrowserType.Chrome);
	}

	@After(order=2,value={"@chrome"})
	public void afterChrome(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	@Before(order=1,value={"@phantomjs"})
	public void beforePhantomjs() throws Exception {
		setUpDriver(BrowserType.PhantomJs);
		oLog.info(BrowserType.PhantomJs);
	}

	@After(order=1,value={"@phantomjs"})
	public void afterPhantomjs(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	public void setUpDriver(BrowserType bType) throws Exception {
		ObjectRepo.driver = standAloneStepUp(bType);
		oLog.debug("InitializeWebDrive : " + ObjectRepo.driver.hashCode());
		ObjectRepo.driver
				.manage()
				.timeouts()
				.pageLoadTimeout(ObjectRepo.reader.getPageLoadTimeOut(),
						TimeUnit.SECONDS);
		ObjectRepo.driver
				.manage()
				.timeouts()
				.implicitlyWait(ObjectRepo.reader.getImplicitWait(),
						TimeUnit.SECONDS);
		ObjectRepo.driver.manage().window().maximize();

	}

	public void tearDownDriver(Scenario scenario) throws Exception {
		
		try {
			if (ObjectRepo.driver != null) {

				GenericHelper driver = new GenericHelper(ObjectRepo.driver);

//				if (scenario.isFailed()) {
//					// Capture screenshot and embed in the report
//					final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//					scenario.attach(screenshot,new GenericHelper(ObjectRepo.driver).takeScreenShot(scenario),scenario.getName() );
////							(screenshot, "image/png", "screenshot");
//				}
//				if(scenario.)
//					scenario.write(new GenericHelper(ObjectRepo.driver).takeScreenShot(scenario));

				if(scenario.isFailed())
					scenario.write(new GenericHelper(ObjectRepo.driver).takeScreenShot(scenario));
//					scenario.embed(screenshot, destPath.toString());
//					scenario.attach(screenshot, "image/png", "screenshot");
				
				ObjectRepo.driver.quit();
				ObjectRepo.reader = null;
				ObjectRepo.driver = null;
				oLog.info("Shutting Down the driver");
			}
		} catch (Exception e) {
			oLog.error(e);
			throw e;
		}
	}

}


package com.cucumber.framework.helper.Generic;

import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.interfaces.IwebComponent;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.framework.utility.ResourceHelper;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public class GenericHelper implements IwebComponent {

    private WebDriver driver;
    private Logger oLog = LoggerHelper.getLogger(GenericHelper.class);

    public GenericHelper(WebDriver driver) {
        this.driver = driver;
        oLog.debug("GenericHelper : " + this.driver.hashCode());
    }

    public void highlightElem(By locator) {
        WebElement weElement = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('style','background: yellow;border: 2px solid red');",
                weElement);
    }
    public void highlightElement(WebElement weElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('style','background: yellow;border: 2px solid red');",
                weElement);
    }

    public void highlightElements(List<WebElement> webElements) {
        for (WebElement locator : webElements) {
            highlightElement(locator);
        }
    }

//	public void highlightElements(List<WebElement> webElements) {
//		for (WebElement locator : webElements) {
//			highlightElement(locator);
//		}
//	}

    public void highlightElement(By locator) {
        WebElement weElement = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('style','background: yellow;border: 2px solid red');",
                weElement);
    }

    public WebElement getElement(By locator) {
        oLog.info(locator);
        if (IsElementPresentQuick(locator))
            return driver.findElement(locator);

        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            oLog.error(re);
            throw re;
        }
    }

    /**
     * Check for element is present based on locator
     * If the element is present return the web element otherwise null
     * @param locator
     * @return WebElement or null
     */

    public WebElement getElementWithNull(By locator) {
        oLog.info(locator);
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            // Ignore
        }
        return null;
    }

    public boolean IsElementPresentQuick(By locator) {
        boolean flag = driver.findElements(locator).size() >= 1;
        oLog.info(flag);
        return flag;
    }
    public File screenShorPath (String screenShotName){

        File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
                + DateTimeHelper.getCurrentDate());
        if (!destDir.exists())
            destDir.mkdir();

        File destPath = new File(destDir.getAbsolutePath()
                + System.getProperty("file.separator") + screenShotName + ".jpg");
        return destPath;
    }
    public String takeScreenShot(Scenario scenario) throws IOException {

        if (driver instanceof HtmlUnitDriver) {
            oLog.fatal("HtmlUnitDriver Cannot take the ScreenShot");
            return "";
        }
        File destPath = screenShorPath(scenario.getName());
        try {
            FileUtils
                    .copyFile(((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE), destPath);
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, destPath.toString());
//			highlightElementInScreenshot(screenshot);
        } catch (IOException e) {
            oLog.error(e);
            throw e;
        }
        oLog.info(destPath.getAbsolutePath());
        return destPath.getAbsolutePath();
    }

    public byte[] highlightElementInScreenshot(byte[] screenshot, By locator) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshot));

            // Get the location and size of the element
            WebElement element = driver.findElement(locator);
            Point point = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            // Draw a rectangle around the element
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.RED);
            graphics.setStroke(new BasicStroke(2));
            graphics.drawRect(point.getX(), point.getY(), width, height);
            graphics.dispose();

            // Convert the BufferedImage back to bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return screenshot; // Return the original screenshot if highlighting fails
        }
    }
    public String takeScreenShot(String name,By locator) throws IOException {

        if (driver instanceof HtmlUnitDriver) {
            oLog.fatal("HtmlUnitDriver Cannot take the ScreenShot");
            return "";
        }

//		File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
//				+ DateTimeHelper.getCurrentDate());
//		if (!destDir.exists())
//			destDir.mkdir();
//
//		File destPath = new File(destDir.getAbsolutePath()
//				+ System.getProperty("file.separator") + name + ".jpg");
        File destPath = screenShorPath(name);
        try {
            FileUtils
                    .copyFile(((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE), destPath);
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

//			Added locator here and passed as argument it was not there before

            highlightElementInScreenshot(screenshot, locator);
//			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//			scenario.attach(screenshot, destPath, "screenshot");
        } catch (IOException e) {
            oLog.error(e);
            throw e;
        }
        oLog.info(destPath.getAbsolutePath());
        return destPath.getAbsolutePath();
    }

    public String takeScreenShot() {
        oLog.info("");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}

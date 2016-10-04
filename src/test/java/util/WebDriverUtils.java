package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.Environment;
import domain.Timeout;

/**
 * Util for defining configuration of browser drivers (inc timeouts) and for obtaining test
 * environment setting.
 * @author Ritesh Kadu 
 */
public class WebDriverUtils {

    private static final long IMPLICIT_WAIT_SECONDS = 10;

    private static Properties PROPERTIES;

    private static Environment ENVIRONMENT;
    
    private static WebDriver WEB_DRIVER;

    static {
        try {
            final InputStream inputStream = WebDriverUtils.class.getClassLoader().getResourceAsStream(
                    "selenium2-webdriver.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(inputStream);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getWebDriver()
            throws IOException
    {
        final String WEB_DRIVERType = PROPERTIES.getProperty("web.driver");
        if (WEB_DRIVERType.equals("FIREFOX")) {
            final FirefoxProfile firefoxProfile = new FirefoxProfile();
            // See http://kb.mozillazine.org/Firefox_:_FAQs_:_About:config_Entries for ways to customize
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            firefoxProfile.setPreference(
                            "browser.helperApps.neverAsk.saveToDisk",
                            "application/zip;application/rtf;application/excel;application/octet-stream;"
                                    + "application/vnd.ms-excel;application/msword;text/plain;text/text;text/txt;text/html;application/exe");
            firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);

            WEB_DRIVER = new FirefoxDriver(firefoxProfile);
        } else if (WEB_DRIVERType.equals("CHROME")) {
            final String chromeDriver = System.getenv("HOME") + "/Resume/crunch-base-webdriver/bin/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            WEB_DRIVER = new ChromeDriver();
        } else if (WEB_DRIVERType.equals("IE")) {
            final String internetExplorerDriver = "C:\\code\\BookshareWebTest\\bin\\IEDriverServer.exe";
            System.setProperty("webdriver.ie.driver", internetExplorerDriver);
            WEB_DRIVER = new InternetExplorerDriver();
        } else if (WEB_DRIVERType.equals("SAFARI")) {
            WEB_DRIVER = new SafariDriver();
        } else if (WEB_DRIVERType.equals("HTMLUNIT")) {
            WEB_DRIVER = new HtmlUnitDriver();
        } else if (WEB_DRIVERType.equals("OPERA")) {
            final String operaDriver = System.getenv("HOME") + "/Resume/crunch-base-webdriver/bin/operadriver";
            System.setProperty("webdriver.opera.driver", operaDriver);
            WEB_DRIVER = new OperaDriver();
        } else {
            throw new UnsupportedOperationException("whatchoo talkin' 'bout willis");
        }
        resetImplicitWait();
        WEB_DRIVER.manage().timeouts().pageLoadTimeout(Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds(), TimeUnit.SECONDS);
        WEB_DRIVER.manage().window().maximize();
        return WEB_DRIVER;
    }

    public static Environment getEnv() {
        if (ENVIRONMENT == null) {
            // Use the same mechanism here as we use in our application runtime, but allow it to be overridden on the
            // command line
            String value = System.getProperty("GOLDEN_KEY");
            if (value == null) {
                value = System.getenv("GOLDEN_KEY");
            }
            ENVIRONMENT = Environment.valueOf(value.toUpperCase());
        }
        return ENVIRONMENT;
    }

    public static Logger getLogger(Class className) {
        return LoggerFactory.getLogger(className);
    }

    /**
     * Check whether we want to quit our WebDriver instance at the end of each test. Defaults to true.
     * @return boolean
     */
    public static boolean quitOnTeardown() {
        boolean result = true;
        if ("true".equals(System.getProperty("DEBUG")) || ("true".equals(System.getenv("DEBUG")))) {
            result = false;
        }
        return result;
    }

    public static void timedWait(Timeout timeout) {
        try {
            Thread.sleep(timeout.getTimeoutMilliseconds());
        } catch (InterruptedException e) {
            throw new RuntimeException("Timeout hit InterruptedException");
        }
    }
    
    public static void setImplicitWait(int waitSeconds) {
        WEB_DRIVER.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
    }
    
    public static void resetImplicitWait() {
        WEB_DRIVER.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_SECONDS, TimeUnit.SECONDS);
    }
    
    public static void logElementAttributes(WebDriver driver, WebElement element, Logger logger) {
        final List parentAttributes = (List) ((JavascriptExecutor)driver).executeScript(
             "var s = []; var attrs = arguments[0].attributes; for (var l = 0; l < attrs.length; ++l) { var a = attrs[l]; s.push(a.name + ':' + a.value); } ; return s;", element);
        for (Object o : parentAttributes) {
            logger.info("Element " + element.getTagName() + ", Attribute: " + o.toString());
        }
        logger.info("Element " + element.getTagName() + ", Displayed: " + element.isDisplayed());
        logger.info("Element " + element.getTagName() + ", Enabled: " + element.isEnabled());
        logger.info("Element " + element.getTagName() + ", Selected: " + element.isSelected());
    }
    
    /**
     * Find the output directory of the tests to support the ability to store other test artifacts.
     * This is very specific to our Maven structure (target/surefire-reports).
     * @return File directory
     */
    public static File getTestResultsDirectory() {
        // Find the directory relative to a known file's location.
        final URL referenceFile = WebDriverUtils.class.getClassLoader().getResource("selenium2-webdriver.properties");
        final File parentPath = new File(referenceFile.getPath()).getParentFile().getParentFile();
        return new File(parentPath, "surefire-reports");
    }
    
    /**
     * Capture a screenshot of the current browser state.
     * Save it to the test artifacts directory for inspection.
     * @param driver WebDriver'
     * @param fileName String name to store the screenshot file
     * @return File where the screenshot was saved
     * @throws IOException
     */
    public static File takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(getTestResultsDirectory(), fileName);
        FileUtils.copyFile(screenshot, destFile);
        return destFile;
    }
}

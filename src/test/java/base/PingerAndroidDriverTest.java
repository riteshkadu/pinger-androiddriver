package base;

import java.io.File;

import io.appium.java_client.android.AndroidDriver;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A base class that handles the basic WebDriver lifecycle for tests.
 * @author Ritesh Kadu
 */
public class PingerAndroidDriverTest {
    protected static AndroidDriver driver;
    protected Logger logger;
    public static DesiredCapabilities capabilities;
    
    @Rule 
    public TestName name = new TestName();
    
    public PingerAndroidDriverTest() {
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    @BeforeClass
    public static void setUpDriver() {
        File app = new File("/Users/ritesh/Appium/PingerAPK/Textfree_6.8.apk");
        capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("version", "5.1.1");
		capabilities.setCapability("deviceName", "015d16897a101202");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
    }
    
    @AfterClass
    public static void tearDownDriver() {
    	driver.quit();
    }
    
    @Before
    public void markTestBeginning() {
        logger.info("-------- Starting test: {}#{} --------",
                this.getClass().getSimpleName(), name.getMethodName());
    }
}
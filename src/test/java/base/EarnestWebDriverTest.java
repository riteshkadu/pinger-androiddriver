package base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.WebDriverUtils;

/**
 * A base class that handles the basic WebDriver lifecycle for tests.
 * @author Ritesh Kadu
 */
public class EarnestWebDriverTest {
    protected static WebDriver driver;
    protected Logger logger;
    
    @Rule 
    public TestName name = new TestName();
    
    public EarnestWebDriverTest() {
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    @BeforeClass
    public static void setUpDriver() throws Exception {
        driver = WebDriverUtils.getWebDriver();
    }
    
    @AfterClass
    public static void tearDownDriver() {
        if (WebDriverUtils.quitOnTeardown()) {
            driver.quit();
        }
    }
    
    @Before
    public void markTestBeginning() {
        logger.info("-------- Starting test: {}#{} --------",
                this.getClass().getSimpleName(), name.getMethodName());
    }
}
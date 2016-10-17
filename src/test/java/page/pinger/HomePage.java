package page.pinger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Timeout;

/**
 * Go Pinger Home Page 
 */
public class HomePage {

	protected Logger logger;
    
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/btn_sign_up\")")
	public WebElement signUpButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/tv_login\")")
	public WebElement logInLink;

	/**
     * Default constructor.
     */
    public HomePage() {
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    public HomePage(final AndroidDriver driver) {
        this();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    public PingerLogInPage clickLogInLink(AndroidDriver driver) {
    	logger.info("Clicking on LogIn link");
    	logInLink.click();
    	Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds();
    	return new PingerLogInPage(driver);
    }

    public PingerSignUpPage clickSignUpButton(AndroidDriver driver) {
    	logger.info("Clicking on SignUp button");
    	signUpButton.click();
    	Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds();
    	return new PingerSignUpPage(driver);
    }
}
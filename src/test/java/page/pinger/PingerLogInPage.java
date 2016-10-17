package page.pinger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import domain.LiveUser;
import domain.Timeout;
import util.InputUtils;

/**
 * Pinger LogIn Page
 */
public class PingerLogInPage extends PingerTemplatePage {

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/et_content\")")
	public List<WebElement> credentials;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/cb_show_password\")")
	public WebElement showPassword;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/tv_forgot_password\")")
	public WebElement forgotPassword;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/tv_login\")")
	public WebElement logInButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.pinger.textfree:id/tv_error_message\")")
	public WebElement errorMessage;

    public PingerLogInPage(AndroidDriver driver) {
        super(driver);
    }
    
    public HomePage enterUsernamePassword(AndroidDriver driver, LiveUser user, Boolean value) {
    	logger.info("Enter valid credential for a user");
    	
    	enterCredentials(driver, user, value);
    	
    	//Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id, 'org.pinger.android:id/done_button')]")));
    	return new HomePage(driver); //Return to a new page after success
    }
    
    public PingerLogInPage errorUsernamePassword(AndroidDriver driver, LiveUser user, Boolean value) {
    	logger.info("Enter incorrect credential for a user");
    	
    	enterCredentials(driver, user, value);

    	//Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id, 'org.pinger.android:id/done_button')]")));
    	return new PingerLogInPage(driver); //Stays on the same page after error
    }
    
    //These are the common steps for both sucess and error log in tests
    private void enterCredentials(AndroidDriver driver, LiveUser user, Boolean value) {
    	logger.info("Enter credential for a user to log in");
    	InputUtils.clearAndType(credentials.get(0), user.getUsername());
    	InputUtils.clearAndType(credentials.get(1), user.getPassword());
    	if(value == true) {
    		showPassword.click();
    	}
    	logInButton.click();
    }
}
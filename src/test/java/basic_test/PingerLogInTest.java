package basic_test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.junit.Before;
import org.junit.Test;

import page.pinger.HomePage;
import page.pinger.PingerLogInPage;
import domain.LiveUser;
import domain.Timeout;
import base.PingerAndroidDriverTest;

public class PingerLogInTest extends PingerAndroidDriverTest {
	public HomePage home;
	
    @Before
    public void setUp() throws Exception {
        // Set up local application and capabilities configuration. 
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds();
		home = new HomePage(driver);
    }
    
    @Test
    public void logInError(){
    	logger.info("Test Case: An user log in with incorrect credentials with password On");
    	PingerLogInPage logIn = home.clickLogInLink(driver);
    	
    	logIn = logIn.errorUsernamePassword(driver, LiveUser.QA_ADMIN, false);
        assertThat(logIn.errorMessage.getText(), containsString("No match found. Try again."));
        
    	logger.info("Test Case: An user log in with incorrect credentials with password Off");
    	logIn = logIn.errorUsernamePassword(driver, LiveUser.QA_TEST, true);
        assertThat(logIn.errorMessage.getText(), containsString("Please provide some info."));
        
        //NOTE: Here we are only verifying error messages on log in page
    }
    
    @Test
    public void logInSuccess(){
    	//TODO: Add test cases for the valid log in test cases
    }
}
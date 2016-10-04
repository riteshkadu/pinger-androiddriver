package code_test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import page.earnest.EarnestCheckoutPage;
import page.earnest.EarnestHomePage;
import domain.Environment;
import base.EarnestWebDriverTest;

/**
 * @author Ritesh Kadu
 */
public class JungleSocksValidationTest extends EarnestWebDriverTest {
	private EarnestHomePage home;
	
    @Before
    public void setUpUser() throws Exception {
		logger.info("Navigate to earnest test home page");
		driver.get(Environment.getEarnestURL());
		home = new EarnestHomePage(driver); 
    }
    
	@Test
    public void testEmptyValidations() throws Exception {
		logger.info("TestCase: Do not enter any information and click on checkout.");
        assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 0, 0, 0, 0, "");
        assertThat(checkout.dialogHeading.getText(), containsString("We're sorry, but something went wrong."));
	}
	
	@Test
    public void testNoStateValidations() throws Exception {
		logger.info("TestCase: Enter information but do not select any state and click on checkout.");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 4, 5, 6, 1, "");
        assertThat(checkout.dialogHeading.getText(), containsString("We're sorry, but something went wrong."));
	}
	
	@Test
    public void testCaliforniaStateValidations() throws Exception {
		logger.info("TestCase: Enter information, select california state and click on checkout.");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 4, 0, 0, 0, "California");
        assertThat(checkout.pageHeading.getText(), containsString("Please Confirm Your Order"));
        assertThat(checkout.subtotal.getText(), containsString(String.valueOf(checkout.orderResult.getSubTotal())));
        assertThat(checkout.taxes.getText(), containsString(String.valueOf(checkout.orderResult.getTaxes())));
        assertThat(checkout.total.getText(), containsString(String.valueOf(checkout.orderResult.getTotal())));
	}

	@Test
    public void testNewYorkStateValidations() throws Exception {
		logger.info("TestCase: Enter information, select new york state and click on checkout.");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 4, 0, 0, 10, "New York");
        assertThat(checkout.pageHeading.getText(), containsString("Please Confirm Your Order"));
        assertThat(checkout.subtotal.getText(), containsString(String.valueOf(checkout.orderResult.getSubTotal())));
        assertThat(checkout.taxes.getText(), containsString(String.valueOf(checkout.orderResult.getTaxes())));
        assertThat(checkout.total.getText(), containsString(String.valueOf(checkout.orderResult.getTotal())));
	}

	@Test
    public void testMinnesotaStateValidations() throws Exception {
		logger.info("TestCase: Enter information, select Minnesota state and click on checkout.");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 4, 0, 0, 10, "Minnesota");
        assertThat(checkout.pageHeading.getText(), containsString("Please Confirm Your Order"));
        assertThat(checkout.subtotal.getText(), containsString(String.valueOf(checkout.orderResult.getSubTotal())));
        assertThat(checkout.taxes.getText(), containsString(String.valueOf(checkout.orderResult.getTaxes())));
        assertThat(checkout.total.getText(), containsString(String.valueOf(checkout.orderResult.getTotal())));
        assertTrue(checkout.subtotal.getText().contains(checkout.total.getText()));
	}

	@Test
    public void testAlaskaOtherStateValidations() throws Exception {
		logger.info("TestCase: Enter information, select Alaska state (other) and click on checkout.");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 4, 10, 20, 10, "Alaska");
        assertThat(checkout.pageHeading.getText(), containsString("Please Confirm Your Order"));
        assertThat(checkout.subtotal.getText().replaceAll(",([^,]*)$", "$1").substring(1), containsString(String.valueOf(checkout.orderResult.getSubTotal())));
        assertThat(checkout.taxes.getText().replaceAll(",([^,]*)$", "$1").substring(1), containsString(String.valueOf(checkout.orderResult.getTaxes())));
        assertThat(checkout.total.getText().replaceAll(",([^,]*)$", "$1").substring(1), containsString(String.valueOf(checkout.orderResult.getTotal())));
	}

	@Ignore
	@Test
    public void testQuantityLimitValidations() throws Exception {
		logger.info("TestCase: Verify validations on item quantity limit");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 400, 1000, 20, 10, "Alaska");
        //TODO: Feature needed to be developed to test the limit on instock items
	}

	@Ignore
	@Test
    public void testCharacterLimitValidations() throws Exception {
		logger.info("TestCase: Verify validations on input characters");
		assertThat(home.pageHeading.getText(), containsString("Welcome To Jungle Socks"));
		EarnestCheckoutPage checkout = home.enterOrderInfo(driver, 0, 1000, 20, 10, "!@#$%^&*");
        //TODO: Feature needed to be developed to test other character input validations
	}

}
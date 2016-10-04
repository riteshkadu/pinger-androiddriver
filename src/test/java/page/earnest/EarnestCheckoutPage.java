package page.earnest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Order;

/**
 * HomePage at Earnest Test Checkout Page
 */
public class EarnestCheckoutPage extends EarnestTemplatePage {

	public Order orderResult;
	
    @FindBy(xpath = "//div[@class='dialog']//h1") 
    public WebElement dialogHeading;

    @FindBy(id = "subtotal")                                                        
    public WebElement subtotal;

    @FindBy(id = "taxes")                                                        
    public WebElement taxes;

    @FindBy(id = "total")                                                        
    public WebElement total;
    
    final Logger logger = LoggerFactory.getLogger(EarnestCheckoutPage.class);
    
    public EarnestCheckoutPage(WebDriver driver) {
        super(driver);
    }

	public EarnestCheckoutPage(WebDriver driver, Order order) {
		super(driver);
		orderResult = order;
	}
}
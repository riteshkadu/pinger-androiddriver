package page.earnest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Timeout;

/**
 * HomePage at Earnest 
 */
public class HomePage {

	protected Logger logger;
    
    @FindBy(xpath = "//h1") 
    public WebElement pageHeading;
    
    //TODO: Add More home page web elements which are commonly used across all the pages at Earnest code page 
    
    /**
     * Default constructor.
     */
    public HomePage() {
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    public HomePage(final WebDriver driver) {
        this();
        
        //An example of explicite wait
        WebDriverWait wait = new WebDriverWait(driver, Timeout.PAGE_LOAD_WAIT.getTimeoutSeconds());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));
        PageFactory.initElements(driver, this);
    }
}
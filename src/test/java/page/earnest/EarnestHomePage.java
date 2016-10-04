package page.earnest;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Order;
import util.InputUtils;

/**
 * HomePage at Earnest Test
 */
public class EarnestHomePage extends EarnestTemplatePage {

    @FindBy(id = "line_item_quantity_zebra")                                                        
    public WebElement quantityZebra;

    @FindBy(id = "line_item_quantity_lion")                                                        
    public WebElement quantityLion;
	
    @FindBy(id = "line_item_quantity_elephant")                                                        
    public WebElement quantityElephant;

    @FindBy(id = "line_item_quantity_giraffe")                                                        
    public WebElement quantityGiraffe;

    @FindBy(name = "state")                                                        
    public WebElement stateOptions;

    @FindBy(name = "commit")                                                        
    public WebElement checkoutButton;

    final Logger logger = LoggerFactory.getLogger(EarnestHomePage.class);
    
    public EarnestHomePage(WebDriver driver) {
        super(driver);
    }
    
    public Select getStateListDropdown() {
        return new Select(stateOptions);
    }
    
    public void clickCheckoutButton(WebDriver driver) {
    	logger.info("Clicking on checkout button.");
    	checkoutButton.click();
    }
    
    public EarnestCheckoutPage enterOrderInfo(WebDriver driver, Integer zebra, Integer lion, Integer elephant, Integer giraffe, String state) {
    	logger.info("Enter order information");
    	InputUtils.clearAndType(quantityZebra, String.valueOf(zebra));
    	InputUtils.clearAndType(quantityLion, String.valueOf(lion));
    	InputUtils.clearAndType(quantityElephant, String.valueOf(elephant));
    	InputUtils.clearAndType(quantityGiraffe, String.valueOf(giraffe));
    	InputUtils.selectOption(getStateListDropdown(), state);
    	clickCheckoutButton(driver);
    	
    	Order order = performOperations(zebra, lion, elephant, giraffe, state);
    	return new EarnestCheckoutPage(driver, order);
    }
    
    public Order performOperations(Integer zebra, Integer lion, Integer elephant, Integer giraffe, String state) {
    	logger.info("Add all the items as per their quantity");
    	int orderTotal = zebra * priceHashMap("zebra") + lion * priceHashMap("lion") + 
    			elephant * priceHashMap("elephant") + giraffe * priceHashMap("giraffe");
    	
    	logger.info("Calulate the State Tax");
    	Float stateTax = orderTotal * stateHashMap(state);
    	DecimalFormat format = new DecimalFormat("##.##");
    	stateTax = Float.parseFloat(format.format(stateTax));
    	
    	logger.info("Calulate the Total");
    	Float total = orderTotal + stateTax;
    	
    	Order order = new Order(orderTotal, stateTax, total);
    	return order;
    }
    
    //Test data for state and their taxes
    public Float stateHashMap(String mapState){
        HashMap<String, Float> map = new HashMap<String, Float>();
        map.put("California", 0.08f);
        map.put("New York", 0.06f);
        map.put("Minnesota", 0.00f);
        map.put("Alabama", 0.05f);
        map.put("Alaska", 0.05f);
        map.put("", 0.00f);
        //TODO: Add all the US states

        Float mapTaxResult = map.get(mapState);
        return mapTaxResult;
    }

    //Test data for Item and its price
    public Integer priceHashMap(String mapName){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("zebra", 13);
        map.put("lion", 20);
        map.put("elephant", 35);
        map.put("giraffe", 17);

        Integer mapPriceResult = map.get(mapName);
        return mapPriceResult;
    }
}
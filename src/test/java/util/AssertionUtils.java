package util;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Ritesh Kadu
 */
public class AssertionUtils {

    /**
     * General check if any defined Web Element is present on the page.
     * @param webElement element
     * @return true if present
     */
    public static boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * General check if any id-defined Web Element is present on the page.
     * @param locator string
     * @return true if present
     */
    public static boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return driver.findElement(By.id(locator)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Indicates whether or not a Web Element is present by looking for the element's text.  The element must
     * always have text when present, or this won't work.
     *
     * @param webElement
     */
    public static boolean isElementPresentByText(WebElement webElement) {
        WebDriverUtils.setImplicitWait(0);
        try {
            webElement.getText();
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } finally {
            WebDriverUtils.resetImplicitWait();
        }
    }

    /**
     * Indicates whether or not a Web Element is present by looking for the element's ID.  The element must
     * always have an ID when present, or this won't work.
     *
     * @param webElement
     */
    public static boolean isElementPresentById(WebElement webElement) {
        WebDriverUtils.setImplicitWait(0);
        try {
            webElement.getAttribute("id");
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } finally {
            WebDriverUtils.resetImplicitWait();
        }
    }

    public static boolean isElementPresentByName(WebElement webElement) {
        WebDriverUtils.setImplicitWait(0);
        try {
            webElement.getAttribute("name");
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } finally {
            WebDriverUtils.resetImplicitWait();
        }
    }

    /**
     * Indicates whether or not a Web Element is present by looking for the element's alt text (e.g., DAISY
     * download icon).  The element must always have alt text when present, or this won't work.
     *
     * @param webElement
     */
    public static boolean isElementPresentByAltText(WebElement webElement) {
        WebDriverUtils.setImplicitWait(0);
        try {
            webElement.getAttribute("alt");
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } finally {
            WebDriverUtils.resetImplicitWait();
        }
    }

    /**
     * Indicates whether or not a link is present based on the link's text.
     *
     * @param driver
     * @param linkText
     */
    public static boolean isLinkPresentByText(WebDriver driver, String linkText) {
        return doesElementExist(driver, By.linkText(linkText));
    }

    /**
     * Does a specific element contain a link with matching text? Faster
     * than checking the entire web document.
     * @param element page element
     * @param linkText text to match
     * @return true if present and displayed
     */
    public static boolean isLinkPresent(WebElement element, String linkText) {
    	try {
    		return element.findElement(By.linkText(linkText)).isDisplayed();
    	} catch (org.openqa.selenium.NoSuchElementException ignored) {
    		return false;
    	}
    }

    /**
     * Verifies the exact options available on a select menu, based on the option text.
     *
     * @param select
     * @param expectedOptions
     */
    public static boolean assertSelectOptions(Select select, List<String> expectedOptions) {
        List<String> actualOptions = new ArrayList<String>();
        for (WebElement option : select.getOptions()) actualOptions.add(option.getText());
        try {
            assertArrayEquals(expectedOptions.toArray(), actualOptions.toArray());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether or not an element exists on the page using findElement rather than Web Element
     *
     * @param driver
     * @param locator
     */
    public static boolean doesElementExist(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

}
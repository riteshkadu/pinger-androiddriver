package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Ritesh Kadu
 */
public class InputUtils {

    /**
     * A shortcut both to clear the existing contents of a field and also to enter new text in that field.
     * @param field WebElement
     * @param text String
     */
    public static void clearAndType(WebElement field, String text) {
      field.clear();
      field.sendKeys(text);
    }
    
    /**
     * Enter field value only if it is not null.
     * @param field WebElement
     * @param text String
     */
    public static void clearAndTypeIfNew(WebElement field, String text) {
        if (text != null) {
            clearAndType(field, text);
        }
    }

    /**
     * Update a checkbox to be consistent with the given boolean value.
     * True means it should be checked, false means it should be unchecked.
     * @param checkbox WebElement
     * @param value boolean
     */
    public static void setCheckbox(WebElement checkbox, boolean value) {
        if (value) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } else {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
    
    /**
     * Add another selection to a select control that matches the given visible text.
     * If value is null, choose the default option, which is assumed to have the value
     * of an empty string.
     * @param selectControl Select
     * @param value String, may be null
     */
    public static void selectOption(Select selectControl, String value) {
        if (value == null || value == "") {
            selectControl.selectByValue("");
        } else {
            selectControl.selectByVisibleText(value);
        }
        // Move off of the selection to trigger any JS that might be attached to it.
        if (selectControl.getFirstSelectedOption().isDisplayed()) {
            selectControl.getFirstSelectedOption().sendKeys(Keys.TAB);
        }
    }

    /**
     * Choose a set of choices of a select control that match the given visible text.
     * If value is null, choose the default option, which is assumed to have the value
     * of an empty string.
     * This will remove any existing selections first.
     * @param selectControl Select
     * @param values set of Strings, may be null
     */
    public static void selectOptions(Select selectControl, String... values) {
        selectControl.deselectAll();
        for (final String value : values) {
            if (value == null || value == "") {
                selectControl.selectByValue("");
            } else {
                selectControl.selectByVisibleText(value);
            } 
        }
        // Move off of the selection to trigger any JS that might be attached to it.
        if (selectControl.getFirstSelectedOption().isDisplayed()) {
            selectControl.getFirstSelectedOption().sendKeys(Keys.TAB);
        }
    }

    /**
     * Choose the option of a select control that matches the given value text.
     * If value is null, choose the default option, which is assumed to have the value
     * of an empty string.
     * @param selectControl Select
     * @param value String, may be null
     */
    public static void selectOptionValue(Select selectControl, String value) {
        if (value == null) {
            selectControl.selectByValue("");
        } else {
            selectControl.selectByValue(value);
        }
        // Move off of the selection to trigger any JS that might be attached to it.
        if (selectControl.getFirstSelectedOption().isDisplayed()) {
            selectControl.getFirstSelectedOption().sendKeys(Keys.TAB);
        }
    }

    /**
     * Get the validation error message on a form field, if any, to compare
     * to the expected result.
     * @param field form input
     * @param driver webdriver
     * @return error message if present, or null
     */
    public static String getErrorMessage(final WebElement field, final WebDriver driver) {
    	final String errorId = field.getAttribute("aria-describedby");
    	if (errorId != null) {
    		final WebElement error = driver.findElement(By.id(errorId));
    		if (error != null && error.getAttribute("class").contains("error")) {
    			return error.getText();
    		}
    	}
    	return null;
    }
}
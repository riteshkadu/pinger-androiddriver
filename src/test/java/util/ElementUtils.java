package util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Class of utility methods on WebElements.
 * @author Ritesh Kadu
 */
public class ElementUtils {
    /**
     * Shorthand for getting a dropdown selection.
     * @param element Select
     * @return String that is selected
     */
    public static String dropDownText(final Select element) 
    {
        return element.getFirstSelectedOption().getText();        
    }
    
    /**
     * Change the selection of an element to match the given value, if needed.
     * If this is a checkbox, for example, it will toggle it if necessary.
     * @param element WebElement
     * @param value true for checked, false for unchecked
     */
    public static void setSelected(final WebElement element, final boolean value) {
        if (element.isSelected() != value) {
            element.click();
        }
    }
}

package majchman.testauto.ui.web.pageobjects.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class DropdownPage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/dropdown";
    private static final String PAGE_HEADING = "Dropdown List";

    private static final By.ByCssSelector HEADING = new By.ByCssSelector( "h3" );
    private static final By.ById DROPDOWN_LIST = new By.ById( "dropdown" );
    private static final By.ByCssSelector SELECTED_OPTION = new By.ByCssSelector( "#dropdown > option[selected]" );
    private static final By.ByCssSelector OPTION_1 = new By.ByCssSelector( "option[value='1']" );
    private static final By.ByCssSelector OPTION_2 = new By.ByCssSelector( "option[value='2']" );

    private final WebDriver driver;

    public DropdownPage( WebDriver driver ) {
        this.driver = driver;
    }

    public void open() {
        driver.get( PAGE_URL );
    }

    public boolean isOpen() {
        try {
            return driver.getCurrentUrl().equals( PAGE_URL )
                    && driver.findElement( HEADING ).getText().equals( PAGE_HEADING );
        } catch ( NoSuchElementException e ) {
            return false;
        }
    }

    public void selectOption1() {
        clickOnDropdown();
        driver.findElement( OPTION_1 ).click();
    }

    public void selectOption2() {
        clickOnDropdown();
        driver.findElement( OPTION_2 ).click();
    }

    public void clickOnDropdown() {
        driver.findElement( DROPDOWN_LIST ).click();
    }

    public String getSelectedOption() {
        return driver.findElement( SELECTED_OPTION ).getText();
    }
}

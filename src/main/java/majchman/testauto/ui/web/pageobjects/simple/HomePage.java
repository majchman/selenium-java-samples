package majchman.testauto.ui.web.pageobjects.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/";
    private static final String PAGE_HEADING = "Welcome to the-internet";

    private static final By.ByCssSelector HEADING = new By.ByCssSelector( "h1" );
    private static final By.ByLinkText DROPDOWN_PAGE_LINK = new By.ByLinkText( "Dropdown" );
    private static final By.ByLinkText KEY_PRESSES_PAGE_LINK = new By.ByLinkText( "Key Presses" );

    private final WebDriver driver;

    public HomePage( WebDriver driver ) {
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

    public void openDropdownPage() {
        driver.findElement( DROPDOWN_PAGE_LINK )
                .click();
    }

    public void openKeyPressesPage() {
        driver.findElement( KEY_PRESSES_PAGE_LINK )
                .click();
    }
}

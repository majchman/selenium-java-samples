package majchman.testauto.ui.web.simple.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeyPressesPage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/key_presses";
    private static final String PAGE_HEADING = "Key Presses";

    private static final By.ByCssSelector HEADING = new By.ByCssSelector( "h3" );
    private static final By.ById INPUT_FIELD = new By.ById( "target" );
    private static final By.ById RESULT_TEXT = new By.ById( "result" );

    private final WebDriver driver;

    public KeyPressesPage( WebDriver driver ) {
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

    public void enterKeyPress( CharSequence keyValue ) {
        WebElement inputField = driver.findElement( INPUT_FIELD );
        inputField.click();
        inputField.sendKeys( keyValue );
    }

    public String getKeyPressResult() {
        return driver.findElement( RESULT_TEXT ).getText();
    }
}

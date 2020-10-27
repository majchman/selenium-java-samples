package majchman.testauto.ui.web.pageobjects.simple;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DropdownPageTest {

    private static WebDriver driver;

    private DropdownPage dropdownPage;

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty( "webdriver.gecko.driver", "bin/firefoxdriver_win64.exe" );
        driver = new FirefoxDriver();
    }

    @Before
    public void createDropdownPage() {
        this.dropdownPage = new DropdownPage( driver );
    }

    @Test
    public void dropdownPageOpensCorrectly() {
        dropdownPage.open();

        assertThat( "Dropdown Page should open correctly",
                dropdownPage.isOpen(),
                is( true ) );
    }

    @Test
    public void selectOption1_option1IsSelected() {
        final String selectedOptionText = "Option 1";

        dropdownPage.open();
        dropdownPage.selectOption1();

        assertThat( "Option 1 should be selected in the dropdown list",
                dropdownPage.getSelectedOption(),
                is( equalTo( selectedOptionText ) ));
    }

    @Test
    public void selectOption2_option2IsSelected() {
        final String selectedOptionText = "Option 2";

        dropdownPage.open();
        dropdownPage.selectOption2();

        assertThat( "Option 2 should be selected in the dropdown list",
                dropdownPage.getSelectedOption(),
                is( equalTo( selectedOptionText ) ));
    }

    @AfterClass
    public static void tearDown() {
        if ( driver != null ) {
            driver.quit();
        }
    }
}

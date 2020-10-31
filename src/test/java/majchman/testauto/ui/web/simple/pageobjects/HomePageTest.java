package majchman.testauto.ui.web.simple.pageobjects;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageTest {

    private static WebDriver driver;

    private HomePage homePage;

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty( "webdriver.chrome.driver", "bin/chromedriver_win64.exe" );
        driver = new ChromeDriver();
    }

    @Before
    public void createHomePage() {
        this.homePage = new HomePage( driver );
    }

    @Test
    public void homePageOpensCorrectly() {
        homePage.open();

        assertThat( "Home Page opened correctly",
                homePage.isOpen(),
                is( true ) );
    }

    @Test
    public void dropdownPageOpensCorrectly() {
        homePage.open();
        homePage.openDropdownPage();

        DropdownPage dropdownPage = new DropdownPage( driver );

        assertThat( "Dropdown Page opened correctly",
                dropdownPage.isOpen(),
                is( true ) );
    }

    @Test
    public void keyPressesPageOpensCorrectly() {
        homePage.open();
        homePage.openKeyPressesPage();

        KeyPressesPage keyPressesPage = new KeyPressesPage( driver );

        assertThat( "Key Presses Page opened correctly",
                keyPressesPage.isOpen(),
                is( true ) );
    }

    @AfterClass
    public static void tearDown() {
        if ( driver != null ) {
            driver.quit();
        }
    }
}

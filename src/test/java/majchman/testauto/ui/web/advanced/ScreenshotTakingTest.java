package majchman.testauto.ui.web.advanced;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScreenshotTakingTest {

    private static WebDriver driver;

    @Rule
    public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule( driver );

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty( "webdriver.chrome.driver", "bin/chromedriver_win64.exe" );
        driver = new ChromeDriver();
    }

    @Test
    public void failingTest_takesScreenshotOnFailure() {
        final String url = "https://the-internet.herokuapp.com/";
        driver.get( url );

        try {
            TimeUnit.SECONDS.sleep( 10 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement( By.id( "none" ) );

        assertThat( "a page element should have been found",
                element,
                is( notNullValue() ) );
    }

    @AfterClass
    public static void tearDown() {
        if ( driver != null ) {
            driver.quit();
        }
    }
}

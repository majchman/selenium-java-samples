package majchman.testauto.ui.web.advanced;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChromeDriverServiceTest {

    private static ChromeDriverService service;
    private static WebDriver driver;

    @BeforeClass
    public static void setupChromeDriverService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable( new File( "bin/chromedriver_win64.exe" ) )
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver( service.getUrl(), new ChromeOptions() );
    }

    @Test
    public void chrome_chromedriverCanAccessTheInternet() {
        final String url = "https://the-internet.herokuapp.com/";
        final String pageTitle = "Welcome to the-internet";
        final By selector = By.cssSelector( "h1" );

        assertURLCanBeAccessed( url, pageTitle, selector );
    }

    @Test
    public void chrome_chromedriverCanAccessCheckboxes() {
        final String url = "https://the-internet.herokuapp.com/checkboxes";
        final String pageTitle = "Checkboxes";
        final By selector = By.cssSelector( "h3" );

        assertURLCanBeAccessed( url, pageTitle, selector );
    }

    @Test
    public void chrome_chromedriverCanAccessFileDownloads() {
        final String url = "https://the-internet.herokuapp.com/download";
        final String pageTitle = "File Downloader";
        final By selector = By.cssSelector( "h3" );

        assertURLCanBeAccessed( url, pageTitle, selector );
    }

    private void assertURLCanBeAccessed( final String url, final String pageTitle, By selector ) {
        driver.get( url );

        WebElement element = driver.findElement( selector );
        assertThat( String.format( "The page under %s should have the title >%s<", url, pageTitle ),
                element.getText(),
                is( equalTo( pageTitle ) ) );
    }

    @After
    public void quitDriver() {
        if ( driver != null ) {
            driver.quit();
        }
    }

    @AfterClass
    public static void stopChromeDriverService() {
        service.stop();
    }
}

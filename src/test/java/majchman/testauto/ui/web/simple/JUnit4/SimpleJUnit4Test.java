package majchman.testauto.ui.web.simple.JUnit4;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SimpleJUnit4Test {

    private static WebDriver driver;

    @Test
    public void chrome_chromedriverCanAccessChrome() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver_win64.exe");
        driver = new ChromeDriver();

        assertTheInternetCanBeAccessed();
    }

    @Test
    public void chromeHeadless_chromedriverCanAccessChrome() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver_win64.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);

        assertTheInternetCanBeAccessed();
    }

    @Test
    public void firefox_firefoxdriverCanAccessFirefox() {
        System.setProperty("webdriver.gecko.driver", "bin/firefoxdriver_win64.exe");
        driver = new FirefoxDriver();

        assertTheInternetCanBeAccessed();
    }

    @Test
    public void firefoxHeadless_firefoxdriverCanAccessFirefox() {
        FirefoxBinary binary = new FirefoxBinary();
        binary.addCommandLineOptions("--headless");

        System.setProperty("webdriver.gecko.driver", "bin/firefoxdriver_win64.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(binary);
        driver = new FirefoxDriver(options);

        assertTheInternetCanBeAccessed();
    }

    private void assertTheInternetCanBeAccessed() {
        final String URL = "https://the-internet.herokuapp.com/";
        final String pageTitle = "Welcome to the-internet";

        driver.get(URL);

        WebElement element = driver.findElement(By.cssSelector("h1"));
        assertThat("The internet homepage should have a title", element.getText(), is(equalTo(pageTitle)));
    }

    // runs after each method
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

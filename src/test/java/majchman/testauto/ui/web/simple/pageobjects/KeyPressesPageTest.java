package majchman.testauto.ui.web.simple.pageobjects;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class KeyPressesPageTest {

    private static WebDriver driver;

    private KeyPressesPage keyPressesPage;

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty( "webdriver.gecko.driver", "bin/firefoxdriver_win64.exe" );
        driver = new FirefoxDriver();
    }

    @Before
    public void createKeyPressesPage() {
        this.keyPressesPage = new KeyPressesPage( driver );
    }

    @Test
    public void keyPressesPageOpensCorrectly() {
        keyPressesPage.open();

        assertThat( "Key Presses Page should open correctly",
                keyPressesPage.isOpen(),
                is( true ) );
    }

    @Test
    public void keyPressedIsShown() {
        final String keyPressed = "t";

        keyPressesPage.open();
        keyPressesPage.enterKeyPress( "t" );

        assertThat( String.format( "Pressed key '%s' should be shown", keyPressed ),
                keyPressesPage.getKeyPressResult(),
                is( equalTo( "You entered: " + keyPressed.toUpperCase() ) ) );
    }

    @Test
    public void whenPressingKeySequence_onlyLastKeyIsShown() {
        String[] keySequence = {"t", "e", "s"};

        keyPressesPage.open();
        String lastKeyPressed = null;
        for ( String key : keySequence ) {
            keyPressesPage.enterKeyPress( key );
            lastKeyPressed = key;
        }

        assertThat( String.format( "Pressed key '%s' should be shown", lastKeyPressed ),
                keyPressesPage.getKeyPressResult(),
                is( equalTo( "You entered: " + lastKeyPressed.toUpperCase() ) ) );
    }

    @AfterClass
    public static void tearDown() {
        if ( driver != null ) {
            driver.quit();
        }
    }
}

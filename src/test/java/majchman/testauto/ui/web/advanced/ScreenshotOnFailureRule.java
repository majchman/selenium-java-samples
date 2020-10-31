package majchman.testauto.ui.web.advanced;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotOnFailureRule extends TestWatcher {

    private WebDriver driver;
    private String screenshotDir = "screenshots/";

    public ScreenshotOnFailureRule( WebDriver driver ) {
        this.driver = driver;
    }

    @Override
    protected void failed( Throwable e, Description description ) {
        File targetFile = createTargetFile( description );

        TakesScreenshot screenshot = ( ( TakesScreenshot ) driver );
        File screenshotFile = screenshot.getScreenshotAs( OutputType.FILE );

        try {
            FileUtils.copyFile( screenshotFile, targetFile );
        } catch ( IOException ioException ) {
            throw new RuntimeException( ioException );
        }
    }

// Quiting the WebDriver in an @After method closes it before
// the rule can take a screenshot. When using @After quit
// the WebDriver here instead
// Quiting the WebDriver in an @AfterClass method is fine though

//    @Override
//    protected void finished( Description description ) {
//        driver.quit(); //or driver.close()
//    }

    private File createTargetFile( Description description ) {
        String methodName = description.getMethodName();
        String testClassName = description.getTestClass().getSimpleName();
        String fileName = String.format( "%s-%s-%d.png", testClassName, methodName, System.currentTimeMillis() );

        return new File( screenshotDir + fileName );
    }
}

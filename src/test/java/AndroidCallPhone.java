import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class AndroidCallPhone {

    protected AppiumDriver driver = null;

    UiAutomator2Options options = new UiAutomator2Options();

    String apiToken = "";

    @BeforeMethod
    public void setUp() throws MalformedURLException, IOException {
        System.out.println("Setup");

        options.setAutomationName("uiautomator2");
        options.setAppPackage("");
        options.setAppActivity("");
        options.setDeviceName("SM-S901U");
        options.setPlatformName("android");
        options.setUdid("R5CRC2NDFPR");
        URL appiumServer = new URL("https://dev-us-sny-1.headspin.io:7037/v0/" + apiToken + "/wd/hub");
        System.out.println(options);
        System.out.println(appiumServer);

        driver = new AppiumDriver(appiumServer, options);

    }
    @Test
    public void test() throws IOException {
        System.out.println( "Test" );

        for(int i = 0; i<60; i++){
            try{
               driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Answer, Drag right with two fingers to answer call.\"]"));
                System.out.println("Found it!");
                break;
            } catch (Exception e) {
                System.out.println("Did not find yet  " + i);
            }
            sleep(1);
        }
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshot.png"));

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("End of test");
    }

    public void sleep(int time){
        time = time*1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


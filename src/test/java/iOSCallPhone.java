import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class iOSCallPhone {

    protected AppiumDriver driver = null;

    XCUITestOptions options = new XCUITestOptions();
    String apiToken = "";

    @BeforeMethod
    public void setUp() throws MalformedURLException, IOException {
        System.out.println("Setup");

        options.setCapability("headspin:appiumVersion", "2.1.3");
        options.setAutomationName("xcuitest");
        options.setBundleId("com.apple.Preferences");
//        options.setBundleId("");
        options.setPlatformVersion("15.0");
        options.setDeviceName("iPhone 12");
        options.setPlatformName("ios");
        options.setUdid("00008101-001A61801106001E");

        URL appiumServer = new URL("https://dev-us-sny-3.headspin.io:7010/v0/" + apiToken + "/wd/hub");

        driver = new AppiumDriver(appiumServer, options);

    }
    @Test
    public void test() throws IOException {
        System.out.println( "Test" );

        for(int i = 0; i<60; i++){
            try{
               driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Accept\"]"));
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


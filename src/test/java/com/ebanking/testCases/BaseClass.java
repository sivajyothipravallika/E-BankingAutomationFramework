package com.ebanking.testCases;

import com.ebanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    ReadConfig readConfig = new ReadConfig();

    public String baseURL = readConfig.getApplicationURL();
    public String username = readConfig.getUserName();
    public String password = readConfig.getPassword();

    public String home = readConfig.getHomePath();
    public static WebDriver driver;


    public static Logger logger;

    @SuppressWarnings("deprecation")
	@Parameters("browser")
    @BeforeClass
    public void setup(String br){


        logger = Logger.getLogger("e-banking");
        PropertyConfigurator.configure("Log4j.properties");

        switch (br) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriver());
                driver = new ChromeDriver();
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxDriver());
                driver = new FirefoxDriver();
            }
            case "msedge": {
                System.setProperty("webdriver.edge.driver", readConfig.getMsedgeDriver());

                driver = new EdgeDriver();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String testname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(home + "/Screenshots/" + testname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot Taken Successfully");
    }
    public String randomString(){
        return RandomStringUtils.randomAlphabetic(8);
    }

    public String randomNum(){
        return RandomStringUtils.randomNumeric(5);
    }


}

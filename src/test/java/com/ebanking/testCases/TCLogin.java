package com.ebanking.testCases;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebanking.pageObjects.LoginPage;

public class TCLogin extends BaseClass {
    @Test
    public void loginTest() throws IOException {

        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("Entered UserName");
        lp.setPassword(password);
        logger.info("Entered Password");

        lp.clickSubmit();
        logger.info("Clicked on login button");

        if (driver.getTitle().equals("GTPL Bank Manager HomePage")){
            Assert.assertTrue(true);
            logger.info("Login Test Passed");
        }else {

            captureScreen(driver, "loginTest");
            fail();
            logger.info("Login Test Failed");
        }

     }
}

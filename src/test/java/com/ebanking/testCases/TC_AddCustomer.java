package com.ebanking.testCases;

import com.ebanking.pageObjects.AddCustomer;
import com.ebanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AddCustomer extends BaseClass{

    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("username is provided");
        lp.setPassword(password);
        lp.clickSubmit();

        Thread.sleep(3000);

        AddCustomer addCustomer = new AddCustomer(driver);
        addCustomer.clickAddNewCustomer();

        logger.info("providing customer details...");

        addCustomer.custName("Sivajyothi");
        addCustomer.custGender("Female");
        addCustomer.custdob("18","08","1995");
        Thread.sleep(3000);
        addCustomer.custAddress("INDIA");
        addCustomer.custcity("Visakhapatnam");
        addCustomer.custState("Andhra Pradesh");
        addCustomer.custPinno(530051);
        addCustomer.custTelephone("7843224321");

        String email = randomString() + "@gmail.com";
        addCustomer.custEmail(email);
       // addCustomer.custPassword("abcdef");
        addCustomer.custSubmit();
        Thread.sleep(3000);

        logger.info("Validation Started....");

        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

        if (res == true){
            Assert.assertTrue(true);
            logger.info("test case passed...");
        }else {
            captureScreen(driver, "addNewCustomer");
            Assert.assertTrue(false);

        }
    }

}

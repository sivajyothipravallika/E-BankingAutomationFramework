package com.ebanking.testCases;

import com.ebanking.pageObjects.LoginPage;
import com.ebanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT extends BaseClass{

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        logger.info("username provided");
        lp.setPassword(pwd);
        logger.info("password provided");
        lp.clickSubmit();

        Thread.sleep(3000);
        if (isAlertPresent() == true){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login failed");
        }else {
            Assert.assertTrue(true);
            logger.info("Login passed");
            lp.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept(); //close logout alert
            driver.switchTo().defaultContent();
        }
    }

    public boolean isAlertPresent(){
        try{

            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){

            return false;
        }


    }


    @DataProvider(name="LoginData")
     String[][] getData() throws IOException {
        String path = home + "src/test/java/com/ebanking/testData/LoginData.xlsx";
        int rowNum = XLUtils.getRowCount(path, "Sheet1");
        int columnNum = XLUtils.getCellCount(path, "Sheet1", 1);
        String[][] logindata = new String[rowNum][columnNum];

        for (int i=1; i<=rowNum; i++){
            for (int j=0; j<columnNum; j++){
                logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i,j);
            }
        }
         return logindata;
    }
}

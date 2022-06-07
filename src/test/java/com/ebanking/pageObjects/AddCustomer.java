package com.ebanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomer {

    WebDriver driver;

    public AddCustomer(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.LINK_TEXT, using = "New Customer")
    @CacheLookup
    WebElement lnkAddNewCustomer;

    @FindBy(how = How.NAME, using = "name")
    @CacheLookup
    WebElement customerName;

    @FindBy(how = How.NAME, using = "rad1")
    @CacheLookup
    WebElement gender;

    @FindBy(how = How.ID_OR_NAME, using = "dob")
    @CacheLookup
    WebElement dob;

    @FindBy(how = How.NAME, using = "addr")
    @CacheLookup
    WebElement address;

    @FindBy(how = How.NAME, using = "city")
    @CacheLookup
    WebElement city;

    @FindBy(how = How.NAME, using = "state")
    @CacheLookup
    WebElement state;

    @FindBy(how = How.NAME, using = "pinno")
    @CacheLookup
    WebElement pinno;

    @FindBy(how = How.NAME, using = "telephoneno")
    @CacheLookup
    WebElement telephoneno;

    @FindBy(how = How.NAME, using = "emailid")
    @CacheLookup
    WebElement emailid;
/*
    @FindBy(how = How.NAME, using = "password")
    @CacheLookup
    WebElement password;*/

    @FindBy(how = How.NAME, using = "sub")
    @CacheLookup
    WebElement btnSubmit;


    public void clickAddNewCustomer(){
        lnkAddNewCustomer.click();
    }

    public void custName(String cname){
        customerName.sendKeys(cname);
    }

    public void custGender(String cgender){
        gender.click();
    }

    public void custdob(String mm, String dd, String yy){
        dob.sendKeys(mm);
        dob.sendKeys(dd);
        dob.sendKeys(yy);
    }

    public void custAddress(String caddress){
        address.sendKeys(caddress);
    }

    public void custcity(String ccity){
        city.sendKeys(ccity);
    }

    public void custState(String cstate){
        state.sendKeys(cstate);
    }

    public void custPinno(int cpinno){
        pinno.sendKeys(String.valueOf(cpinno));
    }

    public void custTelephone(String telphoneno){
        telephoneno.sendKeys(telphoneno);
    }

    public void custEmail(String email){
        emailid.sendKeys(email);
    }

    /*public void custPassword(String pwd){
        password.sendKeys(pwd);
    }*/

    public void custSubmit(){
        btnSubmit.click();
    }


}

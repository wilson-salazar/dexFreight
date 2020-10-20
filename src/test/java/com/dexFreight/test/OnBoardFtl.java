/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dexFreight.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Wilson
 */
public class OnBoardFtl {

    private WebDriver driver;
    private WebDriverWait wait;
    private Utilities util = new Utilities();
    private String KEYPATH = util.getKeypath();
    private String PATH = util.getPath();
    private String url;
    private long time;
    private long lowTime;

    private String user;
    private String password;

    private String shipment;
    private String bidOnBoard;
    private String mcNumber;
    private String dotNumber;
    private String companyName;
    private String fullName;
    private String email;
    private String phone;

    @Before
    public void setUp() throws FileNotFoundException, IOException, InterruptedException {

        System.setProperty(KEYPATH, PATH);
        Properties myProperties = new Properties();
        myProperties.load(new FileInputStream("src\\test\\java\\properties\\parameters.properties"));
        time = util.getFastTime();
        lowTime = util.getLowTime();

        // variables de usuario
        url = myProperties.getProperty("url");
        user = myProperties.getProperty("user");
        password = myProperties.getProperty("password");

        // variables de vista
        shipment = myProperties.getProperty("shipmentOnboard");
        email = myProperties.getProperty("emailCarrierOnBoard");
        bidOnBoard = myProperties.getProperty("bitOnboard");
        mcNumber = myProperties.getProperty("mcNumberOnboard");
        dotNumber = myProperties.getProperty("dotNumberOnboard");
        companyName = myProperties.getProperty("companyNameOnboard");
        fullName = myProperties.getProperty("fullNameOnboard");
        phone = myProperties.getProperty("phoneOnboard");

        //Start ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // maximizar pantalla del navegador
        driver.get(url);
        Thread.sleep(time);

    }

    @Test
    public void test() throws InterruptedException {

        // Login
        WebElement inputBoxUser = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement inputBoxPass = driver.findElement(By.xpath("//input[@id='pswd']"));
        WebElement buttonSignIn = driver
                .findElement(By.xpath("//button[@id='btnLogin']"));

        inputBoxUser.clear();
        inputBoxUser.sendKeys(user);
        inputBoxPass.clear();
        inputBoxPass.sendKeys(password);
        buttonSignIn.click();
        Thread.sleep(lowTime);

        WebElement shipmentElement = driver.findElement(By.xpath("//div[@id='" + shipment + "']"));
        shipmentElement.click();
        Thread.sleep(time);
        WebElement options = driver.findElement(By.xpath("//i[@id='detailsOptions']"));
        options.click();

        WebElement selectedOpt = driver.findElement(By.linkText("Onboard new carrier"));
        selectedOpt.click();
        Thread.sleep(time);

        //bid para onboad 
        if (bidOnBoard.equalsIgnoreCase("yes")) {
            WebElement rateElement = driver.findElement(By.xpath("//p[@id='onboardRate']"));
            String valor = rateElement.getText();
            WebElement rateField = driver.findElement(By.xpath("//input[@id='onboardRateField']"));
            rateField.sendKeys(valor);
        }
        
        if(!mcNumber.isEmpty()){
            WebElement mcNumberelement = driver.findElement(By.xpath("//input[@id='onboardMCNumber']"));
            mcNumberelement.sendKeys(mcNumber);
        }
        if(!dotNumber.isEmpty()){
            WebElement dotElement = driver.findElement(By.xpath("//input[@id='onboardDOT']"));
            dotElement.sendKeys(dotNumber);
        }
        
        WebElement companyNameElement = driver.findElement(By.xpath("//input[@id='onboardCompanyName']"));
        companyNameElement.sendKeys(companyName);
        WebElement fullNameElement = driver.findElement(By.xpath("//input[@id='onboardFullName']"));
        fullNameElement.sendKeys(fullName);
        WebElement emailElement = driver.findElement(By.xpath("//input[@id='onboardEmail']"));
        emailElement.sendKeys(email);
        WebElement phoneElement = driver.findElement(By.xpath("//input[@id='onboardPhone']"));
        phoneElement.sendKeys(phone);
        
        WebElement buttonElement = driver.findElement(By.xpath("//button[@id='onboardSendBtn']"));
        buttonElement.click();
        Thread.sleep(lowTime);
        
        WebElement messageElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));
        
        if(messageElement.getText().equalsIgnoreCase("Success")){
            System.out.println(util.getSUCCESSTEST());
        }else{
            System.out.println(util.getERRORTEST());
        }
        
    }

    @After
    public void tearDown() {
        //driver.quit();//cerrar navegador
    }

}

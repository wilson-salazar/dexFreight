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
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Wilson
 */
public class InviteFtl {

    private WebDriver driver;
    private WebDriverWait wait;
    private Utilitys util = new Utilitys();
    private String KEYPATH = util.getKeypath();
    private String PATH = util.getPath();
    private String url;
    private long time;
    private long lowTime;

    private String user;
    private String password;
    
    private String shipment;
    

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
        shipment = myProperties.getProperty("shipment");
        
        //Start ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // maximizar pantalla del navegador
        driver.get(url);
        Thread.sleep(time);

    }

    @Test
    public void test() throws InterruptedException {
        
        wait = new WebDriverWait(driver, time);
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
                
        WebElement shipmentElement = driver.findElement(By.xpath("//div[@id='"+shipment+"']"));
        shipmentElement.click();
        Thread.sleep(time);
        WebElement options = driver.findElement(By.xpath("//i[@id='detailsOptions']"));
        options.click();
        
        WebElement selectedOpt = driver.findElement(By.linkText("Invite a carrier"));
        selectedOpt.click();
        Thread.sleep(time);
        
        //WebElement selectedOpt = driver.findElement(By.linkText("Invite a carrier"));
        
        
        
    }

    @After
    public void tearDown() {
        // driver.quit();//cerrar navegador
    }

}

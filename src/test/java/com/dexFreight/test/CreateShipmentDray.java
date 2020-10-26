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
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Wilson
 */
public class CreateShipmentDray {
    
    private WebDriver driver;
    private Utilities util = new Utilities();

    private String KEYPATH = util.getKeypath();
    private String PATH = util.getPath();
    private String url;
    private long time;
    private long LowTime;
    private String user;
    private String password;
    
    //variables de la vista
    private String typeDrayage;
    private String brokerReference;
    private String carrierReference;
    
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, InterruptedException {
        
        System.setProperty(KEYPATH, PATH);
        Properties myProperties = new Properties();
        myProperties.load(new FileInputStream("src\\test\\java\\properties\\parameters.properties"));
        time = util.getFastTime();
        LowTime = util.getLowTime();
        
        // variables de usuario
        url = myProperties.getProperty("url");
        user = myProperties.getProperty("user");
        password = myProperties.getProperty("password");
        
        // variables de vista
        typeDrayage = myProperties.getProperty("typeDrayage");
        brokerReference = myProperties.getProperty("brokerReferenceIdDray");
        carrierReference = myProperties.getProperty("carrieReferenceIdDray");
        
        
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
        Thread.sleep(time);
        
        //Ir a Create Drayage
        WebElement shipmentSelector = driver.findElement(By.xpath("//a[@id='shipments']"));
        Thread.sleep(time);
        shipmentSelector.click();
        
        WebElement createDrayageOptionElement = driver.findElement(By.xpath("//a[contains(text(),'Create Drayage')]"));
        Thread.sleep(time);
        createDrayageOptionElement.click();
        Thread.sleep(time);
        
        //Import/Export
        Select typeSelect = new Select(driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[2]/div[2]/main[1]/div[2]/app-create-drayage[1]/form[1]/select[1]")));
        Thread.sleep(time);
        typeSelect.selectByVisibleText(typeDrayage);
        
        if(!brokerReference.isEmpty()){
            WebElement brokerReferenceElement = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[2]/div[2]/main[1]/div[2]/app-create-drayage[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/input[1]"));
            brokerReferenceElement.sendKeys(brokerReference);
        }
        if(!carrierReference.isEmpty()){
            WebElement carrierReferenceElement = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[2]/div[2]/main[1]/div[2]/app-create-drayage[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/input[1]"));
            carrierReferenceElement.sendKeys(carrierReference);
        }
        
        
    }
    
    @After
    public void tearDown(){
    
    }
    
}

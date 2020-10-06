package com.dexFreight.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateShipmentFtl {

    private WebDriver driver;
    private Utilitys util = new Utilitys();

    private String KEYPATH = util.getKeypath();
    private String PATH = util.getPath();
    private String url;
    private long time;
    private long LowTime;

    private String user;
    private String password;
    private String cityCodeOrig;
    private String cityCodeDest;
    private String locationType;
    private String phandlingUnits;
    private String ppakageType;
    private String ppieces;
    private String pweight;
    private String pdescription;
    private String plength;
    private String pwidth;
    private String pheight;
    private String puom;
    private String negotiable;
    private String prate;
    private String maxrate;
    private String accesorials;

    @Before
    public void setUp() throws InterruptedException, FileNotFoundException, IOException {
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
        cityCodeOrig = myProperties.getProperty("cityCodeOrig");
        cityCodeDest = myProperties.getProperty("cityCodeDest");
        locationType = myProperties.getProperty("locationType");
        phandlingUnits = myProperties.getProperty("handlingUnits");
        ppakageType = myProperties.getProperty("pakageType");
        ppieces = myProperties.getProperty("pieces");
        pweight = myProperties.getProperty("weight");
        pdescription = myProperties.getProperty("description");
        plength = myProperties.getProperty("length");
        pwidth = myProperties.getProperty("width");
        pheight = myProperties.getProperty("height");
        puom = myProperties.getProperty("uom");
        negotiable = myProperties.getProperty("negotiable");
        prate = myProperties.getProperty("rate");
        maxrate = myProperties.getProperty("maxrate");
        accesorials = myProperties.getProperty("accesorials");

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

        // Create Shipment
        WebElement shipmentSelector = driver.findElement(By.xpath("//a[@id='shipments']"));
        Thread.sleep(time);
        WebElement shipmentCreate = driver.findElement(By.xpath("//a[contains(text(),'Create Shipment')]"));
        Thread.sleep(time);
        shipmentSelector.click();
        Thread.sleep(time);
        shipmentCreate.click();
        Thread.sleep(time);

        // ==================== Origin ====================
        WebElement inputZidCode = driver.findElement(By.xpath("//input[@id='origin']"));
        Thread.sleep(time);
        inputZidCode.sendKeys(cityCodeOrig);
        Thread.sleep(time);

        List<WebElement> zidCodeOriginList = new ArrayList<WebElement>();
        zidCodeOriginList = driver
                .findElements(By.xpath("//typeahead-container[@class='dropdown open dropdown-menu']"));

        // solo trae un solo elemento, investigar
        if (zidCodeOriginList.size() > 0) {
            WebElement option = zidCodeOriginList.get(0);
            option.click();
        }

        // Objeto lista desplegable
        Select locationTypeList = new Select(driver.findElement(By.xpath("//select[@id='originLocationType']")));
        Thread.sleep(time);
        locationTypeList.selectByVisibleText(locationType);

        // ==================== origin accesorial ====================
        if (accesorials.equalsIgnoreCase("yes")) {
            WebElement originAccesorial = driver
                    .findElement(By.xpath("//ng-multiselect-dropdown[@id='pickupAccesorials']"));
            originAccesorial.click();
            Thread.sleep(time);
            WebElement optionAccesorial = driver.findElement(By.xpath("//div[@class='pickupAccessorials']//li[2]"));
            optionAccesorial.click();
        }

        // ==================== Destinaton ====================
        WebElement inputZidCodeDestination = driver.findElement(By.xpath("//input[@id='destination']"));
        Thread.sleep(time);
        inputZidCodeDestination.sendKeys(cityCodeDest);
        Thread.sleep(time);

        List<WebElement> zidCodeDestinationList = new ArrayList<WebElement>();
        zidCodeDestinationList = driver.findElements(By.xpath("//typeahead-container[@class='dropdown open dropdown-menu']"));
        if (zidCodeDestinationList.size() > 0) {
            WebElement option = zidCodeDestinationList.get(0);
            option.click();
        }

        Select locationTypeDes = new Select(driver.findElement(By.xpath("//select[@id='destinationLocationType']")));
        Thread.sleep(time);
        locationTypeDes.selectByVisibleText(locationType);

        // ==================== destination accesorial ====================
        if (accesorials.equalsIgnoreCase("yes")) {
            WebElement originAccesorial = driver.findElement(By.xpath("//ng-multiselect-dropdown[@id='deliveryAccesorials']"));
            originAccesorial.click();
            Thread.sleep(time);
            WebElement optionAccesorial = driver.findElement(By.xpath("//div[@class='deliveryAccessorials']//li[4]"));
            optionAccesorial.click();
        }

        // ==================== Cargo detail ====================
        WebElement butonRow = driver
                .findElement(By.xpath("//a[@id='addRowBtn']"));
        butonRow.click();
        // Items to ship
        WebElement handlingUnits = driver.findElement(By.xpath("//input[@id='handling']"));
        Select pakageType = new Select(driver.findElement(By.xpath("//select[@class='form-control ng-untouched ng-pristine ng-invalid']")));
        Thread.sleep(time);
        WebElement pieces = driver.findElement(By.xpath("//input[@id='pieces']"));
        WebElement weight = driver.findElement(By.xpath("//input[@id='weight']"));
        WebElement description = driver.findElement(By.xpath("//input[@id='loadDescription']"));
        WebElement length = driver.findElement(By.xpath("//input[@id='length']"));
        WebElement width = driver.findElement(By.xpath("//input[@id='width']"));
        WebElement height = driver.findElement(By.xpath("//input[@id='height']"));
        Select uom = new Select(driver.findElement(By.xpath("//select[@id='uom']")));
        Thread.sleep(time);
        WebElement btnAddDetail = driver.findElement(By.xpath("//button[@id='addDetailBtn']"));

        handlingUnits.sendKeys(phandlingUnits);
        pakageType.selectByVisibleText(ppakageType);
        pieces.sendKeys(ppieces);
        weight.sendKeys(pweight);
        description.sendKeys(pdescription);
        length.sendKeys(plength);
        width.sendKeys(pwidth);
        height.sendKeys(pheight);
        uom.selectByVisibleText(puom);
        btnAddDetail.click();

        // ==================== Pyment seccion ====================
        WebElement equipmentType = driver.findElement(By.xpath("//div[@id='content-equipment-type']"));
        equipmentType.click();
        Thread.sleep(time);
        WebElement itemEtype = driver.findElement(By.xpath("//div[contains(text(),'lowboy')]"));
        itemEtype.click();

        // ==================== Rate* ====================
        WebElement rate = driver.findElement(By.xpath("//input[@id='rate']"));
        rate.click();
        rate.sendKeys(prate);

        if (negotiable.equalsIgnoreCase("yes")) {
            WebElement negotiable = driver.findElement(By.xpath("//label[@class='switch']//i"));
            negotiable.click();
            WebElement max = driver.findElement(By.xpath("//input[@id='limit']"));
            max.sendKeys(maxrate);
        }

        WebElement btnPost = driver.findElement(By.xpath("//button[@id='post']"));
        btnPost.click();
        Thread.sleep(LowTime);

        WebElement message = driver.findElement(By.xpath("//h2[@id='swal2-title']"));
        String result = message.getText();

        if (!result.isEmpty() && result.equals("Shipment Posted")) {
            System.err.println("RESULT: ======== Success test... ========");
        } else {
            System.err.println("RESULT: ======== The end of the test is not as expected ========");
        }

    }

    @After
    public void tearDown() {
        // driver.quit();//cerrar navegador
    }

}

package com.dexFreight.test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.rmi.CORBA.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleLogin {

	
    private WebDriver driver;
    private Utilitys util = new Utilitys();
	
	private String KEYPATH = util.getKeypath();
	private String PATH = util.getPath();
	private String url;
	private String user;
	private String password;
	private long time;
	
	@Before
	public void setUp() throws InterruptedException, FileNotFoundException, IOException {
		System.setProperty(KEYPATH, PATH);
		Properties myProperties = new Properties();
		myProperties.load(new FileInputStream("C:\\Users\\Wilson\\eclipse-workspace\\DexFreight\\src\\test\\java\\properties\\parameters.properties"));
		time = util.getTime();
		
		url = myProperties.getProperty("url");
		user = myProperties.getProperty("user");
		password = myProperties.getProperty("password");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize(); //maximizar pantalla del navegador
		driver.get(url);
		System.out.println("Tiempo: >>>>>>>>>>>>>> " + time);
		Thread.sleep(time);	
	}
	
	@Test
	public void test() throws InterruptedException {
		WebElement inputBoxUser = driver.findElement(By.xpath("//input[@id='username']"));
		//Thread.sleep(2000);
		WebElement inputBoxPass = driver.findElement(By.xpath("//input[@id='pswd']"));
		WebElement buttonSignIn = driver.findElement(By.xpath("//button[@class='btn dxf-btn-red border-radius w-100']"));
		
		
		inputBoxUser.clear();
		inputBoxUser.sendKeys(user);
		inputBoxPass.clear();
		inputBoxPass.sendKeys(password);
		buttonSignIn.click();
		
	}
	
	@After
	public void tearDown() {
		//driver.quit();//cerrar navegador
		
	}
	
}

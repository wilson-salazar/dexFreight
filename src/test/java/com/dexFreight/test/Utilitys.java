package com.dexFreight.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilitys {

	private final String KEYPATH = "webdriver.chrome.driver";
	private final String PATH = "./src/test/resources/chromedriver85.0/chromedriver.exe";
	
	public Utilitys() {
		
	}
	
	
	
	public long getTime() throws FileNotFoundException, IOException {
		Properties myProperties = new Properties();
		myProperties.load(new FileInputStream("C:\\Users\\Wilson\\eclipse-workspace\\DexFreight\\src\\test\\java\\properties\\parameters.properties"));
		long time;
		return time = Long.parseLong(myProperties.getProperty("time"));	
	}
	
	public String getKeypath() {
		return KEYPATH;
	}
	public String getPath() {
		return PATH;
	}
	
}

package com.dexFreight.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

	private final String KEYPATH = "webdriver.chrome.driver";
	private final String PATH = "./src/test/resources/chromedriver85.0/chromedriver.exe";
	private final String SUCCESSTEST = "RESULT: ======== Success test... ========";
        private final String ERRORTEST = "ESULT: ======== The end of the test is not as expected ========";
        
	public Utilities() {
		
	}
	
	
	
	public long getFastTime() throws FileNotFoundException, IOException {
		Properties myProperties = new Properties();
		myProperties.load(new FileInputStream("src\\test\\java\\properties\\parameters.properties"));
		long time;
		return time = Long.parseLong(myProperties.getProperty("FastTime"));	
	}
        
        public long getLowTime() throws FileNotFoundException, IOException {
		Properties myProperties = new Properties();
		myProperties.load(new FileInputStream("src\\test\\java\\properties\\parameters.properties"));
		long time;
		return time = Long.parseLong(myProperties.getProperty("LowTime"));	
	}
        
	
	public String getKeypath() {
		return KEYPATH;
	}
	public String getPath() {
		return PATH;
	}

    public String getSUCCESSTEST() {
        return SUCCESSTEST;
    }

    public String getERRORTEST() {
        return ERRORTEST;
    }
	
}

package com.suraam.accepttest.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.suraam.accepttest.utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.suraam.accepttest.modelo.Tenant;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberEnvironment {

	public static final int DEFAULT_TIME_OUT_IN_SECONDS = 20;

	private WebDriver driver;
	private Scenario scenario;
	private Map<String, String> variables;
    private Utilities utilities;
    private Properties properties;
    
	public CucumberEnvironment() {
		String chromeDriverPath = obtenerUbicacionEjecutable(
		        System.getProperty("os.name").toLowerCase());

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        variables = new HashMap<>();
        utilities = new Utilities();
        properties = new Properties();
    }

	private String obtenerUbicacionEjecutable(String os) {
		if(esWindows(os)){
			return "src/test//resources/drivers/chromedriver.exe";
		}else if(esMac(os)){
			return "src/test//resources/drivers/chromedriver";
		}else {
			throw new IllegalStateException();
		}
	}

	@Before
	public void setup(Scenario scenario) {
		this.scenario = scenario;
	}

	@After
	public void dispose() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public Map<String, String> getVariables() {
		return variables;
	}
	
	public static boolean esWindows(String os) {
		return (os.contains("win"));
	}
	
	public static boolean esMac(String os) {
		return (os.contains("mac"));
	}

    public Utilities getUtilities() {
        return utilities;
    }

	public Properties getProperties(Tenant tenant) throws IOException {
		InputStream inputStream = null;
		try {
			String propFileName = MessageFormat.format("properties/path_{0}.properties ", tenant.getAlias());
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			properties.load(inputStream);
		}catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return properties;
	}
    
}

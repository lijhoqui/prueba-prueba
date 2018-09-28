package com.suraam.accepttest.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.suraam.accepttest.steps.CucumberEnvironment;

public class Utilities {

    private static final int MAXIMO_NUMERO_INTENTOS = 5;
    private static final long ESPERA_REINTENTOS = 500;
	
    public WebElement getElementById(WebDriver driver, String id){
        WebDriverWait wait = new WebDriverWait(driver, CucumberEnvironment.DEFAULT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

        return driver.findElement(By.id(id));
    }

    public WebElement getElementByXPath(WebDriver driver, String xpath){
        WebDriverWait wait = new WebDriverWait(driver, CucumberEnvironment.DEFAULT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getElementByLinkText(WebDriver driver, String linkText){
        WebDriverWait wait = new WebDriverWait(driver, CucumberEnvironment.DEFAULT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));

        return driver.findElement(By.linkText(linkText));
    }

    public WebElement getElementByClassName(WebDriver driver, String className){
        WebDriverWait wait = new WebDriverWait(driver, CucumberEnvironment.DEFAULT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));

        return driver.findElement(By.className(className));
    }

    public void clickElement(WebElement element) throws Exception {
        retryFunction(element::click);
    }

    public void clickElementById(WebDriver driver, String elementId) throws Exception {
        retryFunction(() -> {
            WebElement element = getElementById(driver, elementId);
            element.click();
        });
    }


    public void retryFunction(RetryRunnable metodo) throws Exception {
        int intentos = 0;
        while (true){
            try {
                metodo.run();
                break;
            }
            catch (Exception ex){
                if (intentos < MAXIMO_NUMERO_INTENTOS){
                    Thread.sleep(ESPERA_REINTENTOS);
                    intentos = intentos + 1;
                }
                else {
                    throw ex;
                }
            }
        }
    }

}

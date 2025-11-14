package com.testinium.And.PageElement;

import com.testinium.And.Backend.MobileAutomationException;
import com.testinium.And.Util.Driver.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class PageElementModel {

    static AppiumDriver driver = Driver.driver;
    String xPath;
    String id;
    String name;
    String className;
    String loggingName;
    String bounds;
    Integer timeout = 15;
    public enum selectorNames {XPATH, ID, NAME, CLASS_NAME, BOUNDS};
    protected static WebDriverWait webDriverWait;
    private static final Logger log = LoggerFactory.getLogger(TestiniumButton.class);

    public PageElementModel(selectorNames selectorName, String selectorValue) {
        switch (selectorName) {
            case ID:
                id = selectorValue;
                loggingName = "ID: " + selectorValue;
                break;
            case NAME:
                name = selectorValue;
                loggingName = "NAME: " + selectorValue;
                break;
            case XPATH:
                xPath = selectorValue;
                loggingName = "XPATH: " + selectorValue;
                break;
            case CLASS_NAME:
                className = selectorValue;
                loggingName = "CLASS: " + selectorValue;
                break;
            default:
                className = id;
                break;
        }
    }

    public String getxPath() {
        return xPath;
    }

    public void setxPath(String xPath) {
        this.xPath = xPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    protected String getLoggingName() {
        return loggingName;
    }

    public AppiumDriver getDriver(){
        return driver;
    }

    public WebElement getWebElement(){
        if(getId() != null){
            WebElement me;
            try {
                me = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.id(getId())));
            } catch (Exception ex) {
                System.err.println(
                        String.format("[%s] idli element bulunamadi! Hata mesaji: %s", By.id(getId()), ex.getMessage()));
                me = null;
            }
            return me;
        }
        else if(getName() != null){
            WebElement me;
            try {
                me = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.name(getName())));
            } catch (Exception ex) {
                System.err.println(
                        String.format("[%s] idli element bulunamadi! Hata mesaji: %s", By.name(getName()), ex.getMessage()));
                me = null;
            }
            return me;
        }
        else if(getxPath() != null){
            WebElement me;
            try {
                me = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(getxPath())));
            } catch (Exception ex) {
                System.err.println(
                        String.format("[%s] idli element bulunamadi! Hata mesaji: %s", By.xpath(getxPath()), ex.getMessage()));
                me = null;
            }
            return me;
        }
        else if(getClassName() != null){
            WebElement me;
            try {
                me = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className(getClassName())));
            } catch (Exception ex) {
                System.err.println(
                        String.format("[%s] idli element bulunamadi! Hata mesaji: %s", By.className(getClassName()), ex.getMessage()));
                me = null;
            }
            return me;
        }
        else{
            System.out.println("Herhangi bir parametre bulunamadi");
            return null;
        }
    }

    public List<WebElement> getElements(){
        List<WebElement> WebElementList = new ArrayList<WebElement>();
        if(getId() != null){
            WebElementList = findElements(By.id(getId()));
        }
        else if(getClassName() != null){
            WebElementList = findElements(By.className(getClassName()));
        }
        else if(getName() != null){
            WebElementList = findElements((By.name(getName())));
        }
        else if(getxPath() != null){
            WebElementList = findElements(By.xpath(getxPath()));
        }
        else{
            System.out.println("Herhangi bir parametre bulunamadi!");
            WebElementList = null;
        }
        return WebElementList;
    }


    public static List<WebElement> findElements(By byChildren) {

        List<WebElement> WebElementList = new ArrayList<WebElement>();
        List<WebElement> webElementList = new ArrayList<WebElement>();

        try {
            webElementList = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byChildren));
        } catch (Exception ex) {
            System.err.println(String.format("Sayfa kaynaginda [%s] idli element bulunamadi !", byChildren));
        }

        if (webElementList.isEmpty()) {
            return WebElementList;
        }

        for (WebElement we : webElementList) {

            WebElementList.add((WebElement) we);
        }
        return WebElementList;
    }

    public WebElement getAnElement(){
        WebElement me = null;
        try {
            me = getWebElement();
        } catch (NoSuchElementException e){
            String error = "ELEMENT NOT FOUND: " + getLoggingName();
            log.error(error);
            throw new MobileAutomationException(error);
        }
        return me;
    }

    public boolean isExist(){
        boolean isDisplayed = false;
        if (getAnElement() != null){
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public void waitFor(int... timeOut){
        int timeOutI = 2;
        if (timeOut.length != 0){
            timeOutI = timeOut[0];
        }
        try {
            Thread.sleep(timeOutI * 1000L);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}

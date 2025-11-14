package com.testinium.And.Pages;

import com.testinium.And.Backend.TestiniumAutomationContext;
import com.testinium.And.Backend.ContextKeys;
import com.testinium.And.Util.Driver.Driver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(TestiniumMasterPage.class);

    public void switchToTestiniumWebView() {
        log.info("Switching to Testinium WebView...");
        WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//your_webview_locator"))); // Replace with actual locator
        Set<String> contextHandles = Driver.driver.getContextHandles();
        for (String contextName : contextHandles) {
            if (contextName.contains("WEBVIEW")) {
                log.info("Found Testinium WebView: " + contextName);
                Driver.driver.context(contextName);
                break;
            }
        }
    }

    public void switchToNativeContext(){
        Driver.driver.context("NATIVE_APP");
    }

    public static void hideAndroidKeyboard() {

        try {
            Driver.driver.hideKeyboard();
        } catch (Exception ex) {
            System.err.println(String.format("Android klavye kapatilamadi. Hata: %s", ex.getMessage()));
        }
    }

    public void swipeDownAccordingToPhoneSize() {

        Dimension d = Driver.driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width / 8;
        int swipeEndWidth = width / 8;

        int swipeStartHeight = ((height * 80) / 100)-5;
        int swipeEndHeight = (height * 30) / 100;

        // W3C Actions API kullanımı
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), swipeStartWidth, swipeStartHeight));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), swipeEndWidth, swipeEndHeight));
        swipe.addAction(finger.createPointerUp(0));
        Driver.driver.perform(java.util.Collections.singletonList(swipe));

    }


    public void getWebviewPageSource() throws InterruptedException {
        log.info("Web view kaynak kodu cekiliyor.");
        Thread.sleep(10000);
        switchToTestiniumWebView();
        log.info(Driver.driver.getPageSource());
        switchToNativeContext();
    }

    public void chooseRegion(String country) throws InterruptedException {

        switch (country){
            case "TH":
                chooseCountry("Thailand");
                break;
            case "AU":
                chooseCountry("Australia");
                break;
            case "NZ":
                chooseCountry("New Zealand");
                break;
            case "ID":
                chooseCountry("Indonesia");
                break;
            case "SG":
                chooseCountry("Singapore");
                break;
            case "MY":
                swipeLittle();
                chooseCountry("Malaysia");
                break;
            case "PH":
                swipeLittle();
                chooseCountry("Philippines");
                break;
            case "HK":
                swipeLittle();
                chooseCountry("Hong Kong");
                break;
            default: log.info("Girdiğin parametre kodda tanimli degil!");
        }
        TestiniumAutomationContext.addContext(ContextKeys.COUNTRY,country);


        Thread.sleep(50000);
    }

    public void chooseCountry(String countryName) {
        try {
            // Element ID'sini ve elementlerin sınıf adlarını güncelleyin.
            By countryListLocator = By.id("country_list");
            String countryItemXPath = String.format("//android.widget.TextView[@text='%s']", countryName);
            By countryItemLocator = By.xpath(countryItemXPath);

            // Bekleme için WebDriverWait kullanın.
            WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(countryListLocator));

            // Ülke listesini bulun.
            WebElement countryList = Driver.driver.findElement(countryListLocator);
            List<WebElement> countryItems = countryList.findElements(By.xpath("//android.widget.TextView"));

            // Ülke adına göre eşleşen öğeyi bulun ve tıklayın.
            boolean isFound = false;
            for (WebElement countryItem : countryItems) {
                if (countryItem.getText().equals(countryName) && countryItem.isDisplayed()) {
                    log.info("Ülke bulundu: " + countryName);
                    countryItem.click();
                    isFound = true;
                    break;
                }
            }

            // Ülke bulunamadıysa hata fırlatın.
            assertThat(isFound).as("Aranan kriterde ülke bulunamadı").isTrue();
        } catch (Exception e) {
            // Hata yönetimi
            log.error("Ülke seçimi sırasında hata oluştu:", e);
            throw new RuntimeException("Ülke seçimi başarısız oldu", e);
        }
    }



    public void swipeLittle() throws InterruptedException {
        Thread.sleep(5000);
        Dimension d = Driver.driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width / 2;
        int swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 80) / 100;
        int swipeEndHeight = (height * 30) / 100;

        // W3C Actions API kullanımı
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), swipeStartWidth, swipeStartHeight));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), swipeEndWidth, swipeEndHeight));
        swipe.addAction(finger.createPointerUp(0));
        Driver.driver.perform(java.util.Collections.singletonList(swipe));
    }

    public String getARandomMail(){
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();
        String firstPart = getARandomString();
        String email = firstPart+"_"+seconds+"@"+"otomasyon.com";
        TestiniumAutomationContext.addContext(ContextKeys.EMAIL,email);
        return email;
    }

    public String getARandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String rand = buffer.toString();
        return rand;
    }

    /*public static String generatePhoneNumber() throws Exception {
        TVFormat phoneFormat = AutomationDBUtil.getInstance().getPhoneFormat(TestiniumAutomationContext.getContextValue(COUNTRY));
        Random randomNumber = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(phoneFormat.getInitial());
        int i = 0;
        while(i++ < phoneFormat.getDigit()) {
            int num = randomNumber.nextInt(10);
            sb.append(num);
        }
        TestiniumAutomationContext.addContext(PHONE,String.valueOf(sb));
        return String.valueOf(sb);
    }*/


}

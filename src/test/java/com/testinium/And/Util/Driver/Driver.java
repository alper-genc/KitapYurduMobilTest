package com.testinium.And.Util.Driver;

import com.testinium.And.Backend.TestiniumAutomationContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import com.testinium.And.Backend.ContextKeys;
import com.testinium.And.Util.Report.ExcelUtil;

public class Driver {
    public static AndroidDriver driver; // Generic AppiumDriver yerine AndroidDriver

    @BeforeAll
    public static void initializeDriver() throws MalformedURLException {
        driver = DriverFactory.getDriver(); // DriverFactory'den driver alma (implicit wait içinde ayarlanıyor)
        
        // Tüm testler başlamadan önce uygulama reset et (sadece bir kere)

      /*  try {
            String packageName = "com.mobisoft.kitapyurdu";
            
            // Uygulamayı kapat
            driver.terminateApp(packageName);
            Thread.sleep(1000); // Kapatmak için biraz bekle
            
            // Uygulamayı tekrar aç
            driver.activateApp(packageName);
            Thread.sleep(2000); // Açılmak için biraz bekle
            
            System.out.println("Tüm testler başlamadan önce uygulama reset edildi: " + packageName);
        } catch (Exception e) {
            System.out.println("Uygulama reset edilirken hata: " + e.getMessage());
        }*/
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        TestiniumAutomationContext.initializeContext();
        TestiniumAutomationContext.addContext(ContextKeys.CASENAME, scenario.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        TestiniumAutomationContext.addContext(ContextKeys.STARTTIME, sdf.format(Calendar.getInstance().getTime()));
    }

    @After
    public void afterScenario(Scenario scenario) {
        String currentContext = driver.getContext();
        if (!currentContext.equals("NATIVE_APP")) {
            driver.context("NATIVE_APP");
        }

        ExcelUtil excelUtil = ExcelUtil.getInstance();
        String domain = "Mobile Test Suite"; // Cucumber'da specification name yerine sabit değer
        String testCase = scenario.getName();
        boolean isFailed = scenario.isFailed();
        String message = TestiniumAutomationContext.getContextValue(ContextKeys.EXCEPTION);
        String ssLink = TestiniumAutomationContext.getContextValue(ContextKeys.SSLINK);
        String loginData = TestiniumAutomationContext.getContextValue(ContextKeys.EMAIL);
        String startTime = TestiniumAutomationContext.getContextValue(ContextKeys.STARTTIME);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long startLong = 0L;
        try {
            startLong = sdf.parse(startTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long endLong = Calendar.getInstance().getTime().getTime();
        long durationLong = endLong - startLong;
        long durationSeconds = durationLong / 1000 % 60;
        long durationMinutes = durationLong / (60 * 1000) % 60;
        String duration = durationMinutes + ":" + durationSeconds;
        excelUtil.createReportSheet(domain, testCase, isFailed, message, ssLink, loginData, startTime, duration);

    }

    @AfterAll
    public static void closeDriver() {
        // Test bitince uygulamayı kapatmıyoruz, açık bırakıyoruz
        if (driver != null) {
            System.out.println("Testler tamamlandı, uygulama açık bırakılıyor...");
            // driver.quit(); // Bu satırı kaldırdık - uygulama açık kalacak
        }
    }
}

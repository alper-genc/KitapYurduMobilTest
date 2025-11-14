package com.testinium.And.Util.Driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("env/default/default.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Properties dosyası yüklenemedi: " + e.getMessage());
            // Fallback değerler
            setDefaultProperties();
        }
    }

    private static void setDefaultProperties() {
        properties.setProperty("appium.server.url", "http://127.0.0.1:4723");
        properties.setProperty("android.platform.name", "Android");
        properties.setProperty("android.device.name", "emulator-5554");
        properties.setProperty("android.avd.name", "Medium_Phone_API_36.1");
        properties.setProperty("android.automation.name", "UiAutomator2");
        properties.setProperty("app.package", "com.mobisoft.kitapyurdu");
        properties.setProperty("implicit.wait", "10");
    }

    public static AndroidDriver getDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        // Platform ve cihaz bilgileri
        options.setPlatformName(properties.getProperty("android.platform.name"));
        options.setDeviceName(properties.getProperty("android.device.name"));
        options.setAvd(properties.getProperty("android.avd.name"));

        // Uygulama bilgileri
        options.setAppPackage(properties.getProperty("app.package"));
        // Activity belirtmiyoruz - Appium otomatik launcher activity'sini bulacak
        
        // Otomasyon motoru
        options.setAutomationName(properties.getProperty("android.automation.name"));
        
        // Uygulama otomatik açılmasın, biz manuel kontrol edeceğiz
        options.setNoReset(false);     // Uygulamayı otomatik açma
        options.setFullReset(false);  // Tam reset değil
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAutoGrantPermissions(true);
        
        // Appium'un launcher activity'sini otomatik bulmasını sağla
        options.setAppWaitActivity("*");  // Herhangi bir activity'yi kabul et

        // Appium server URL
        String serverUrl = properties.getProperty("appium.server.url");
        AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);

        // Implicit wait ayarı
        int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait", "10"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        return driver;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

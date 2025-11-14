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
        // Activity belirtiyoruz - Uygulamanın açılması için gerekli
        String appActivity = properties.getProperty("app.activity", "com.mobisoft.kitapyurdu.main.SplashActivity");
        if (appActivity != null && !appActivity.isEmpty()) {
            options.setAppActivity(appActivity);
        }
        
        // Otomasyon motoru
        options.setAutomationName(properties.getProperty("android.automation.name"));
        
        // Uygulama ayarları
        options.setNoReset(false);     // Uygulamayı otomatik açma
        options.setFullReset(false);  // Tam reset değil
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAutoGrantPermissions(true);
        
        // Timeout ayarları - Uygulama başlatma için daha fazla zaman
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120)); // 120 saniye
        options.setAndroidInstallTimeout(Duration.ofSeconds(120)); // APK yükleme timeout
        
        // Appium'un launcher activity'sini otomatik bulmasını sağla
        // Activity belirtilmezse Appium otomatik bulur
        // options.setAppWaitActivity("*");  // Bu satırı kaldırdık, sorun çıkarabilir

        // Appium server URL
        String serverUrl = properties.getProperty("appium.server.url");
        AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);

        // Implicit wait ayarı
        int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait", "10"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        // Uygulama zaten setAppActivity ile açılacak, ekstra activateApp gerekmez
        // Ama emin olmak için bir bekleme ekleyelim
        try {
            Thread.sleep(3000); // Uygulamanın açılması için bekle
            System.out.println("Uygulama açılıyor: " + properties.getProperty("app.package"));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Bekleme sırasında kesinti: " + e.getMessage());
        }

        return driver;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

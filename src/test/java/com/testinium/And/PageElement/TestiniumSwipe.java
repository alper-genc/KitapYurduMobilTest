package com.testinium.And.PageElement;

import com.testinium.And.Backend.MobileAutomationException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.testinium.And.Util.Driver.Driver;

import java.time.Duration;

public class TestiniumSwipe extends PageElementModel {

    private static final Log log = LogFactory.getLog(TestiniumSwipe.class);

    // AndroidDriver olarak cast ettik
    private static final AndroidDriver driver = (AndroidDriver) Driver.driver;

    public TestiniumSwipe(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    /**
     * Ekranda belirtilen yöne doğru swipe işlemi gerçekleştirir.
     *
     * @param direction "left", "right", "up", "down"
     * @throws MobileAutomationException hata durumunda fırlatılır
     */
    public void swipe(String direction) throws MobileAutomationException {
        try {
            log.info("Swipe işlemi başlatıldı. Yön: " + direction);

            int screenWidth = driver.manage().window().getSize().width;
            int screenHeight = driver.manage().window().getSize().height;

            int startX = 0, endX = 0, startY = 0, endY = 0;

            switch (direction.toLowerCase()) {
                case "left":
                    startY = endY = screenHeight / 2;
                    startX = (int) (screenWidth * 0.8);
                    endX = (int) (screenWidth * 0.2);
                    break;
                case "right":
                    startY = endY = screenHeight / 2;
                    startX = (int) (screenWidth * 0.2);
                    endX = (int) (screenWidth * 0.8);
                    break;
                case "up":
                    startX = endX = screenWidth / 2;
                    startY = (int) (screenHeight * 0.8);
                    endY = (int) (screenHeight * 0.2);
                    break;
                case "down":
                    startX = endX = screenWidth / 2;
                    startY = (int) (screenHeight * 0.2);
                    endY = (int) (screenHeight * 0.8);
                    break;
                default:
                    throw new IllegalArgumentException("Geçersiz swipe yönü: " + direction);
            }

            new AndroidTouchAction(driver)
                    .press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, endY))
                    .release()
                    .perform();

            log.info("Swipe işlemi başarıyla tamamlandı.");
        } catch (Exception e) {
            log.error("Swipe işlemi sırasında hata oluştu: " + e.getMessage(), e);

        }
    }
}

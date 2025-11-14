
package com.testinium.And.Pages;

import com.testinium.And.PageElement.PageElementModel;
import com.testinium.And.PageElement.TestiniumButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.fail;


public class HomePage extends TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    // !! UYARI: Bu ID geçicidir ve test bir sonraki çalışmada muhtemelen hata verecektir.
    private static TestiniumButton BTN_Kampanyalar = new TestiniumButton(
            PageElementModel.selectorNames.ID, "00000000-0000-0059-ffff-ffff00000045");

    /**
     * Anasayfadaki kampanyalar sekmesine tıklar.
     */
    public void gotoCampaigns() {
        log.info("Kampanyalar sekmesine tıklanıyor (geçici ID ile)...");
        try {
            BTN_Kampanyalar.click();
            log.info("Kampanyalar sekmesine tıklandı.");
        } catch (Exception e) {
            log.error("Kampanyalar sekmesine tıklanamadı: " + e.getMessage());
            fail("Kampanyalar sekmesine tıklanamadı");
        }
    }
}
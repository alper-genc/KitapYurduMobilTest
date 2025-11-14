// Yeni dosya: src/test/java/com/testinium/And/Pages/CampaignsPage.java

package com.testinium.And.Pages;

import com.testinium.And.PageElement.PageElementModel;
import com.testinium.And.PageElement.TestiniumButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.fail;


public class CampaignsPage extends TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(CampaignsPage.class);

    // !! UYARI: Bu ID geçicidir ve test bir sonraki çalışmada muhtemelen hata verecektir.
    private static TestiniumButton BTN_OneCikanlar = new TestiniumButton(
            PageElementModel.selectorNames.ID, "00000000-0000-0065-ffff-ffff000002fe");

    /**
     * Kampanyalar sayfasındaki "Öne Çıkanlar" butonuna tıklar.
     */
    public void clickHighlights() {
        log.info("Öne Çıkanlar butonuna tıklanıyor (geçici ID ile)...");
        try {
            BTN_OneCikanlar.click();
            log.info("Öne Çıkanlar butonuna tıklandı.");
        } catch (Exception e) {
            log.error("Öne Çıkanlar butonuna tıklanamadı: " + e.getMessage());
            fail("Öne Çıkanlar butonuna tıklanamadı");
        }
    }
}
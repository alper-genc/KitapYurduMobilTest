
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

    // İlgi Görenler Tümü butonu - XPath ile (content-desc benzersiz olduğu için önce XPath)
    private static TestiniumButton BTN_IlgiGorenlerTumu_XPath = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.view.View[@content-desc='İlgi Görenler Tümü']");
    
    // Alternatif locator - Resource ID ile (birden fazla buton aynı ID'ye sahip olabilir)
    private static TestiniumButton BTN_IlgiGorenlerTumu_ID = new TestiniumButton(
            PageElementModel.selectorNames.ID, "com.mobisoft.kitapyurdu:id/textViewAllClick");

    // Çok Satanlar Tümü butonu - XPath ile (content-desc benzersiz olduğu için önce XPath)
    private static TestiniumButton BTN_CokSatanlarTumu_XPath = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.view.View[@content-desc='Çok Satanlar Tümü']");
    
    // Alternatif locator - Resource ID ile (birden fazla buton aynı ID'ye sahip olabilir)
    private static TestiniumButton BTN_CokSatanlarTumu_ID = new TestiniumButton(
            PageElementModel.selectorNames.ID, "com.mobisoft.kitapyurdu:id/textViewAllClick");

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

    /**
     * İlgi Görenler bölümündeki "Tümü" butonuna tıklar.
     */
    public void clickIlgiGorenlerTumu() {
        log.info("İlgi Görenler Tümü butonuna tıklanıyor...");
        try {
            // Önce XPath ile dene (content-desc benzersiz)
            try {
                BTN_IlgiGorenlerTumu_XPath.click();
                log.info("İlgi Görenler Tümü butonuna tıklandı (XPath ile).");
                return;
            } catch (Exception e1) {
                log.warn("XPath ile element bulunamadı, Resource ID ile deneniyor...");
                // XPath ile bulunamazsa Resource ID ile dene
                BTN_IlgiGorenlerTumu_ID.click();
                log.info("İlgi Görenler Tümü butonuna tıklandı (Resource ID ile).");
            }
        } catch (Exception e) {
            log.error("İlgi Görenler Tümü butonuna tıklanamadı: " + e.getMessage());
            fail("İlgi Görenler Tümü butonuna tıklanamadı: " + e.getMessage());
        }
    }

    /**
     * Çok Satanlar bölümündeki "Tümü" butonuna tıklar.
     */
    public void clickCokSatanlarTumu() {
        log.info("Çok Satanlar Tümü butonuna tıklanıyor...");
        try {
            // Önce XPath ile dene (content-desc benzersiz)
            try {
                BTN_CokSatanlarTumu_XPath.click();
                log.info("Çok Satanlar Tümü butonuna tıklandı (XPath ile).");
                return;
            } catch (Exception e1) {
                log.warn("XPath ile element bulunamadı, Resource ID ile deneniyor...");
                // XPath ile bulunamazsa Resource ID ile dene
                BTN_CokSatanlarTumu_ID.click();
                log.info("Çok Satanlar Tümü butonuna tıklandı (Resource ID ile).");
            }
        } catch (Exception e) {
            log.error("Çok Satanlar Tümü butonuna tıklanamadı: " + e.getMessage());
            fail("Çok Satanlar Tümü butonuna tıklanamadı: " + e.getMessage());
        }
    }
}
package com.testinium.And.Pages;

import com.testinium.And.PageElement.PageElementModel;
import com.testinium.And.PageElement.TestiniumButton;
import com.testinium.And.PageElement.TestiniumTextBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.fail;

public class SearchPage extends TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(SearchPage.class);

    // Arama sekmesi butonu (FrameLayout - tıklanabilir)
    private static TestiniumButton BTN_AramaSekmesi = new TestiniumButton(
            PageElementModel.selectorNames.ID, "com.mobisoft.kitapyurdu:id/navigation_search");
    
    // Alternatif locator - Accessibility ID ile
    private static TestiniumButton BTN_AramaSekmesi_Accessibility = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.widget.FrameLayout[@content-desc='Ara']");

    // Arama kutusu elementi (tıklamadan sonra açılan sayfadaki input alanı)
    // Bu element ID'sini Appium Inspector'dan almanız gerekecek
    // Şimdilik genel bir XPath kullanıyoruz
    private static TestiniumTextBox TXT_AramaKutusu = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "//android.widget.EditText");

    /**
     * Arama sekmesine tıklar.
     */
    public void clickSearchBox() {
        log.info("Arama sekmesine tıklanıyor...");
        try {
            // Önce ID ile deneyelim
            try {
                BTN_AramaSekmesi.click();
                log.info("Arama sekmesine tıklandı (ID ile).");
                return;
            } catch (Exception e1) {
                log.warn("ID ile element bulunamadı, XPath ile deneniyor...");
                // ID ile bulunamazsa XPath ile dene
                BTN_AramaSekmesi_Accessibility.click();
                log.info("Arama sekmesine tıklandı (XPath ile).");
            }
        } catch (Exception e) {
            log.error("Arama sekmesine tıklanamadı: " + e.getMessage());
            fail("Arama sekmesine tıklanamadı. Element bulunamadı: " + e.getMessage());
        }
    }

    /**
     * Arama kutusuna metin yazar.
     * @param searchText Aranacak metin
     */
    public void typeSearchText(String searchText) {
        log.info("Arama kutusuna metin yazılıyor: " + searchText);
        try {
            // Kısa bir bekleme ekle (sayfa yüklenmesi için)
            Thread.sleep(2000);
            TXT_AramaKutusu.type(searchText);
            log.info("Arama kutusuna metin yazıldı: " + searchText);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Bekleme sırasında kesinti: " + e.getMessage());
        } catch (Exception e) {
            log.error("Arama kutusuna metin yazılamadı: " + e.getMessage());
            fail("Arama kutusuna metin yazılamadı: " + e.getMessage());
        }
    }

    /**
     * Arama sekmesine tıklar ve metin yazar.
     * @param searchText Aranacak metin
     */
    public void clickAndTypeSearchText(String searchText) {
        clickSearchBox();
        typeSearchText(searchText);
    }
}


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
    // Daha spesifik locator'lar - önce ID ile, sonra XPath ile dene
    private static TestiniumTextBox TXT_AramaKutusu_ID = new TestiniumTextBox(
            PageElementModel.selectorNames.ID, "com.mobisoft.kitapyurdu:id/search_src_text");
    
    // Alternatif locator'lar
    private static TestiniumTextBox TXT_AramaKutusu_XPath1 = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "//android.widget.EditText[@hint='Ara' or @content-desc='Ara' or contains(@resource-id, 'search')]");
    
    private static TestiniumTextBox TXT_AramaKutusu_XPath2 = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "//androidx.appcompat.widget.SearchView//android.widget.EditText");
    
    private static TestiniumTextBox TXT_AramaKutusu_XPath3 = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "(//android.widget.EditText)[1]");

    // Sepete ekle butonu - ELEMENT BİLGİSİ GEREKLİ
    private static TestiniumButton BTN_SepeteEkle = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.widget.Button[@text='Sepete Ekle' or @content-desc='Sepete Ekle']");

    // Arama sonucu elementi - dinamik olarak text ile bulunur
    // Örnek: "Suç ve Ceza" için //android.widget.TextView[@resource-id="com.mobisoft.kitapyurdu:id/list_content" and @text="suc ve ceza"]

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
     * Arama kutusu elementini bulur (birden fazla locator dener).
     * @return TestiniumTextBox elementi
     */
    private TestiniumTextBox findSearchBox() {
        log.info("Arama kutusu aranıyor...");
        
        // Locator'ları sırayla dene
        TestiniumTextBox[] locators = {
            TXT_AramaKutusu_ID,
            TXT_AramaKutusu_XPath1,
            TXT_AramaKutusu_XPath2,
            TXT_AramaKutusu_XPath3
        };
        
        for (int i = 0; i < locators.length; i++) {
            TestiniumTextBox locator = locators[i];
            try {
                Thread.sleep(500); // Kısa bekleme
                if (locator.getAnElement() != null) {
                    log.info("Arama kutusu bulundu (Locator " + (i + 1) + "/" + locators.length + ")");
                    return locator;
                }
            } catch (Exception e) {
                log.debug("Locator " + (i + 1) + " deneniyor - Bulunamadı: " + e.getMessage());
                continue;
            }
        }
        
        throw new RuntimeException("Arama kutusu hiçbir locator ile bulunamadı!");
    }

    /**
     * Arama kutusuna metin yazar.
     * @param searchText Aranacak metin
     */
    public void typeSearchText(String searchText) {
        log.info("Arama kutusuna metin yazılıyor: " + searchText);
        int maxRetries = 3;
        int retryCount = 0;
        
        while (retryCount < maxRetries) {
            try {
                // Sayfa yüklenmesi için bekle
                Thread.sleep(2000);
                
                // Arama kutusunu bul
                TestiniumTextBox searchBox = findSearchBox();
                
                // Element'i her seferinde yeniden al (stale element reference hatası için)
                org.openqa.selenium.WebElement element = searchBox.getAnElement();
                if (element == null) {
                    throw new RuntimeException("Arama kutusu elementi null!");
                }
                
                // Önce arama kutusuna tıkla (focus için)
                element.click();
                Thread.sleep(500);
                
                // Metni temizle (varsa)
                try {
                    element.clear();
                } catch (Exception e) {
                    log.warn("Metin temizlenemedi, devam ediliyor: " + e.getMessage());
                }
                Thread.sleep(300);
                
                // Metni tek seferde yaz (daha güvenilir)
                element.sendKeys(searchText);
                Thread.sleep(500);
                
                // Yazılan metni kontrol et
                String writtenText = element.getText();
                if (writtenText == null || writtenText.isEmpty()) {
                    // getText() çalışmazsa getAttribute ile dene
                    writtenText = element.getAttribute("text");
                }
                
                log.info("Arama kutusuna yazılan metin: " + writtenText);
                
                // Klavyeyi kapat
                Thread.sleep(500);
                try {
                    hideAndroidKeyboard();
                } catch (Exception e) {
                    log.warn("Klavye kapatılamadı: " + e.getMessage());
                }
                
                log.info("Arama kutusuna metin başarıyla yazıldı: " + searchText);
                return; // Başarılı, çık
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Bekleme sırasında kesinti: " + e.getMessage());
                throw new RuntimeException("Bekleme sırasında kesinti", e);
            } catch (Exception e) {
                retryCount++;
                log.warn("Arama kutusuna yazma denemesi başarısız (Deneme " + retryCount + "/" + maxRetries + "): " + e.getMessage());
                
                if (retryCount >= maxRetries) {
                    log.error("Arama kutusuna metin yazılamadı (tüm denemeler başarısız): " + e.getMessage());
                    fail("Arama kutusuna metin yazılamadı: " + e.getMessage());
                } else {
                    // Retry öncesi bekle
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
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

    // Arama sonucu elementi - ListView içindeki ilk LinearLayout
    private static TestiniumButton BTN_AramaSonucu = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.widget.ListView[@resource-id='com.mobisoft.kitapyurdu:id/searchListView']/android.widget.LinearLayout[1]");

    /**
     * Arama sonucuna tıklar (ilk sonuca tıklar).
     * @param searchText Aranan metin (log için)
     */
    public void clickSearchResult(String searchText) {
        log.info("Arama sonucuna tıklanıyor: " + searchText);
        try {
            // Arama sonuçlarının yüklenmesi için bekle
            Thread.sleep(2000);
            
            // İlk arama sonucuna tıkla
            BTN_AramaSonucu.click();
            Thread.sleep(1000); // Sayfa yüklenmesi için bekle
            
            log.info("Arama sonucuna tıklandı: " + searchText);
        } catch (Exception e) {
            log.error("Arama sonucuna tıklanamadı: " + e.getMessage());
            fail("Arama sonucuna tıklanamadı: " + e.getMessage());
        }
    }

    /**
     * Sepete ekle butonuna tıklar.
     */
    public void clickSepeteEkle() {
        log.info("Sepete ekle butonuna tıklanıyor...");
        try {
            Thread.sleep(2000); // Arama sonuçlarının yüklenmesi için bekle
            BTN_SepeteEkle.click();
            Thread.sleep(1000); // Sepete ekleme işleminin tamamlanması için bekle
            log.info("Sepete ekle butonuna tıklandı.");
        } catch (Exception e) {
            log.error("Sepete ekle butonuna tıklanamadı: " + e.getMessage());
            fail("Sepete ekle butonuna tıklanamadı: " + e.getMessage());
        }
    }
}


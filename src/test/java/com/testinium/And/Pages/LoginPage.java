package com.testinium.And.Pages;

import com.testinium.And.PageElement.PageElementModel;
import com.testinium.And.PageElement.TestiniumButton;
import com.testinium.And.PageElement.TestiniumTextBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.fail;

public class LoginPage extends TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    // Hesabım butonu
    private static TestiniumButton BTN_Hesabim = new TestiniumButton(
            PageElementModel.selectorNames.ID, "com.mobisoft.kitapyurdu:id/btn_ic_header_account");
    
    // Alternatif locator - XPath ile
    private static TestiniumButton BTN_Hesabim_XPath = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.widget.ImageButton[@content-desc='Hesabım']");

    // E-posta input alanı - ELEMENT BİLGİSİ GEREKLİ
    private static TestiniumTextBox TXT_Email = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "//android.widget.EditText[@hint='E-posta' or @content-desc='E-posta']");

    // Şifre input alanı - ELEMENT BİLGİSİ GEREKLİ
    private static TestiniumTextBox TXT_Sifre = new TestiniumTextBox(
            PageElementModel.selectorNames.XPATH, "//android.widget.EditText[@hint='Şifre' or @content-desc='Şifre' or @password='true']");

    // Giriş butonu - ELEMENT BİLGİSİ GEREKLİ
    private static TestiniumButton BTN_Giris = new TestiniumButton(
            PageElementModel.selectorNames.XPATH, "//android.widget.Button[@text='Giriş Yap' or @content-desc='Giriş Yap']");

    /**
     * Hesabım sekmesine tıklar.
     */
    public void clickHesabim() {
        log.info("Hesabım sekmesine tıklanıyor...");
        try {
            // Önce ID ile deneyelim
            try {
                BTN_Hesabim.click();
                log.info("Hesabım sekmesine tıklandı (ID ile).");
                return;
            } catch (Exception e1) {
                log.warn("ID ile element bulunamadı, XPath ile deneniyor...");
                // ID ile bulunamazsa XPath ile dene
                BTN_Hesabim_XPath.click();
                log.info("Hesabım sekmesine tıklandı (XPath ile).");
            }
        } catch (Exception e) {
            log.error("Hesabım sekmesine tıklanamadı: " + e.getMessage());
            fail("Hesabım sekmesine tıklanamadı. Element bulunamadı: " + e.getMessage());
        }
    }

    /**
     * E-posta alanına metin yazar.
     * @param email E-posta adresi
     */
    public void typeEmail(String email) {
        log.info("E-posta alanına metin yazılıyor: " + email);
        try {
            Thread.sleep(1000);
            TXT_Email.getAnElement().click();
            Thread.sleep(300);
            TXT_Email.clearText();
            Thread.sleep(200);
            TXT_Email.type(email);
            Thread.sleep(500);
            log.info("E-posta alanına metin yazıldı: " + email);
        } catch (Exception e) {
            log.error("E-posta alanına metin yazılamadı: " + e.getMessage());
            fail("E-posta alanına metin yazılamadı: " + e.getMessage());
        }
    }

    /**
     * Şifre alanına metin yazar.
     * @param password Şifre
     */
    public void typePassword(String password) {
        log.info("Şifre alanına metin yazılıyor...");
        try {
            Thread.sleep(500);
            TXT_Sifre.getAnElement().click();
            Thread.sleep(300);
            TXT_Sifre.clearText();
            Thread.sleep(200);
            TXT_Sifre.type(password);
            Thread.sleep(500);
            hideAndroidKeyboard();
            log.info("Şifre alanına metin yazıldı.");
        } catch (Exception e) {
            log.error("Şifre alanına metin yazılamadı: " + e.getMessage());
            fail("Şifre alanına metin yazılamadı: " + e.getMessage());
        }
    }

    /**
     * Giriş butonuna tıklar.
     */
    public void clickGiris() {
        log.info("Giriş butonuna tıklanıyor...");
        try {
            Thread.sleep(1000);
            BTN_Giris.click();
            Thread.sleep(2000); // Giriş işleminin tamamlanması için bekle
            log.info("Giriş butonuna tıklandı.");
        } catch (Exception e) {
            log.error("Giriş butonuna tıklanamadı: " + e.getMessage());
            fail("Giriş butonuna tıklanamadı: " + e.getMessage());
        }
    }
}



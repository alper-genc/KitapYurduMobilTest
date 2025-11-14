package com.testinium.And.PageSteps;

import com.testinium.And.Pages.LoginPage;
import com.testinium.And.Pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAndSearchPageSteps {
    private static final Logger log = LoggerFactory.getLogger(LoginAndSearchPageSteps.class);
    private LoginPage loginPage;
    private SearchPage searchPage;

    public LoginAndSearchPageSteps() {
        this.loginPage = new LoginPage();
        this.searchPage = new SearchPage();
    }

    @Given("Hesabım sekmesine tıkla")
    public void hesabim_sekmesine_tikla() {
        log.info("Hesabım sekmesine tıklanıyor...");
        loginPage.clickHesabim();
    }

    @When("E-posta alanına {string} yaz")
    public void e_posta_alanina_yaz(String email) {
        log.info("E-posta alanına metin yazılıyor: " + email);
        loginPage.typeEmail(email);
    }

    @And("Şifre alanına {string} yaz")
    public void sifre_alanina_yaz(String password) {
        log.info("Şifre alanına metin yazılıyor...");
        loginPage.typePassword(password);
    }

    @And("Giriş butonuna tıkla")
    public void giris_butonuna_tikla() {
        log.info("Giriş butonuna tıklanıyor...");
        loginPage.clickGiris();
    }

    @Then("Arama sekmesine tıkla")
    public void arama_sekmesine_tikla() {
        log.info("Arama sekmesine tıklanıyor...");
        searchPage.clickSearchBox();
    }

    @And("Sepete ekle butonuna tıkla")
    public void sepete_ekle_butonuna_tikla() {
        log.info("Sepete ekle butonuna tıklanıyor...");
        searchPage.clickSepeteEkle();
    }

    @Then("Ürün sepete eklenmelidir")
    public void urun_sepete_eklenmelidir() {
        log.info("Ürünün sepete eklendiği kontrol ediliyor...");
        // Şimdilik sadece log, sonuç kontrolü için element bilgisi eklendiğinde implement edilecek
        log.info("Sepete ekleme işlemi tamamlandı.");
    }
}



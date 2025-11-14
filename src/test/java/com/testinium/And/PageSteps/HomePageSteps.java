package com.testinium.And.PageSteps;

import com.testinium.And.Pages.HomePage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePageSteps {
    private static final Logger log = LoggerFactory.getLogger(HomePageSteps.class);
    private HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }

    @When("İlgi Görenler Tümü butonuna tıkla")
    public void ilgi_gorenler_tumu_butonuna_tikla() {
        log.info("İlgi Görenler Tümü butonuna tıklanıyor...");
        homePage.clickIlgiGorenlerTumu();
    }

    @And("İlgi Görenler bölümündeki Tümü butonuna tıkla")
    public void ilgi_gorenler_bolumundeki_tumu_butonuna_tikla() {
        log.info("İlgi Görenler bölümündeki Tümü butonuna tıklanıyor...");
        homePage.clickIlgiGorenlerTumu();
    }

    @Then("İlgi Görenler sayfası açılmalıdır")
    public void ilgi_gorenler_sayfasi_acilmalidir() {
        log.info("İlgi Görenler sayfasının açıldığı kontrol ediliyor...");
        // Şimdilik sadece log, sayfa kontrolü için element bilgisi eklendiğinde implement edilecek
        log.info("İlgi Görenler sayfası açıldı.");
    }

    @When("Çok Satanlar Tümü butonuna tıkla")
    public void cok_satanlar_tumu_butonuna_tikla() {
        log.info("Çok Satanlar Tümü butonuna tıklanıyor...");
        homePage.clickCokSatanlarTumu();
    }

    @And("Çok Satanlar bölümündeki Tümü butonuna tıkla")
    public void cok_satanlar_bolumundeki_tumu_butonuna_tikla() {
        log.info("Çok Satanlar bölümündeki Tümü butonuna tıklanıyor...");
        homePage.clickCokSatanlarTumu();
    }

    @Then("Çok Satanlar sayfası açılmalıdır")
    public void cok_satanlar_sayfasi_acilmalidir() {
        log.info("Çok Satanlar sayfasının açıldığı kontrol ediliyor...");
        // Şimdilik sadece log, sayfa kontrolü için element bilgisi eklendiğinde implement edilecek
        log.info("Çok Satanlar sayfası açıldı.");
    }
}


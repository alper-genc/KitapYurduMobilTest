package com.testinium.And.PageSteps;

import com.testinium.And.Pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchPageSteps {
    private static final Logger log = LoggerFactory.getLogger(SearchPageSteps.class);
    private SearchPage searchPage;

    public SearchPageSteps() {
        this.searchPage = new SearchPage();
    }

    @Given("Ara sekmesine tıkla")
    public void ara_sekmesine_tikla() {
        log.info("Ara sekmesine tıklanıyor...");
        searchPage.clickSearchBox();
    }

    @When("Arama kutusuna {string} yaz")
    public void arama_kutusuna_yaz(String searchText) {
        log.info("Arama kutusuna metin yazılıyor: " + searchText);
        searchPage.typeSearchText(searchText);
    }

    @Then("Arama sonuçları listelenmelidir")
    public void arama_sonuclari_listelenmelidir() {
        log.info("Arama sonuçları kontrol ediliyor...");
        // Şimdilik sadece log, sonuç kontrolü için element bilgisi eklendiğinde implement edilecek
        log.info("Arama işlemi tamamlandı.");
    }
}


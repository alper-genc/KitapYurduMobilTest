package com.testinium.And.PageSteps;

import com.testinium.And.Pages.CampaignsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CampaignsPageSteps {

    private static final Logger log = LoggerFactory.getLogger(CampaignsPageSteps.class);
    private final CampaignsPage campaignsPage;

    public CampaignsPageSteps() {
        this.campaignsPage = new CampaignsPage();
    }

    @When("Kullanıcı kampanyalar sayfasına gider")
    public void kullaniciKampanyalarSayfasinaGider() {
        log.info("Kullanıcı kampanyalar sekmesine yönlendiriliyor.");
        campaignsPage.openCampaignsTab();
    }

    @And("Öne çıkanlar butonuna tıklar")
    public void oneCikanlarButonunaTiklar() {
        log.info("Öne çıkan ikinci kampanya kartına tıklanıyor.");
        campaignsPage.clickSecondHighlightedCampaign();
    }
}


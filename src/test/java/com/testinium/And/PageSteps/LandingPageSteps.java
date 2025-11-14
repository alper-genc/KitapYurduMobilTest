package com.testinium.And.PageSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import com.testinium.And.Pages.LandingPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LandingPageSteps {
    private static final Logger log = LoggerFactory.getLogger(LandingPageSteps.class);
    private LandingPage landingPage;


    @Given("Örnek test case'i çalıştır")
    public void ornek_test_case_calistir() {
        log.info("Örnek test case'i çalıştırılıyor...");
        // Test işlemleri burada yapılacak

    }

    @When("^Control (.*) Country Clicking$")
    public void control_country_clicking(String countryCode) {
        log.info(countryCode + " ülkesi kontrol ediliyor ve tıklanıyor");
        landingPage.clickCountry(countryCode);
    }


    @And("Kullanıcı bilgileri girilir")
    public void kullaniciGirisBilgileri() throws InterruptedException {

        LandingPage.logoLogin();
    }

    @And("Kullanıcı Giriş İşlemleri")
    public void kullaniciIsim(){
        LandingPage.logoIsim();
    }



}

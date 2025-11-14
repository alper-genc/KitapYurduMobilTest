package com.testinium.And.Pages;

import com.testinium.And.PageElement.PageElementModel;
import com.testinium.And.PageElement.TestiniumButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.fail;

public class CampaignsPage extends TestiniumMasterPage {

    private static final Logger log = LoggerFactory.getLogger(CampaignsPage.class);

    private static final TestiniumButton BTN_KAMPANYALAR_TAB = new TestiniumButton(
            PageElementModel.selectorNames.XPATH,
            "//android.widget.FrameLayout[@content-desc='Kampanyalar']"
    );

    private static final TestiniumButton BTN_SECOND_CAMPAIGN_CARD = new TestiniumButton(
            PageElementModel.selectorNames.XPATH,
            "(//android.widget.RelativeLayout[@resource-id='com.mobisoft.kitapyurdu:id/containerView'])[2]"
    );

    public void openCampaignsTab() {
        log.info("Kampanyalar sekmesine tıklanıyor...");
        try {
            BTN_KAMPANYALAR_TAB.click();
            log.info("Kampanyalar sekmesi başarıyla açıldı.");
        } catch (Exception e) {
            log.error("Kampanyalar sekmesine tıklanamadı: {}", e.getMessage());
            fail("Kampanyalar sekmesine tıklanamadı: " + e.getMessage());
        }
    }

    public void clickSecondHighlightedCampaign() {
        log.info("İkinci kampanya kartına tıklanıyor...");
        try {
            BTN_SECOND_CAMPAIGN_CARD.click();
            log.info("İkinci kampanya kartı açıldı.");
        } catch (Exception e) {
            log.error("İkinci kampanya kartına tıklanamadı: {}", e.getMessage());
            fail("İkinci kampanya kartına tıklanamadı: " + e.getMessage());
        }
    }
}


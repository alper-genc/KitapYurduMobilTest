package com.testinium.And.PageElement;

import com.testinium.And.Backend.MobileAutomationException;
import org.openqa.selenium.WebElement;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestiniumButton extends PageElementModel {
    private static final Logger log = LoggerFactory.getLogger(TestiniumButton.class);
    public TestiniumButton(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    public void click(){
        log.info("ABOUT TO CLICK BUTTON " + getLoggingName());
        WebElement me = getAnElement();
        try {
            me.click();
        } catch (Exception e) {
            String error = "COULD NOT CLICK BUTTON: " + getLoggingName();
            log.error(error);
            throw new MobileAutomationException(error);
        }
    }


}

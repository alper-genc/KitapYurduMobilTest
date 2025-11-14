package com.testinium.And.PageElement;

import com.testinium.And.Backend.MobileAutomationException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestiniumTextBox extends PageElementModel {
    private static final Logger log = LoggerFactory.getLogger(TestiniumTextBox.class);
    public TestiniumTextBox(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    public void type(String inputText){
        log.info("ABOUT TO TYPE TEXTBOX " + getLoggingName());
        WebElement me = getAnElement();
        try {
            me.sendKeys(inputText);
        } catch (Exception e) {
            String error = "COULD NOT TYPE TEXTBOX: " + getLoggingName() + " TEXT IS: " + inputText;
            log.error(error);
            throw new MobileAutomationException(error);
        }
    }

    public void clearText(){
        log.info("ABOUT TO CLEAR TEXTBOX " + getLoggingName());
        WebElement me = getAnElement();
        me.clear();
    }
}

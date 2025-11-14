package com.testinium.And.PageElement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebElement;

public class TestiniumLabel extends PageElementModel {
    private static final Logger log = LoggerFactory.getLogger(TestiniumButton.class);
    public TestiniumLabel(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    public String getElementText(){
        log.info("Getting elements's text :" + getLoggingName());
        String elementText = "";
        WebElement me = getAnElement();
        elementText = me.getText();
        return  elementText;
    }
}

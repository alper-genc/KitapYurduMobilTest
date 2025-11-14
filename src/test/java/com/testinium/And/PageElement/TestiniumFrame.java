package com.testinium.And.PageElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestiniumFrame extends PageElementModel {
    private static final Logger log = LoggerFactory.getLogger(TestiniumFrame.class);

    public TestiniumFrame(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    public String getFrame() {
        log.info("Getting elements's frame :" + getLoggingName());
        //TODO Frame eklenecek !!!
        return getFrame();
    }

}

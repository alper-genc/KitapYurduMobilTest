package com.testinium.And.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.testinium.And.PageSteps", "com.testinium.And.Util.Driver"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/cucumber-html-reports.html"
        },
        tags = "@smoke",
        publish = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

} 

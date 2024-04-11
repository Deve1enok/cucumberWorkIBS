package org.ibs.fazlyakhmetov.tests.qualit;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.ibs.fazlyakhmetov.config.qualit.QualitConfig;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

@Tag("@all")
public class BaseTest {
    public static WebDriver driver;
    public static QualitConfig configOwner = ConfigFactory.create(QualitConfig.class);

    @Before(value = "@all")
    public static void beforeAll() {
        System.setProperty("webdriver.chromedriver.driver", configOwner.chromeDriver());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get(configOwner.baseUrl());
    }

    @AfterStep(value = "@all")
    public void afterStep(Scenario scenario) throws IllegalMonitorStateException {
        Allure.addAttachment("Screenshot step", new ByteArrayInputStream(((TakesScreenshot) driver).
                getScreenshotAs(OutputType.BYTES)));
    }

    @After(value = "@all")
    public static void after() {
        driver.close();
        driver.quit();
    }
}


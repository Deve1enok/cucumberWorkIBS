package org.ibs.fazlyakhmetov.tests.qualit;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.aeonbits.owner.ConfigFactory;
import org.ibs.fazlyakhmetov.config.qualit.QualitConfig;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@Tag("@all")
public class BaseTest {
    public static WebDriver driver;
    public static QualitConfig configOwner = ConfigFactory.create(QualitConfig.class);

    @Before(value = "@all")
    public static void beforeAll() {
        System.setProperty("webdriver.chromedriver.driver", configOwner.chromeDriver());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get(configOwner.baseUrl());
    }

    @After(value = "@all")
    public static void afterAll() {
        driver.close();
        driver.quit();
    }
}


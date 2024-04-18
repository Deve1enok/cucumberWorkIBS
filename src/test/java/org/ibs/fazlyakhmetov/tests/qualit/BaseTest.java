package org.ibs.fazlyakhmetov.tests.qualit;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.ibs.fazlyakhmetov.config.qualit.QualitConfig;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

@Tag("@all")
public class BaseTest {
    public static WebDriver driver;
    public static QualitConfig configOwner = ConfigFactory.create(QualitConfig.class);

    @Before(value = "@all")
    public static void beforeAll() throws MalformedURLException {
        String selenoidRun = System.getProperty("selenoid.run");

        if (selenoidRun != null && selenoidRun.equalsIgnoreCase("true")) {
//            System.setProperty(configOwner.remoteDriver(), "remote");
//            System.setProperty(configOwner.selenoidUrl(), "http://149.154.71.152:4444/wd/hub");
//            System.setProperty(configOwner.typeBrowser(), "chrome");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", System.getProperty("type.browser"));
            capabilities.setCapability("browserVersion", System.getProperty("version.browser"));
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            driver = new RemoteWebDriver(URI.create(configOwner.selenoidUrl()).toURL(), capabilities);
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.get(configOwner.baseUrl());
        } else {
            System.setProperty("webdriver.chromedriver.driver", configOwner.chromeDriver());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.get(configOwner.baseUrl());
        }
        //            ChromeOptions options = new ChromeOptions();
//            options.setCapability("browserName", System.getProperty(configOwner.typeBrowser()));
//            options.setCapability("browserVersion", "109.0");
//            options.addArguments("start-maximized");
//            options.setCapability("selenoid:options", Map.<String, Object>of(
//                    "enableVNC", true,
//                    "enableVideo", true
//            ));
    }
    @After(value = "@all")
    public static void after() {
        String selenoidRun = System.getProperty("selenoid.run");

        if (selenoidRun != null && selenoidRun.equalsIgnoreCase("true")) {
            Allure.addAttachment("Latest screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.BYTES)));
            driver.quit();
        } else {
            driver.close();
            driver.quit();
        }
    }
}


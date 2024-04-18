package org.ibs.fazlyakhmetov.helpers;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static org.ibs.fazlyakhmetov.tests.qualit.BaseTest.driver;

public class Attach {
    public static void screenshotAs() {
        Allure.addAttachment("Latest screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).
                getScreenshotAs(OutputType.BYTES)));
    }
}

package org.ibs.fazlyakhmetov.pages;

import org.ibs.fazlyakhmetov.tests.qualit.BaseTest;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Tag("@all")
public class QualitMainPage {
    private static WebDriver driver;

    private static WebDriverWait wait;

    public QualitMainPage(WebDriver webDriver) {
        PageFactory.initElements(BaseTest.driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver = webDriver;
    }

    @FindBy(xpath = "//*[@id='navbarDropdown']")
    private WebElement sandboxDropDown;

    @FindBy(xpath = "//a[@href='/food']")
    private WebElement products;
    @FindBy(xpath = "//*[@id='reset']")
    private WebElement resetData;


    public QualitMainPage sandboxDropDownClick() {
        wait.until(ExpectedConditions.elementToBeClickable(sandboxDropDown)).click();
        return this;
    }

    public QualitMainPage menuProductClick() {
        products.click();
        return this;
    }


    public QualitMainPage resetResults() {
        resetData.click();
        return this;
    }
}

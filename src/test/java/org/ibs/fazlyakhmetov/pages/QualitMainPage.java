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
    public static WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public QualitMainPage(WebDriver webDriver) {
        PageFactory.initElements(BaseTest.driver, this);
        driver = webDriver;
    }

    @FindBy(xpath = "//li/a[@id='navbarDropdown']")
    private WebElement sandboxDropDown;

    @FindBy(xpath = "//a[@href='/food']")
    private WebElement products;
    @FindBy(xpath = "//div/a[@id='reset']")
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

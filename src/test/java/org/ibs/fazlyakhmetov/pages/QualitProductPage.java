package org.ibs.fazlyakhmetov.pages;

import lombok.extern.slf4j.Slf4j;
import org.ibs.fazlyakhmetov.tests.qualit.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag("@all")
public class QualitProductPage {
    public static WebDriver driver;
    public static WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

    public QualitProductPage(WebDriver webDriver) {
        PageFactory.initElements(BaseTest.driver, this);
        driver = webDriver;
    }

    @FindBy(xpath = "//div/button[@data-target='#editModal']")
    private WebElement addButton;
    @FindBy(xpath = "//div/input[@id='name']")
    private WebElement productNameField;
    @FindBy(xpath = "//div/select[@id='type']")
    private WebElement productType;
    @FindBy(xpath = "//div/select[@id='type']/option[@*='FRUIT']")
    private WebElement productTypeFruit;
    @FindBy(xpath = "//div/select[@id='type']/option[@*='VEGETABLE']")
    private WebElement productTypeVegetable;
    @FindBy(xpath = "//div/input[@*='exotic']")
    private WebElement exoticCheckbox;
    @FindBy(xpath = "//div/button[@*='save']")
    private WebElement saveButton;
    @FindBy(xpath = "//tbody/tr")
    private WebElement defaultTable;
    @FindBy(xpath = "//div/a[@id='reset']")
    private WebElement resetData;


    public QualitProductPage openMenuAddProducts() {
        addButton.click();
        return this;
    }

    public QualitProductPage setProductName(String value) {
        productNameField.sendKeys(value);
        return this;
    }


    public QualitProductPage selectProductTypeFruit() {
        productTypeFruit.click();
        return this;
    }

    public QualitProductPage selectProductTypeVegetable() {
        productTypeVegetable.click();
        return this;
    }


    public QualitProductPage selectExoticCheckboxTrue() {
        exoticCheckbox.click();
        return this;
    }


    public QualitProductPage clickSaveResults() {
        saveButton.click();
        return this;
    }

    /**
     * Цикл для подсчета количества элементов(продуктов) в таблице.
     * Используается для проверки результата
     *
     * @author Fazlyakhmetov_Dinar
     */

    public static int checkQuantityProducts() {
        int count = 0;
        for (int i = 1; i < 6; i++) {
            try {
                WebElement wb = driver.findElement(By.xpath("//tbody/tr[" + i + "]"));
                count++;
            } catch (Exception ex) {
                log.debug("Ошибка");
            }
        }
        return count;
    }

        /**
         * Цикл для перебора строки в таблице и сравнения строки с вашим парамтером
         * Используается для проверки результата
         *
         * @author Fazlyakhmetov_Dinar
         */


        public static void checkAddedFruit (String tableField){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr")));
            List<WebElement> productWebList = driver.findElements(By.xpath("//tbody/tr"));
            ArrayList<String> productTextList = new ArrayList<>();
            for (WebElement product : productWebList) {
                productTextList.add(product.getText());
            }
            if (productTextList.contains(tableField)) {
                log.info("Добавленный продукт совпадает");
            } else Assertions.fail("Добавленный продукт не совпадает");
        }
    }


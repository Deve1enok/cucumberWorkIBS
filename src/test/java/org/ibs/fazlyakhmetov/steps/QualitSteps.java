package org.ibs.fazlyakhmetov.steps;

import io.cucumber.java.ru.И;
import org.ibs.fazlyakhmetov.pages.QualitMainPage;
import org.ibs.fazlyakhmetov.pages.QualitProductPage;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;


@Tag("@all")
public class QualitSteps {
    public static WebDriver driver;
    QualitMainPage qualitMainPage = new QualitMainPage(driver);
    QualitProductPage qualitProductPage = new QualitProductPage(driver);


    @И("выполнено нажатие на \"Песочница\"")
    public void sandboxDropDownClick() {
        qualitMainPage.sandboxDropDownClick();
    }

    @И("выполнено нажатие на \"Товары\"")
    public void menuProductClick() {
        qualitMainPage.menuProductClick();
    }

    @И("выполнено нажатие на \"Добавить\"")
    public void openMenuAddProducts() {
        qualitProductPage.openMenuAddProducts();
    }

    @И("поле \"Наименование\" заполняется значением {string}")
    public void setProductName(String name) {
        qualitProductPage.setProductName(name);
    }

    @И("выполнен выбор значения \"Фрукт\" из выпадающего списка \"Тип\"")
    public void selectProductTypeFruit() {
        qualitProductPage.selectProductTypeFruit();
    }

    @И("выполнен выбор значения \"Овощ\" из выпадающего списка \"Тип\"")
    public void selectProductTypeVegetable() {
        qualitProductPage.selectProductTypeVegetable();
    }

    @И("выполнено нажатие на чек-бокс \"Экзотический\"")
    public void selectExoticCheckboxTrue() {
        qualitProductPage.selectExoticCheckboxTrue();
    }

    @И("выполнено нажатие на \"Сохранить\"")
    public void clickSaveResults() {
        qualitProductPage.clickSaveResults();
    }

    @И("проверено количество количество товаров в списке")
    public void checkQuantityProducts() {
        QualitProductPage.checkQuantityProducts();
    }

    @И("проверено добавление введенного {string}")
    public void checkAddedFruit(String value) {
        QualitProductPage.checkAddedFruit(value);
    }

    @И("выполнено нажатие на \"Сброс данных\"")
    public void resetResults() {
        qualitMainPage.resetResults();
    }

    @И("проверено удаление добавленного {string}")
    public void checkFruitAfterReset(String value) {
        QualitProductPage.checkAddedFruit(value);
    }
}
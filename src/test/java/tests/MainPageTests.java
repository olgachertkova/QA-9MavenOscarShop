package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPageHelper;

public class MainPageTests extends TestBase{
    MainPageHelper mainPage;

    @BeforeMethod
    public void initPage(){
        mainPage = PageFactory.initElements(driver, MainPageHelper.class);
    }

    @Test(priority = 2)
    public void changeLanguageTest(){
        mainPage.selectLanguage("ru");
        mainPage.clickOnGoButton();
        String text = mainPage.getViewBasketButtonText();
        Assert.assertEquals(text, "Посмотреть корзину");
    }

    @Test(priority = 1)
    public void changeLanguageTest2(){
        mainPage.selectLanguage("ru");
        mainPage.clickOnGoButton();
        mainPage.waitUntilTextChanged();
        String text = mainPage.getViewBasketButtonText();
        Assert.assertEquals(text, "Посмотреть корзину");
    }




}

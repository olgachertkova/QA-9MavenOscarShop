package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPageHelper;
import pages.ProductPageHelper;

import java.util.List;

public class OscarProductTests extends TestBase{
    ProductPageHelper productPage;
    MainPageHelper mainPage;

    @BeforeMethod
    public void initPage(){
        productPage = PageFactory.initElements(driver, ProductPageHelper.class);
        mainPage = PageFactory.initElements(driver, MainPageHelper.class);
    }

    @Test
    public void addProductToBasketTest(){
        mainPage.selectBooksCategory();
        Assert.assertTrue(productPage.isItBooksPage());
        List<WebElement> productList = driver.findElements(By.className("product_pod"));
        String firstBookName = productList.get(0).findElement(By.tagName("h3")).getText();
        String firstBookPrice = productList.get(0).findElement(By.cssSelector(".product_price .price_color")).getText();
        productPage.addToBasketFirstBook();
        Assert.assertTrue(productPage.alertsIsPresent());
        List<WebElement> alertsData = driver.findElements(By.cssSelector(".alert strong"));
        Assert.assertTrue(productPage.isItBookName(firstBookName, alertsData));
        Assert.assertTrue(productPage.isItBookPrice(firstBookPrice, alertsData));
    }

    @Test
    public void countProductInBasketTest(){
        mainPage.selectBooksCategory();
        productPage.addToBasketFirstBook();
//        selectBooksCategorySecondTime();
        driver.navigate().refresh();
        productPage.addToBasketFirstBook();
        productPage.clickOnViewBasketButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Basket')]")));
        Assert.assertTrue(pageTitle.isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().contains("basket"));
        int count = productPage.getProductCountInBasket();
        Assert.assertTrue(count==2);
    }

    @Test
    public void productListShouldBeVisible(){
        mainPage.selectBooksCategory();
        productPage.waitUntilAllBooksIsVisible();
        int size = productPage.getBooksCount();

        Assert.assertEquals(size, 20, "Quantity of items is not valid");
    }

    @Test
    public void getMinPriceTest(){
        mainPage.selectBooksCategory();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.waitUntilAllBooksIsVisible();
        System.out.println(productPage.getBookPriceList().toString());

    }


}

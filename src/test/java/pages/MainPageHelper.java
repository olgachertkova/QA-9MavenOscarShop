package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MainPageHelper extends PageBase{
    @FindBy(className = "btn-group") 
    WebElement basketButton;
    @FindBy (css = "button.btn.btn-default[type=\"submit\"]")
    WebElement submitLangButton ;
    @FindBy (name = "language")
    WebElement langButton;

    public MainPageHelper(WebDriver driver) {
        super(driver);
    }

    public String getViewBasketButtonText() {
        String text = basketButton.getText();
        return text;
    }

    public void clickOnGoButton() {
        submitLangButton.click();
    }

    public void selectLanguage(String lang) {
        Select langDropDown = new Select(langButton);
        langDropDown.selectByValue(lang);
    }

    public void waitUntilTextChanged() {
        waitUntilElementContainsText("Выполнить", submitLangButton, 20);
    }

    public void selectBooksCategory() {
        driver.findElement(By.className("dropdown-submenu")).click();
    }

    public void clickOnLoginOrRegisterLink() {
        WebElement loginLink = driver.findElement(By.id("login_link"));
        loginLink.click();
    }

    public boolean successMessageIsDisplayed() {
        return driver.findElement(By.className("alertinner")).isDisplayed();
    }

    public boolean successMessageContainsText(String message) {
        return driver.findElement(By.className("alertinner")).getText().contains(message);
    }
}

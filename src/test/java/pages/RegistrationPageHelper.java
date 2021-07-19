package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPageHelper extends PageBase{
    @FindBy (id = "id_registration-email")
    WebElement emailField;
    @FindBy (id = "id_registration-password1")
    WebElement passwordField;
    @FindBy (id = "id_registration-password2")
    WebElement confirmPasswordField;
    @FindBy (name = "registration_submit")
    WebElement registerButton;

    public RegistrationPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementVisible(driver.findElement(By.id("content_inner")), 30);
    }

    public void fillEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void fillConfirmPasswordField(String password) {
        confirmPasswordField.sendKeys(password);
    }

    public void clickOnRegisterButton() {
        registerButton.click();
    }
}

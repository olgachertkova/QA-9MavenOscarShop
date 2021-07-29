package pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Random;

public abstract class PageBase {
    protected WebDriver driver;
    int timeWaitElement = 30;

    public PageBase(WebDriver driver){
        this.driver = driver;
    }
    public void uploadFile(WebElement element, String filePath){
        waitUntilElementClickable(element, timeWaitElement);
        inputText(element, filePath);
    }

    public void click(WebElement element){
        waitUntilElementClickable(element, timeWaitElement);
        element.click();
    }

    public void checkIn(WebElement element){
        waitUntilElementVisible(element, timeWaitElement);
        if(!element.isSelected()){
            element.click();
        }
    }

    public void inputText(WebElement element, String text){
        waitUntilElementClickable(element, timeWaitElement);
        element.click();
        element.clear();
        element.sendKeys(text);
    }


    public void selectInDropDownByValue(WebElement element, String value){
        waitUntilElementClickable(element, timeWaitElement);
        Select languageSelect = new Select(element);
        languageSelect.selectByValue(value);
    }

    void waitUntilElementClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void waitUntilElementVisible (WebElement element, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void waitUntilElementInVisible (WebElement element, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void waitUntilAllElementsVisible (List<WebElement> elements, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String generateRandomEmail(int strLen){
        String randomStrings = "";
        Random random = new Random();
        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int i = 0; i < strLen; i++) {
            word[i] = (char)('a' + random.nextInt(26));
            randomStrings = randomStrings + word[i];
        }
        String randomEmail = randomStrings + "@gmail.com";
        return randomEmail;
    }

    void waitUntilElementContainsText (String text, WebElement element, int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElementsBecome(By locator, int quantity, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.numberOfElementsToBe(locator, quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takeSnapShot(WebDriver webdriver, Method method) throws Exception{

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Create new file name
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String curDate = formatter.format(date);
        String filePath = "screenshots/" + method.getName() + curDate +".png";

        //Move image file to new destination
        File DestFile=new File(filePath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }




}


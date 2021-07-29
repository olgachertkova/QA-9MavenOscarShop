package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static pages.PageBase.takeSnapShot;

public class TestBase {
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod(alwaysRun = true)
    public void openBrowser(Method m, Object[] p){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://selenium1py.pythonanywhere.com/en-gb/");
        logger.info("Start test: " + m );
        if(p.length != 0) {
            logger.info(" --> With data: " + Arrays.asList(p));
        }
    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result, Method method) throws Exception {
        if(result.isSuccess()){
            logger.info("Test result: PASSED");
        }else{
            logger.error("Test result: FAILED");
            takeSnapShot(driver, method);
        }
        logger.info("Stop test: " + result.getMethod().getMethodName());
        logger.info("======================================================");
        driver.quit();
    }

}

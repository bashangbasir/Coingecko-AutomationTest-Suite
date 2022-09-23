package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebApiUtils {

    WebDriverWait waitExplicit;
    Actions action;
    JavascriptExecutor javascriptExecutor;
    WebDriver driver;


    // COMMON METHOD
    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }
    public void forwardToNextPage(WebDriver driver) {
        driver.navigate().forward();
    }
    public void backToPreviousPage(WebDriver driver) {
        driver.navigate().back();
    }
    public void backToTopWindow(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void scrollToBottomPage(WebDriver driver) {
        this.javascriptExecutor = (JavascriptExecutor)driver;
        this.javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)", new Object[0]);
    }

    public WebElement waitForElementClickable(WebDriver driver,WebElement element) {
        this.waitExplicit = new WebDriverWait(driver, 30);
        return (WebElement)this.waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementVisible(WebDriver driver,WebElement element) {
        this.waitExplicit = new WebDriverWait(driver, 30);

        try {
            element = (WebElement)this.waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {

        }
        return element;
    }
}

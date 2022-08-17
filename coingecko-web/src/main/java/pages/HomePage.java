package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static constants.StringConstant.HomePageTitle;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    //WEBELEMENT IN HOMEPAGE SCREEN

    @FindBy(xpath = "")
    private WebElement coinGeckoLogo;

    @FindBy(xpath = "")
    private WebElement candyNotificationIcon;
    @FindBy(id = "cookie-notice")
    private WebElement cookieModal;
    @FindBy(xpath = "//*[@id='cookie-notice']//button[@data-action='click->cookie-note#accept']")
    private WebElement cookieModalOkayBtn;

    // ACTIONS IN HOMEPAGE

    public HomePage verifyHomePageTittle() {
        Assert.assertEquals(driver.getTitle(), HomePageTitle);
        return this;
    }

    public HomePage clearCookiesModal() {
        if (cookieModal.isDisplayed()) {
            cookieModalOkayBtn.click();
        }
        return this;
    }


}

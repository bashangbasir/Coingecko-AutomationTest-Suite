package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static constants.StringConstant.*;

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

    @FindBy(xpath = "//div/a[@class=\"mr-3 text-black tw-text-sm\" and contains(text(),\"Login\")]")
    private WebElement loginBtn;

    @FindBy(xpath = "//div/a[@class=\"text-black mr-1 tw-text-sm\" and contains(text(),\"Sign Up\")]")
    private WebElement signUpBtn;

    @FindBy(id = "signInEmail")
    private WebElement emailTextBox;

    @FindBy(id = "signInPassword")
    private WebElement passwordTextBox;

    @FindBy(id ="sign-in-button")
    private WebElement signInBtn;

    @FindBy(xpath = "//div[@class= \"unobtrusive-flash-message\"]")
    private WebElement signedInSignedOutMessage;

    @FindBy(css = " a.text-black > i.fas.fa-user")
    private WebElement humanIcon;

    @FindBy(xpath = " //div/a[@href=\"/account/sign_out?locale=en\" and @class=\"dropdown-item py-2 tw-text-sm\"]")
    private WebElement signOutBtn;

    // ACTIONS IN HOMEPAGE

    public HomePage verifyHomePageTittle() {
        Assert.assertEquals(driver.getTitle(), HOMEPAGE_TABTITTLE_TEXT);
        return this;
    }

    public HomePage clearCookiesModal() {
        if (cookieModal.isDisplayed()) {
            cookieModalOkayBtn.click();
        }
        return this;
    }

    public HomePage userLoginWithValidData(String email,String password){
        loginBtn.click();
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        signInBtn.click();
        return this;
    }

    public SignInPage userLoginWithInvalidData(String email, String password) {
        loginBtn.click();
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        signInBtn.click();
        return new SignInPage(driver);
    }

    public HomePage verifySignedInMessageSuccess(){
        Assert.assertEquals(signedInSignedOutMessage.getText(),SIGNED_IN_SUCCESS_TEXT);
        return this;
    }

    public HomePage verifySignedOutMessageSuccess(){
        Assert.assertEquals(signedInSignedOutMessage.getText(),SIGNED_OUT_SUCCESS_TEXT);
        return this;
    }

    public HomePage userSignOut(){
        humanIcon.click();
        signOutBtn.click();
        return this;
    }

    public HomePage refreshPage(){
        driver.navigate().refresh();
        return this;
    }



}

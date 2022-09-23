package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebApiUtils;

import java.util.List;

import static constants.StringConstant.*;

public class HomePage extends WebApiUtils {

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

    @FindBy(xpath = "//div/a[@class='mr-3 text-black tw-text-sm' and contains(text(),'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//div/a[@class='text-black mr-1 tw-text-sm' and contains(text(),'Sign Up')]")
    private WebElement signUpBtn;

    @FindBy(id = "signInEmail")
    private WebElement emailTextBox;

    @FindBy(id = "signInPassword")
    private WebElement passwordTextBox;

    @FindBy(id ="sign-in-button")
    private WebElement signInBtn;

    @FindBy(xpath = "//div[@class= 'unobtrusive-flash-message']")
    private WebElement signedInSignedOutMessage;

    @FindBy(css = " a.text-black > i.fas.fa-user")
    private WebElement humanIcon;

    @FindBy(xpath = " //div/a[@href='/account/sign_out?locale=en' and @class='dropdown-item py-2 tw-text-sm']")
    private WebElement signOutBtn;

    @FindBy(xpath = "//div[@data-action='click->desktop-search-popup#showSearchPopup']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@placeholder='Search token name or exchange']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(text(),'Trending Search')]")
    private WebElement trendingText;

    @FindBy(xpath = "//div[contains(text(),'Cryptocurrencies')]")
    private WebElement cryptoCurrenciesText;

    @FindBy(xpath = "//div[contains(text(),'NFT')]")
    private WebElement nftText;

    @FindBy(xpath = "//ul[@class='list-reset relevant-results']/li")
    private List<WebElement> listOfTrendingCoins;

    @FindBy(xpath = "//ul[@class='list-reset relevant-results']/li")
    private List<WebElement> searchedCoins;


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

    public HomePage clickSearchTextBox(){
        searchBox.click();
        return this;
    }

    public HomePage verifyTrendingCoinsExist(){
        Assert.assertTrue(trendingText.isDisplayed());
        int trendingCoinSize = listOfTrendingCoins.size();
        Assert.assertEquals(trendingCoinSize,7, "Trending coins actual " + trendingCoinSize + " but expected 7");
        return this;
    }

    public HomePage userSearch(String searchedString){
        searchInput.sendKeys(searchedString);
        return this;
    }

    public CoinPage selectFirstSearchResult(){
        searchedCoins.get(0).click();
        return new CoinPage(driver);
    }

    public HomePage refreshPage(){

        driver.navigate().refresh();
        return this;
    }



}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static constants.StringConstant.*;

public class SignInPage {

    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    // WEBELEMENT IN SIGN IN PAGE

    @FindBy(xpath = "//div[@class= \"unobtrusive-flash-message\"]")
    private WebElement unsuccessfulSignInMessage;

    // ACTION IN SIGN IN PAGE

    public SignInPage verifySignInErrorMessage() {
        Assert.assertEquals(unsuccessfulSignInMessage.getText(), SIGN_IN_INVALID_EMAIL_PASSWORD_TEXT);
        return this;
    }

    public SignInPage verifyUnconfirmedEmailMessage() {
        Assert.assertEquals(unsuccessfulSignInMessage.getText(), SIGN_IN_UNCONFIRM_EMAIL_TEXT);
        return this;
    }

    public SignInPage verifySignInPageTittle() {
        Assert.assertEquals(driver.getTitle(), SIGN_IN_TABTITTLE_TEXT);
        return this;
    }
}

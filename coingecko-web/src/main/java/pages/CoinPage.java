package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CoinPage {

    private WebDriver driver;

    public CoinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@aria-current='page']")
    private WebElement coinNameBreadcrumb;


    public CoinPage verifyCoinNameInBreadcrumb(String searchedString){
        Assert.assertTrue(coinNameBreadcrumb.getText().contains(searchedString + " Price"));
        return this;
    }



}

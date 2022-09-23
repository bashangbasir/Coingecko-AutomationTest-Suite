package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SearchBoxPage {

    private WebDriver driver;

    public SearchBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    // WEBELEMENT IN SearchBox SCREEN

    @FindBy(xpath = "//input[@type='search' and @placeholder='Search']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(text(),'Trending Search')]/li")
    private WebElement trendingText;

    @FindBy(xpath = "//ul[@class='list-reset relevant-results']")
    private List<WebElement> listOfTrendingCoins;

    @FindBy(xpath = "//a[@data-action='search#goBack']")
    private WebElement closeBtn;


    // ACTION IN SearchBox SCREEN

    public void verifyTrendingCoinsExist(){
        Assert.assertTrue(trendingText.isDisplayed());
        int trendingCoinSize = listOfTrendingCoins.size();
        Assert.assertEquals(trendingCoinSize,7, "Trending coins actual " + trendingCoinSize + " but expected 7");
    }





}

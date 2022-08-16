package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "")
    private WebElement coinGeckoLogo;

    @FindBy(xpath = "")
    private WebElement candyNotificationIcon;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getHomePageTabTittle(){
       return driver.getTitle();
    }



}

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import static constants.StringConstant.HomePageTitle;

public class LoadPageTest extends BaseTest {

    @Test
    public void loadCoingeckoHompage(){

        String actualTitle = homePage.getHomePageTabTittle();
        Assert.assertEquals(actualTitle, HomePageTitle);

    }

}

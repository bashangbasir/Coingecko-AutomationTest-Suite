package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoadPageTest extends BaseTest {

    @Test
    public void loadCoinGeckoHomepage(){

        homePage.
                clearCookiesModal().
                verifyHomePageTittle();

    }

}

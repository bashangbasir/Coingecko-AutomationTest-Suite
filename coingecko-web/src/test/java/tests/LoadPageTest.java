package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoadPageTest extends BaseTest {

    @Test
    public void LOAD_loadCoinGeckoHomepage() {

        homePage.
                clearCookiesModal().
                verifyHomePageTittle();

    }

}

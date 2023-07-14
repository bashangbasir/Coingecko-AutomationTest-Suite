package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoadPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void verifyHumanCheck() throws Exception {
        homePage.verifyHumanCheck();
    }

    @Test
    public void LOAD_loadCoinGeckoHomepage() {

        homePage.
                clearCookiesModal().
                verifyHomePageTittle();

    }

}

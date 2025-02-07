package tests;

import base.BaseTest;
import constants.Data;
import constants.UserRegistry;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCoinTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void verifyHumanCheck() throws Exception {
        homePage.verifyHumanCheck();
    }

    @Test
    public void SEARCH_trendingCoinDuringSearch() throws Exception {
        User validUser = new UserRegistry().getUser("valid-user");

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData(validUser.getEmail(), validUser.getPassword())
                .verifySignedInMessageSuccess()
                .clickSearchTextBox()
                .verifyTrendingCoinsExist();
    }

    @Test(dataProvider = "coins", dataProviderClass = Data.class)
    public void SEARCH_searchCoin(String coins) throws Exception {
        User validUser = new UserRegistry().getUser("valid-user");

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData(validUser.getEmail(), validUser.getPassword())
                .verifySignedInMessageSuccess()
                .clickSearchTextBox()
                .verifyTrendingCoinsExist()
                .userSearch(coins);

        //FIXME: need to improve this code. Use wait maybe instead of sleep.
        Thread.sleep(1000);

        homePage.selectFirstSearchResult()
                .verifyCoinNameInBreadcrumb(coins);
    }

}

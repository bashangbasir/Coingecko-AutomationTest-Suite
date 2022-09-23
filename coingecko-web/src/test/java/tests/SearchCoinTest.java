package tests;

import base.BaseTest;
import constants.Data;
import org.testng.annotations.Test;

public class SearchCoinTest extends BaseTest {

    @Test
    public void SEARCH_trendingCoinDuringSearch() throws Exception {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData("confirmemailtester@gmail.com","TestPassword@123")
                .verifySignedInMessageSuccess()
                .clickSearchTextBox()
                .verifyTrendingCoinsExist();
    }

    @Test(dataProvider = "coins", dataProviderClass = Data.class)
    public void SEARCH_searchCoin(String coins) throws Exception {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData("confirmemailtester@gmail.com","TestPassword@123")
                .verifySignedInMessageSuccess()
                .clickSearchTextBox()
                .verifyTrendingCoinsExist()
                .userSearch(coins);

        Thread.sleep(500); // need to improve this code. Use wait maybe instead of sleep.

        homePage.selectFirstSearchResult()
                .verifyCoinNameInBreadcrumb(coins);
    }

}

package tests;

import base.BaseTest;
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

    @Test
    public void SEARCH_searchBitcoin() {

        String searchedString = "bitcoin";

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData("confirmemailtester@gmail.com","TestPassword@123")
                .verifySignedInMessageSuccess()
                .search(searchedString)
                .verifyCoinNameInBreadcrumb(searchedString);
        //TODO - currently failing since search() method throwing error -> org.openqa.selenium.ElementNotInteractableException: element not interactable
    }

}

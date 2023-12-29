package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static constants.URIConstant.TRENDING_URI;
import static org.hamcrest.Matchers.hasSize;

public class TrendingTest extends BaseTest {

    @Test
    public void TRENDING_getTrendingSearchedCoin() {
        sendGet(TRENDING_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify the body got 7 array of coins
                .assertThat().body("coins", hasSize(15))
                .assertThat().body("nfts", hasSize(7))
                .assertThat().body("categories", hasSize(6));
    }


}

package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static constants.URIConstant.TRENDING_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TrendingTests extends BaseTest {

    @Test
    public void TRENDING_getTrendingSearchedCoin(){
        given()
                .when()
                .get(TRENDING_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify the body got 7 array of coins
                .assertThat().body("coins",hasSize(7));
    }


}

package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
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
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                //Verify the body got 7 array of coins
                .assertThat().body("coins",hasSize(7));
    }


}

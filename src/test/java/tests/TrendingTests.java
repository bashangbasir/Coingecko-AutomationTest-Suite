package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static enums.URIConstant.TRENDING_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TrendingTests extends BaseTest {

    @Test
    public void TRENDING_checkStatusCode_expectHttp200(){
        given()
                .when()
                    .get(TRENDING_URI)
                .then()
                    .assertThat().statusCode(200);
    }

    @Test
    public void TRENDING_checkContentType_expectApplicationJson(){
        given()
                .when()
                    .get(TRENDING_URI)
                .then()
                    .assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void TRENDING_checkTopCoins_expectSevenCoins(){
        given()
                .when()
                    .get(TRENDING_URI)
                .then()
                .assertThat().body("coins",hasSize(7));
    }


}

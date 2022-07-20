package tests;

import base.BaseTest;
import base.Data;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static constants.URIConstant.SIMPLE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class SimpleTests extends BaseTest {


    @Test(dataProvider = "simpleCoinVsCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_requiredQueryParamOnly(String coinId, String currency){
            given()
                    //Required query param
                    .queryParam("ids",coinId).queryParam("vs_currencies",currency)
                    .when()
                        .get(SIMPLE_URI)
                    .then()
                        .assertThat().statusCode(200)
                        .assertThat().contentType(ContentType.JSON)
                        //Verify body contains ids field
                        .assertThat().body("",hasKey(coinId))
                        //Verify ids contain currency field
                        .assertThat().body(coinId,hasKey(currency));
    }

    @Test(dataProvider = "simpleCoinVsCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_additionalQueryParam(String coinId, String currency){
        given()
                //Required query param
                .queryParam("ids",coinId).queryParam("vs_currencies",currency)
                //additional query param
                .queryParam("include_market_cap","true")
                .queryParam("include_24hr_vol", "true")
                .queryParam("include_24hr_change","true")
                .queryParam("include_last_updated_at","true")
                .when()
                .get(SIMPLE_URI)
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                //Verify body contains ids field
                .assertThat().body("",hasKey(coinId))
                //Verify ids contains all field
                .assertThat().body(coinId,hasKey(currency))
                .assertThat().body(coinId,hasKey(currency+"_market_cap"))
                .assertThat().body(coinId,hasKey(currency+"_24h_vol"))
                .assertThat().body(coinId,hasKey(currency+"_24h_change"))
                .assertThat().body(coinId,hasKey("last_updated_at"));
    }
}

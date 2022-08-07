package tests;

import base.BaseTest;
import base.Data;
import utils.CommonUtils;
import org.testng.annotations.Test;

import java.util.List;

import static constants.URIConstant.SIMPLE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class SimpleTests extends BaseTest {

    @Test(dataProvider = "dataSingleCoinVsSingleCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_singleCoinVsSingleCurrency_requiredQueryParamOnly(String coinId, String currency) {
        given()
                //Required query param
                .queryParam("ids", coinId).queryParam("vs_currencies", currency)
                .when()
                .get(SIMPLE_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coinId))
                //Verify ids contain currency field
                .assertThat().body(coinId, hasKey(currency));
    }

    @Test(dataProvider = "dataSingleCoinVsSingleCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_singleCoinVsSingleCurrency_additionalQueryParam(String coinId, String currency) {
        given()
                //Required query param
                .queryParam("ids", coinId).queryParam("vs_currencies", currency)
                //additional query param
                .queryParam("include_market_cap", "true")
                .queryParam("include_24hr_vol", "true")
                .queryParam("include_24hr_change", "true")
                .queryParam("include_last_updated_at", "true")
                .when()
                .get(SIMPLE_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coinId))
                //Verify ids contains all field
                .assertThat().body(coinId, hasKey(currency))
                .assertThat().body(coinId, hasKey(currency + "_market_cap"))
                .assertThat().body(coinId, hasKey(currency + "_24h_vol"))
                .assertThat().body(coinId, hasKey(currency + "_24h_change"))
                .assertThat().body(coinId, hasKey("last_updated_at"));
    }

    @Test(dataProvider = "dataSingleCoinVsMultipleCurrencies", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_singleCoinVsMultipleCurrencies_requiredQueryParamOnly(String coinId, List currencies) {

        String vsCurrencies = CommonUtils.parseListToStringCommaSeparated(currencies);

        given()
                //Required query param
                .queryParam("ids", coinId).queryParam("vs_currencies", vsCurrencies)
                .when()
                .get(SIMPLE_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coinId))
                //Verify each coin contains all currency field
                .assertThat().body(coinId, hasKey((String) currencies.get(0)))
                .assertThat().body(coinId, hasKey((String) currencies.get(1)))
                .assertThat().body(coinId, hasKey((String) currencies.get(2)));
    }

    @Test(dataProvider = "dataMultipleCoinVsSingleCurrencies", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_multipleCoinsVsSingleCurrencies_requiredQueryParamOnly(List coins, String currency) {

        String coinIds = CommonUtils.parseListToStringCommaSeparated(coins);

        given()
                //Required query param
                .queryParam("ids", coinIds)
                .queryParam("vs_currencies", currency)
                .when()
                .get(SIMPLE_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coins.get(0)))
                .assertThat().body("", hasKey(coins.get(1)))
                .assertThat().body("", hasKey(coins.get(2)))
                //Verify each coin contains all currency field
                .assertThat().body((String) coins.get(0), hasKey(currency))
                .assertThat().body((String) coins.get(1), hasKey(currency))
                .assertThat().body((String) coins.get(2), hasKey(currency));
    }

    @Test(dataProvider = "dataMultipleCoinsVsMultipleCurrencies", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_multipleCoinsVsMultipleCurrencies_requiredQueryParamOnly(List coins, List currencies) {

        String coinIds = CommonUtils.parseListToStringCommaSeparated(coins);
        String vsCurrencies = CommonUtils.parseListToStringCommaSeparated(currencies);

        given()
                //Required query param
                .queryParam("ids", coinIds)
                .queryParam("vs_currencies", vsCurrencies)
                .when()
                .get(SIMPLE_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coins.get(0)))
                .assertThat().body("", hasKey(coins.get(1)))
                .assertThat().body("", hasKey(coins.get(2)))
                //Verify each coin contains all currency field
                .assertThat().body((String) coins.get(0), hasKey(currencies.get(0)))
                .assertThat().body((String) coins.get(0), hasKey(currencies.get(1)))
                .assertThat().body((String) coins.get(0), hasKey(currencies.get(2)))
                .assertThat().body((String) coins.get(1), hasKey(currencies.get(0)))
                .assertThat().body((String) coins.get(1), hasKey(currencies.get(1)))
                .assertThat().body((String) coins.get(1), hasKey(currencies.get(2)))
                .assertThat().body((String) coins.get(2), hasKey(currencies.get(0)))
                .assertThat().body((String) coins.get(2), hasKey(currencies.get(1)))
                .assertThat().body((String) coins.get(2), hasKey(currencies.get(2)));
    }

}

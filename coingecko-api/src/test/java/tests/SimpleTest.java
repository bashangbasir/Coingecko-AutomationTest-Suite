package tests;

import base.BaseTest;
import constants.Data;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.URIConstant.SIMPLE_URI;
import static org.hamcrest.Matchers.hasKey;

public class SimpleTest extends BaseTest {

    @Test(dataProvider = "dataSingleCoinVsSingleCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_singleCoinVsSingleCurrency_requiredQueryParamOnly(String coinId, String currency) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", coinId);
        queryParams.put("vs_currencies", currency);

        sendGet(SIMPLE_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body contains ids field
                .assertThat().body("", hasKey(coinId))
                //Verify ids contain currency field
                .assertThat().body(coinId, hasKey(currency));
    }

    @Test(dataProvider = "dataSingleCoinVsSingleCurrency", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_singleCoinVsSingleCurrency_additionalQueryParam(String coinId, String currency) {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", coinId);
        queryParams.put("vs_currencies", currency);
        queryParams.put("include_market_cap", true);
        queryParams.put("include_24hr_vol", true);
        queryParams.put("include_24hr_change", true);
        queryParams.put("include_last_updated_at", true);

        sendGet(SIMPLE_URI, queryParams)
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

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", coinId);
        queryParams.put("vs_currencies", vsCurrencies);

        Response response = sendGet(SIMPLE_URI, queryParams);

        response.then()
                .spec(statusCode200responseSpec).assertThat().body("", hasKey(coinId))
                //Verify each coin contains all currency field
                .assertThat().body(coinId, hasKey((String) currencies.get(0)))
                .assertThat().body(coinId, hasKey((String) currencies.get(1)))
                .assertThat().body(coinId, hasKey((String) currencies.get(2)));
                //Verify body contains ids field
    }

    @Test(dataProvider = "dataMultipleCoinVsSingleCurrencies", dataProviderClass = Data.class)
    public void SIMPLE_getSimplePrice_multipleCoinsVsSingleCurrencies_requiredQueryParamOnly(List coins, String currency) {

        String coinIds = CommonUtils.parseListToStringCommaSeparated(coins);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", coinIds);
        queryParams.put("vs_currencies", currency);

        Response response = sendGet(SIMPLE_URI, queryParams);

        response.then()
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

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", coinIds);
        queryParams.put("vs_currencies", vsCurrencies);

        Response response = sendGet(SIMPLE_URI, queryParams);

        response.then()
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

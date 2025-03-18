package tests;

import base.BaseTest;

import io.restassured.response.Response;
import models.CategoriesResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static constants.MessagesConstant.CATEGORIES_INVALID_PARAM;
import static constants.URIConstant.CATEGORIES_URI;
import static constants.URIConstant.LIST_CATEGORIES_URI;
import static org.hamcrest.Matchers.*;


public class CategoriesTest extends BaseTest {

    @Test
    public void CATEGORIES_getListAllCategories() {
        sendGet(LIST_CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body has name and category_id field.
                .assertThat().body("[0]", hasKey("name"))
                .assertThat().body("[0]", hasKey("category_id"))
                .assertThat().body("[0].name", notNullValue())
                .assertThat().body("[0].category_id", notNullValue());
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataWithDefaultOrder() {
        Response response = sendGet(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        //market_cap_desc by default , so verify the market is in descending order
        float firstCategoryMarketCap = response.jsonPath().get("[0].market_cap");
        float secondCategoryMarketCap = response.jsonPath().get("[1].market_cap");
        float thirdCategoryMarketCap = response.jsonPath().get("[2].market_cap");
        Assert.assertTrue((firstCategoryMarketCap >= secondCategoryMarketCap) && (secondCategoryMarketCap >= thirdCategoryMarketCap), String.format("The order is not descending | {%f} >= {%f} >= {%f}", firstCategoryMarketCap, secondCategoryMarketCap, thirdCategoryMarketCap));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapAscending() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "market_cap_asc");

        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        //market_cap can be null, added condition if null then set 0
        ArrayList<Float> marketCapValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (response.jsonPath().get("[" + i + "].market_cap") == null) {
                marketCapValues.add(0.0F);
            } else {
                marketCapValues.add(response.jsonPath().get("[" + i + "].market_cap"));
            }
        }
        Assert.assertTrue((marketCapValues.get(0) <= marketCapValues.get(1)) && (marketCapValues.get(1) <= marketCapValues.get(2)) , String.format("The order is not ascending | {%f} >= {%f} >= {%f}", marketCapValues.get(0), marketCapValues.get(1), marketCapValues.get(2)));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapDescending() {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "market_cap_desc");

        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCap = response.jsonPath().get("[0].market_cap");
        float secondCategoryMarketCap = response.jsonPath().get("[1].market_cap");
        float thirdCategoryMarketCap = response.jsonPath().get("[2].market_cap");
        Assert.assertTrue((firstCategoryMarketCap >= secondCategoryMarketCap) && (secondCategoryMarketCap >= thirdCategoryMarketCap), String.format("The order is not descending | {%f} >= {%f} >= {%f}", firstCategoryMarketCap, secondCategoryMarketCap, thirdCategoryMarketCap));
    }

    // name_desc,
    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderNameAscending() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "name_asc");

        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        String firstName = response.jsonPath().get("[0].name");
        String secondName = response.jsonPath().get("[1].name");
        String thirdName = response.jsonPath().get("[2].name");
        // .compareToIgnoreCase() method will return negative value  if string1 comes before string2
        Assert.assertTrue((firstName.compareToIgnoreCase(secondName) < 0) && (secondName.compareToIgnoreCase(thirdName) < 0), String.format("The order is not ascending | {%s} >= {%s} >= {%s}", firstName, secondName, thirdName));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderNameDescending() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "name_desc");

        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        String firstName = response.jsonPath().get("[0].name");
        String secondName = response.jsonPath().get("[1].name");
        String thirdName = response.jsonPath().get("[2].name");
        // .compareToIgnoreCase() method will return positive value  if string1 comes before string2
        Assert.assertTrue((firstName.compareToIgnoreCase(secondName) > 0) && (secondName.compareToIgnoreCase(thirdName) > 0), String.format("The order is not descending | {%s} >= {%s} >= {%s}", firstName, secondName, thirdName));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapChange24hAscending() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "market_cap_change_24h_asc");

        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCapChange24h = response.jsonPath().get("[0].market_cap_change_24h");
        float secondCategoryMarketCapChange24h = response.jsonPath().get("[1].market_cap_change_24h");
        float thirdCategoryMarketCapChange24h = response.jsonPath().get("[2].market_cap_change_24h");
        Assert.assertTrue((firstCategoryMarketCapChange24h <= secondCategoryMarketCapChange24h) && (secondCategoryMarketCapChange24h <= thirdCategoryMarketCapChange24h), String.format("The order is not ascending | {%f} >= {%f} >= {%f}", firstCategoryMarketCapChange24h, secondCategoryMarketCapChange24h, thirdCategoryMarketCapChange24h));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapChange24hDescending() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "market_cap_change_24h_desc");


        Response response = sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        ArrayList<Float> marketCapValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (response.jsonPath().get("[" + i + "].market_cap_change_24h") == null) {
                marketCapValues.add(0.0F);
            } else {
                marketCapValues.add(response.jsonPath().get("[" + i + "].market_cap_change_24h"));
            }
        }

        Assert.assertTrue((marketCapValues.get(0) >= marketCapValues.get(1)) && (marketCapValues.get(1) >= marketCapValues.get(2)), String.format("The order is not descending | {%f} >= {%f} >= {%f}", marketCapValues.get(0), marketCapValues.get(1), marketCapValues.get(2)));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataWrongOrderValue() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", CommonUtils.getRandomCharacters(6));

        sendGet(CATEGORIES_URI, queryParams)
                .then()
                .spec(statusCode400responseSpec)
                .assertThat().body("error", equalTo(CATEGORIES_INVALID_PARAM));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesValidateDataProperties() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", "market_cap_change_24h_desc");

        Response res = sendGet(CATEGORIES_URI, queryParams)
                .then().spec(statusCode200responseSpec)
                .extract()
                .response();

        CategoriesResponseModel[] data =  CommonUtils.getJsonAsObject(res, CategoriesResponseModel[].class);

        Assert.assertNotNull(data[0].getId(), "Id should not null");
        Assert.assertNotNull(data[0].getName(), "name should not null");
        Assert.assertNotNull(data[0].getMarket_cap(), "market_cap should not null");
        Assert.assertNotNull(data[0].getMarket_cap_change_24h(), "market_cap_change_24h should not null");
        Assert.assertNotNull(data[0].getContent(), "contents should not null");
        Assert.assertEquals(data[0].getTop_3_coins().size(), 3);
        Assert.assertNotNull(data[0].getVolume_24h(), "volume_24h should not null");
        Assert.assertNotNull(data[0].getUpdated_at(), "update_at should not null");
    }
}

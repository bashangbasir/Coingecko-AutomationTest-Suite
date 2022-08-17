package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonUtils;

import static constants.URIConstant.CATEGORIES_URI;
import static constants.URIConstant.LIST_CATEGORIES_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class CategoriesTest extends BaseTest {

    @Test
    public void CATEGORIES_getListAllCategories() {
        given()
                .when()
                .get(LIST_CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body has name and catogery_id field.
                .assertThat().body("[0]", hasKey("name"))
                .assertThat().body("[0]", hasKey("category_id"));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataWithDefaultOrder() {
        Response response = given()
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        //market_cap_desc by default , so verify the market is in descending order
        float firstCategoryMarketCap = response.jsonPath().get("[0].market_cap");
        float secondCategoryMarketCap = response.jsonPath().get("[1].market_cap");
        float thirdCategoryMarketCap = response.jsonPath().get("[2].market_cap");
        Assert.assertTrue((firstCategoryMarketCap >= secondCategoryMarketCap) && (secondCategoryMarketCap >= thirdCategoryMarketCap));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapAscending() {
        Response response = given()
                .queryParam("order", "market_cap_asc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCap = response.jsonPath().get("[0].market_cap");
        float secondCategoryMarketCap = response.jsonPath().get("[1].market_cap");
        float thirdCategoryMarketCap = response.jsonPath().get("[2].market_cap");
        Assert.assertTrue((firstCategoryMarketCap <= secondCategoryMarketCap) && (secondCategoryMarketCap <= thirdCategoryMarketCap));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapDescending() {
        Response response = given()
                .queryParam("order", "market_cap_desc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCap = response.jsonPath().get("[0].market_cap");
        float secondCategoryMarketCap = response.jsonPath().get("[1].market_cap");
        float thirdCategoryMarketCap = response.jsonPath().get("[2].market_cap");
        Assert.assertTrue((firstCategoryMarketCap >= secondCategoryMarketCap) && (secondCategoryMarketCap >= thirdCategoryMarketCap));
    }

    // name_desc,
    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderNameAscending() {
        Response response = given()
                .queryParam("order", "name_asc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        String firstName = response.jsonPath().get("[0].name");
        String secondName = response.jsonPath().get("[1].name");
        String thirdName = response.jsonPath().get("[2].name");
        // .compareToIgnoreCase() method will return negative value  if string1 comes before string2
        Assert.assertTrue((firstName.compareToIgnoreCase(secondName) < 0) && (secondName.compareToIgnoreCase(thirdName) < 0));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderNameDescending() {
        Response response = given()
                .queryParam("order", "name_desc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        String firstName = response.jsonPath().get("[0].name");
        String secondName = response.jsonPath().get("[1].name");
        String thirdName = response.jsonPath().get("[2].name");
        // .compareToIgnoreCase() method will return positive value  if string1 comes before string2
        Assert.assertTrue((firstName.compareToIgnoreCase(secondName) > 0) && (secondName.compareToIgnoreCase(thirdName) > 0));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapChange24hAscending() {
        Response response = given()
                .queryParam("order", "market_cap_change_24h_asc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCapChange24h = response.jsonPath().get("[0].market_cap_change_24h");
        float secondCategoryMarketCapChange24h = response.jsonPath().get("[1].market_cap_change_24h");
        float thirdCategoryMarketCapChange24h = response.jsonPath().get("[2].market_cap_change_24h");
        Assert.assertTrue((firstCategoryMarketCapChange24h <= secondCategoryMarketCapChange24h) && (secondCategoryMarketCapChange24h <= thirdCategoryMarketCapChange24h));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataOrderMarketCapChange24hDescending() {
        Response response = given()
                .queryParam("order", "market_cap_change_24h_desc")
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                .extract().response();

        float firstCategoryMarketCapChange24h = response.jsonPath().get("[0].market_cap_change_24h");
        float secondCategoryMarketCapChange24h = response.jsonPath().get("[1].market_cap_change_24h");
        float thirdCategoryMarketCapChange24h = response.jsonPath().get("[2].market_cap_change_24h");
        Assert.assertTrue((firstCategoryMarketCapChange24h >= secondCategoryMarketCapChange24h) && (secondCategoryMarketCapChange24h >= thirdCategoryMarketCapChange24h));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDataWrongOrderValue() {

        given()
                .queryParam("order", CommonUtils.getRandomCharacters(6))
                .when()
                .get(CATEGORIES_URI)
                .then()
                .spec(statusCode400responseSpec)
                .assertThat().body("'error'", equalTo("invalid parameter"));

    }


}

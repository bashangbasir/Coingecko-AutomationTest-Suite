package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.URIConstant.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CategoriesTest extends BaseTest {

    @Test
    public void CATEGORIES_getListAllCategories(){
        given()
                .when()
                .get(LIST_CATEGORIES_URI)
                .then()
                .spec(statusCode200responseSpec)
                //Verify body has name and catogery_id field.
                .assertThat().body("[0]",hasKey("name"))
                .assertThat().body("[0]",hasKey("category_id"));
    }

    @Test
    public void CATEGORIES_getListAllCategoriesWithMarketDatawithDefaultOrder(){
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
        Assert.assertTrue((firstCategoryMarketCap > secondCategoryMarketCap) && (secondCategoryMarketCap >  thirdCategoryMarketCap));
    }


}

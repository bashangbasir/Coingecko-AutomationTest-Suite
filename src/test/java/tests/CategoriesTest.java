package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.hamcrest.collection.HasItemInArray;
import org.testng.annotations.Test;

import java.util.List;

import static enums.URIConstant.CATEGORIES_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CategoriesTest extends BaseTest {

    @Test
    public void CATEGORIES_getListOfCategories_checkStatusCode_expectHttp200(){
        given()
                .when()
                    .get(CATEGORIES_URI)
                .then()
                    .assertThat().statusCode(200);
    }

    @Test
    public void CATEGORIES_getListOfCategories_checkContentType_expectApplicationJson(){
        given()
                .when()
                    .get(CATEGORIES_URI)
                .then()
                    .assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void CATEGORIES_getListOfCategories_checkResponse_expectToHaveNameAndCategoryId(){
        given()
                .when()
                    .get(CATEGORIES_URI)
                .then()
                // Check the response contains name and category_id
                    .assertThat().body("[0]",hasKey("name"))
                    .assertThat().body("[0]",hasKey("category_id"));
    }

}

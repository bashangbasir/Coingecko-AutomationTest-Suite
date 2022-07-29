package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static constants.URIConstant.CATEGORIES_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CategoriesTest extends BaseTest {

    @Test
    public void CATEGORIES_getListOfCategories(){
        given()
                .when()
                .get(CATEGORIES_URI)
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                //Verify body has name and catogery_id field.
                .assertThat().body("[0]",hasKey("name"))
                .assertThat().body("[0]",hasKey("category_id"));
    }


}

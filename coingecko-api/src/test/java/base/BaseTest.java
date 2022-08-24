package base;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public static ResponseSpecification statusCode200responseSpec;
    public static ResponseSpecification statusCode400responseSpec;

    @BeforeSuite
    public void createStatusCode200ResponseSpecs() {
        statusCode200responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @BeforeSuite
    public void createStatusCode400ResponseSpecs() {
        statusCode400responseSpec = new ResponseSpecBuilder().
                expectStatusCode(400).
                expectContentType(ContentType.JSON).
                build();
    }

    @AfterSuite
    public void cleanup() {

    }

    public Response sendGet(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .spec(statusCode200responseSpec)
                .extract()
                .response();
    }

    public Response sendGet(String url, Map<String, Object> queryParams) {
        return given()
                .params(queryParams)
                .when()
                .get(url).then()
                .spec(statusCode200responseSpec)
                .extract().response();
    }

    public Response sendPost(String url, Object body) {
        return given().when().post(url, body);
    }
}

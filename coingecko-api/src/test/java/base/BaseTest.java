package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;

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
    @BeforeMethod(alwaysRun = true)
    public void pauseForFewSeconds() throws Exception {
        //since the api is public and got rate limit. Hack for 10-30 request/min
        Thread.sleep(15000);
    }

    @AfterSuite
    public void cleanup() {

    }

    public Response sendGet(String url) {
        return given()
                .when().spec(getRequestSpec().build())
                .get(url);
    }

    public Response sendGet(String url, Map<String, Object> queryParams) {
        return given()
                .when().spec(getRequestSpec().addQueryParams(queryParams).build())
                .get(url);
    }

    public Response sendPost(String url, Map<String, Object> body) {
        return given()
                .when().spec(getRequestSpec().build())
                .post(url, body);
    }

    public static RequestSpecBuilder getRequestSpec() {
        return new RequestSpecBuilder();
    }
}

package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static constants.URIConstant.PING_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PingTest extends BaseTest {

    @Test
    public void PING_checkStatusCode_expectHttp200(){
        given()
                .when()
                    .get(PING_URI)
                .then()
                    .assertThat().statusCode(200);
    }

    @Test
    public void PING_checkContentType_expectApplicationJson(){
        given()
                .when()
                    .get(PING_URI)
                .then()
                    .assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void PING_checkGeckoSaysInResponse(){
        given()
                .when()
                    .get(PING_URI)
                .then()
                    .assertThat().body("'gecko_says'",equalTo("(V3) To the Moon!"));
    }
}

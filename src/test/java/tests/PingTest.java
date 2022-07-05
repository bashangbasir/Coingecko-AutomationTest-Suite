package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static enums.URIConstant.PING_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PingTest extends BaseTest {

    @Test
    public void PING_expect_200_Status(){
        given()
                .when()
                    .get(PING_URI)
                .then()
                    .assertThat().statusCode(200);
    }

    @Test
    public void PING_expect_body_contains_gecko_says(){
        given()
                .when()
                    .get(PING_URI)
                .then()
                    .assertThat().body("'gecko_says'",equalTo("(V3) To the Moon!"));
    }
}

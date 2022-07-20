package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static constants.URIConstant.PING_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PingTest extends BaseTest {

    @Test
    public void PING_serverStatus(){
        given()
                .when()
                .get(PING_URI)
                .then()
                //Verify status code 200
                .assertThat().statusCode(200)
                //Verify content type - application/json
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("'gecko_says'",equalTo("(V3) To the Moon!"));
    }
}

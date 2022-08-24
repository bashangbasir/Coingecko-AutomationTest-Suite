package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static constants.URIConstant.PING_URI;
import static org.hamcrest.Matchers.equalTo;


public class PingTest extends BaseTest {

    @Test
    public void PING_serverStatus() {
        sendGet(PING_URI)
                .then()
                .spec(statusCode200responseSpec)
                .assertThat().body("'gecko_says'", equalTo("(V3) To the Moon!"));
    }
}

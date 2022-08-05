package base;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static ResponseSpecification statusCode200responseSpec;
    public static ResponseSpecification statusCode400responseSpec;
    @BeforeClass
    public static void createStatusCode200ResponseSpecs(){
        statusCode200responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @BeforeClass
    public static void createStatusCode400ResponseSpecs(){
        statusCode400responseSpec = new ResponseSpecBuilder().
                expectStatusCode(400).
                expectContentType(ContentType.JSON).
                build();
    }

    @AfterSuite
    public void cleanup(){

    }
}

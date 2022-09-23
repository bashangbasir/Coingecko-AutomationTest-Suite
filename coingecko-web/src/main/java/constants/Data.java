package constants;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider (name = "coins")
    public Object[][] coins(){
        return new Object[][] {{"bitcoin"}, {"xrp"}};
    }
}

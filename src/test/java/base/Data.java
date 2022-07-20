package base;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "simpleCoinVsCurrency")
    public Object[][] dataSimpleCoinVsCurrency(){
        return new Object[][]{
            {"bitcoin","usd"},
            {"ripple","myr"}
        };
    }
}

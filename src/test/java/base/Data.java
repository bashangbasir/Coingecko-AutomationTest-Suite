package base;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    @DataProvider(name = "dataSingleCoinVsSingleCurrency")
    public Object[][] dataSingleCoinVsSingleCurrency(){
        return new Object[][]{
            {"bitcoin","usd"},
            {"ripple","btc"}
        };
    }

    @DataProvider(name = "dataSingleCoinVsMultipleCurrencies")
    public Object[][] dataSingleCoinVsMultipleCurrencies(){
        return new Object[][]{
                {"bitcoin",Arrays.asList("myr", "btc", "usd")},
                {"ripple",Arrays.asList("myr", "btc", "usd")}
        };
    }

    @DataProvider(name = "dataMultipleCoinsVsMultipleCurrencies")
    public Object[][] dataMultipleCoinsVsMultipleCurrencies(){
        return new Object[][]{
                {Arrays.asList("bitcoin", "ripple", "etherium"),Arrays.asList("myr", "btc", "usd")},
                {Arrays.asList("bitcoin", "ripple", "etherium"),Arrays.asList("myr", "btc", "usd")}
        };
    }
}

package constants;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class Data {

    @DataProvider(name = "dataSingleCoinVsSingleCurrency")
    public Object[][] dataSingleCoinVsSingleCurrency() {
        return new Object[][]{
                {"bitcoin", "usd"},
                {"ripple", "btc"}
        };
    }

    @DataProvider(name = "dataSingleCoinVsMultipleCurrencies")
    public Object[][] dataSingleCoinVsMultipleCurrencies() {
        return new Object[][]{
                {"bitcoin", Arrays.asList("myr", "btc", "usd")},
                {"ripple", Arrays.asList("myr", "btc", "usd")}
        };
    }

    @DataProvider(name = "dataMultipleCoinVsSingleCurrencies")
    public Object[][] dataMultipleCoinVsSingleCurrencies() {
        return new Object[][]{
                {Arrays.asList("bitcoin", "ripple", "tether"), "usd"},
                {Arrays.asList("solana", "polkadot", "dogecoin"), "myr"}
        };
    }

    @DataProvider(name = "dataMultipleCoinsVsMultipleCurrencies")
    public Object[][] dataMultipleCoinsVsMultipleCurrencies() {
        return new Object[][]{
                {Arrays.asList("bitcoin", "ripple", "tether"), Arrays.asList("myr", "btc", "usd")},
                {Arrays.asList("dogecoin", "polkadot", "solana"), Arrays.asList("myr", "btc", "usd")}
        };
    }
}

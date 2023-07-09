package constants;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider (name = "coins")
    public Object[][] coins(){
        return new Object[][] {{"bitcoin"}, {"xrp"}};
    }

    @DataProvider(name = "invalidUser")
        public Object[][] invalidUser() {
        return new Object[][] {{"random.user.email@gmail.com","<password>"}};
    }

    @DataProvider(name = "validUser")
    public Object[][] validUser() {
        return new Object[][] {{"confirmemailtester@gmail.com","TestPassword@123"}};
    }

    @DataProvider(name = "unverifiedUser")
    public Object[][] unverifiedUser() {
        return new Object[][] {{"valid@email.com","Valid@123"}};
    }
}

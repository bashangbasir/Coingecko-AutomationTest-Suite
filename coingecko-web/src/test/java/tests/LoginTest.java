package tests;

import base.BaseTest;
import constants.Data;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "validUser", dataProviderClass = Data.class)
    public void LOGIN_userLoginWithValidDetails(String email, String password) {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData(email, password)
                .verifySignedInMessageSuccess()
                .userSignOut()
                .verifySignedOutMessageSuccess();
    }

    @Test(dataProvider = "invalidUser",  dataProviderClass = Data.class)
    public void LOGIN_userLoginWithInvalidDetails(String email, String password) {
        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithInvalidData(email, password)
                .verifySignInPageTittle()
                .verifySignInErrorMessage();
    }

    @Test(dataProvider = "unverifiedUser", dataProviderClass = Data.class)
    public void LOGIN_userLoginWithUnconfirmedEmails(String email, String password) {
        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithInvalidData(email, password)
                .verifySignInPageTittle()
                .verifyUnconfirmedEmailMessage();
    }
}

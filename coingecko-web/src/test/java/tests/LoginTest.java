package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userLoginWithValidDetails() {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLogin("<youremail@email.com>","<password>")
                .verifySignedInMessageSuccess()
                .userSignOut()
                .verifySignedOutMessageSuccess();
    }
}

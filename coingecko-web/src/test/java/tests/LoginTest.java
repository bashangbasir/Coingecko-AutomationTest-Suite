package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userLoginWithValidDetails() {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithValidData("confirmemailtester@gmail.com","TestPassword@123")
                .verifySignedInMessageSuccess()
                .userSignOut()
                .verifySignedOutMessageSuccess();
    }

    @Test
    public void userLoginWithInvalidDetails(){
        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithInvalidData("<youremail@email.com>","<password>")
                .verifySignInPageTittle()
                .verifySignInErrorMessage();
    }

    @Test
    public void userLoginWithUnconfirmedEmails() {

        homePage.clearCookiesModal()
                .verifyHomePageTittle()
                .userLoginWithInvalidData("valid@email.com","Valid@123")
                .verifySignInPageTittle()
                .verifyUnconfirmedEmailMessage();
    }
}
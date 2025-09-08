package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPageActions;

public class LoginStepDefinitions {

    private LoginPageActions loginPage;

    @Given("the user is on the login page")
    public void user_on_login_page() {
        loginPage = new LoginPageActions(Hooks.driver);
        loginPage.clickSignIn();
    }

    @When("the user enters {string} and {string}")
    public void enter_username_password(String username, String password) {
        loginPage.selectUsername(username);
        loginPage.selectPassword(password);
    }

    @And("clicks on the Sign In button")
    public void clicks_sign_in() {
        loginPage.clickLogin();
    }

    @Then("the user should be logged in successfully")
    public void verify_login() {
        Assert.assertTrue(loginPage.isOrdersLinkVisible(), "Login failed! 'Orders' link not found.");
    }
}

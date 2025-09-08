package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AppUtil;
import java.time.Duration;

public class LoginPageActions {

    private WebDriver driver;
    private AppUtil util;

    public LoginPageActions(WebDriver driver) {
        this.driver = driver;
        this.util = new AppUtil(driver, Duration.ofSeconds(10));
    }

    public void clickSignIn() {
        util.safeClick(AppLocators.signInButton);
    }

    public void selectUsername(String username) {
        util.selectReactDropdownStable(AppLocators.usernameInput, username);
    }

    public void selectPassword(String password) {
        util.selectReactDropdownStable(AppLocators.passwordInput, password);
    }

    public void clickLogin() {
        util.safeClick(AppLocators.loginButton);
    }

    public boolean isOrdersLinkVisible() {
        return util.waitForVisible(AppLocators.ordersLink).isDisplayed();
    }
}

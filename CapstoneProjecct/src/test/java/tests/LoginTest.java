package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AppActions;
import pages.LoginPageActions;
import pages.SortPage;
import utils.AppUtil;
import utils.WebDriverFactory;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private AppActions appActions;
    private LoginPageActions loginPage;
    private AppUtil util;

    @BeforeClass
    public void setupTest() {
        loginPage = new LoginPageActions(WebDriverFactory.getDriver());
        appActions = new AppActions(WebDriverFactory.getDriver());
        util = new AppUtil(WebDriverFactory.getDriver(), Duration.ofSeconds(10));
    }

    // ===== LOGIN TEST =====
    @Test(priority = 1)
    public void verifyLogin() {
        loginPage.clickSignIn();
        loginPage.selectUsername("demouser");
        loginPage.selectPassword("testingisfun99");
        util.pause(2);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isOrdersLinkVisible(), "Login failed! 'Orders' link not found.");
    }

    // ===== SORTING TESTS =====
    @Test(priority = 2, dependsOnMethods = "verifyLogin")
    public void verifySortLowestToHighest() {
        appActions.sortLowestToHighest();
        util.pause(2);
        String selected = appActions.getSelectedSortOption();
        Assert.assertTrue(selected.equalsIgnoreCase("Lowest to highest"),
                "Dropdown did not select 'Lowest to Highest'. Actual: " + selected);
    }

    @Test(priority = 3, dependsOnMethods = "verifyLogin")
    public void verifySortHighestToLowest() {
        appActions.sortHighestToLowest();
        util.pause(2);
        String selected = appActions.getSelectedSortOption();
        Assert.assertTrue(selected.equalsIgnoreCase("Highest to lowest"),
                "Dropdown did not select 'Highest to Lowest'. Actual: " + selected);
    }

    @Test(priority = 4, dependsOnMethods = "verifyLogin")
    public void verifyInvalidSortValue() {
        try {
            WebElement dropdown = util.waitForVisible(SortPage.sortDropdown);
            new org.openqa.selenium.support.ui.Select(dropdown).selectByValue("invalidvalue");
            Assert.fail("Sorting accepted invalid value!");
        } catch (Exception e) {
            Assert.assertTrue(true, "Exception caught as expected for invalid value.");
        }
    }

    @Test(priority = 5, dependsOnMethods = "verifyLogin")
    public void verifySortDropdownVisible() {
        WebElement dropdown = util.waitForVisible(SortPage.sortDropdown);
        Assert.assertTrue(dropdown.isDisplayed(), "Sort dropdown is not visible!");
    }

    // ===== PRODUCT SELECTION =====
    @Test(priority = 6, dependsOnMethods = "verifySortDropdownVisible")
    public void verifyAddGalaxyS20AndIncreaseQuantity() {
        util.pause(2);
        appActions.addSamsunggalaxyS20();  // Add Galaxy S20
        util.pause(2);

        appActions.increaseQuantity();
        util.pause(1);

        Assert.assertTrue(appActions.verifyProductQuantity("Galaxy S20", 2),
                "Quantity did not increase correctly for Galaxy S20!");
    }

    @Test(priority = 7, dependsOnMethods = "verifyAddGalaxyS20AndIncreaseQuantity")
    public void verifyCloseCartAndOrdersVisible() {
        appActions.closeCart();
        util.pause(1);
        Assert.assertTrue(loginPage.isOrdersLinkVisible(),
                "'Orders' link not visible after closing cart!");
    }

    @Test(priority = 8, dependsOnMethods = "verifyCloseCartAndOrdersVisible")
    public void verifyAddGalaxyS20Ultra() {
        util.pause(1);
        appActions.addiSamsunggalaxyS20ultra();  // Add Galaxy S20 Ultra
        util.pause(2);
        Assert.assertTrue(appActions.verifyProductQuantity("Galaxy S20 Ultra", 1),
                "Quantity did not show as 1 for Galaxy S20 Ultra!");
    }

    // ===== CHECKOUT =====
    @Test(priority = 9, dependsOnMethods = "verifyAddGalaxyS20Ultra")
    public void verifyCheckoutPageVisible() {
        appActions.clickCheckout();
        util.pause(2);
        Assert.assertTrue(appActions.isOrderSummaryVisible(), "Checkout failed! Order Summary not visible.");
    }

    @Test(priority = 10, dependsOnMethods = "verifyCheckoutPageVisible")
    public void verifyAddressEntryAndOrderPlacement() {
        appActions.enterAddress("Pavan", "Kalyan", "addagutta", "Hyderabad", "500085");
        util.pause(2);
        Assert.assertTrue(appActions.isOrderPlaced(), "Order not placed successfully!");
    }

    @Test(priority = 11, dependsOnMethods = "verifyAddressEntryAndOrderPlacement")
    public void verifyReceiptDownloadAndContinueShopping() {
        appActions.downloadReceipt();
        util.pause(2);
        appActions.continueShopping();
        Assert.assertTrue(appActions.isOrdersMenuVisible(), "Continue shopping did not return to orders!");
    }

    // ===== ORDER VERIFICATION =====
    @Test(priority = 12, dependsOnMethods = "verifyReceiptDownloadAndContinueShopping")
    public void verifyOrderExistsInOrdersMenu() {
        appActions.clickOrdersMenu();
        util.pause(2);
        Assert.assertTrue(appActions.isOrderVisibleInList(), "Order was not placed successfully!");
    }

    // ===== LOGOUT =====
    @Test(priority = 13, dependsOnMethods = "verifyOrderExistsInOrdersMenu")
    public void verifyLogoutFunctionality() {
        appActions.logout();
        util.pause(1);
        Assert.assertTrue(appActions.isSignInVisible(), "Logout failed! Sign In not visible.");
    }

    @AfterClass
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}

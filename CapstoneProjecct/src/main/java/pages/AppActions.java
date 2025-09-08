package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.AppUtil;

import java.time.Duration;

public class AppActions {

    private WebDriver driver;
    public AppUtil util;

    public AppActions(WebDriver driver) {
        this.driver = driver;
        this.util = new AppUtil(driver, Duration.ofSeconds(10));
    }

    // ===== Login Actions =====
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

    public boolean isOrdersVisible() {
        return util.waitForVisible(AppLocators.ordersLink).isDisplayed();
    }

    // ===== Sorting =====
    public void sortLowestToHighest() {
        WebElement dropdown = util.waitForVisible(SortPage.sortDropdown);
        new Select(dropdown).selectByValue("lowestprice");
    }

    public void sortHighestToLowest() {
        WebElement dropdown = util.waitForVisible(SortPage.sortDropdown);
        new Select(dropdown).selectByValue("highestprice");
    }

    public String getSelectedSortOption() {
        WebElement dropdown = util.waitForVisible(SortPage.sortDropdown);
        return new Select(dropdown).getFirstSelectedOption().getText().trim();
    }

    // ===== Product Actions =====
    public void selectSamsungleBrand() {
        try {
            util.safeClick(AppLocators.SamsungBrandCheckbox);
        } catch (Exception e) {
            new Actions(driver).moveToElement(util.waitForVisible(AppLocators.SamsungBrandCheckbox)).click().perform();
        }
    }

    public void addSamsunggalaxyS20() {
        util.safeClick(AppLocators.galaxyS20AddButton);
    }

    public void addiSamsunggalaxyS20ultra() {
        util.safeClick(AppLocators.galaxyS20UltraAddButton);
    }

    public void increaseQuantity() {
        util.waitForVisible(AppLocators.cartSidebar);
        util.safeClick(AppLocators.increaseQuantityButton);
    }

    public boolean verifyProductQuantity(String productName, int expectedQty) {
        try {
            WebElement productContainer = driver.findElement(
                By.xpath("//div[contains(@class,'shelf-item')]//p[@class='title' and text()='" + productName + "']/..")
            );
            WebElement qtyElement = productContainer.findElement(By.xpath(".//p[@class='desc']"));
            return qtyElement.getText().contains("Quantity: " + expectedQty);
        } catch (Exception e) {
            return false;
        }
    }

    // ===== Cart =====
    public void closeCart() {
        util.safeClick(AppLocators.cartSidebar); // or use cart close button if available
    }

    // ===== Checkout =====
    public void clickCheckout() {
        util.safeClick(AppLocators.checkoutButton);
    }

    public boolean isOrderSummaryVisible() {
        return util.waitForVisible(AppLocators.orderSummaryHeader).isDisplayed();
    }

    public void enterAddress(String fname, String lname, String address, String province, String postal) {
        util.sendKeys(AppLocators.firstNameInput, fname);
        util.sendKeys(AppLocators.lastNameInput, lname);
        util.sendKeys(AppLocators.addressLine1Input, address);
        util.sendKeys(AppLocators.provinceInput, province);
        util.sendKeys(AppLocators.postalCodeInput, postal);
        util.safeClick(AppLocators.submitAddressButton);
    }

    public boolean isOrderPlaced() {
        try {
            WebElement confirm = util.waitForVisible(AppLocators.orderConfirmationMessage);
            return confirm.isDisplayed() && confirm.getText().contains("successfully placed");
        } catch (Exception e) {
            return false;
        }
    }

    // ===== Receipt & Continue Shopping =====
    public void downloadReceipt() {
        util.safeClick(AppLocators.downloadReceiptButton);
    }

    public void continueShopping() {
        util.safeClick(AppLocators.continueShoppingButton);
    }

    public boolean isOrdersMenuVisible() {
        return util.waitForVisible(AppLocators.ordersMenu).isDisplayed();
    }

    public void clickOrdersMenu() {
        util.safeClick(AppLocators.ordersMenu);
    }

    public boolean isOrderVisibleInList() {
        try {
            return util.waitForVisible(By.xpath("//span[text()='Order placed']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== Logout =====
    public void logout() {
        try {
            util.safeClick(AppLocators.logoutButton);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                    util.waitForVisible(AppLocators.logoutButton));
        }
    }

    public boolean isSignInVisible() {
        return util.waitForVisible(AppLocators.signInVisibleText).isDisplayed();
    }
}

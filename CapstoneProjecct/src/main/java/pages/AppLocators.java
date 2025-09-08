package pages;

import org.openqa.selenium.By;

public class AppLocators {

    // ===== Login Page Locators =====
    public static By signInButton = By.id("signin");
    public static By usernameDropdownContainer = By.xpath("//div[contains(@class,'css-1hwfws3')][.//div[text()='Select Username']]");
    public static By passwordDropdownContainer = By.xpath("//div[contains(@class,'css-1hwfws3')][.//div[text()='Select Password']]");
    public static By loginButton = By.cssSelector("button[type='submit']");
    public static By ordersLink = By.id("orders");
    public static By usernameInput = By.id("react-select-2-input");
    public static By passwordInput = By.id("react-select-3-input");

    // ===== Sorting Locators =====
    public static By sortDropdown = By.tagName("select");
    public static By sortLowestToHighest = By.xpath("//option[@value='lowestprice']");
    public static By sortHighestToLowest = By.xpath("//option[@value='highestprice']");

    // ===== Product Selection =====
    public static By SamsungBrandCheckbox = By.xpath("//span[@class='checkmark' and text()='Samsung']");
    public static By galaxyS20AddButton = By.xpath("//div[@id='10']//div[@class='shelf-item__buy-btn']");
    public static By galaxyS20UltraAddButton = By.xpath("//div[@id='12']//div[@class='shelf-item__buy-btn']");
    public static By cartSidebar = By.cssSelector(".float-cart.float-cart--open");
    public static By increaseQuantityButton = By.cssSelector(".float-cart.float-cart--open .shelf-item__price .change-product-button:nth-of-type(2)");

    // ===== Checkout =====
    public static By checkoutButton = By.cssSelector("div.buy-btn");
    public static By orderSummaryHeader = By.xpath("//h3[text()='Order Summary']");

    // ===== Address Input =====
    public static By firstNameInput = By.id("firstNameInput");
    public static By lastNameInput = By.id("lastNameInput");
    public static By addressLine1Input = By.id("addressLine1Input");
    public static By provinceInput = By.id("provinceInput");
    public static By postalCodeInput = By.id("postCodeInput");
    public static By submitAddressButton = By.id("checkout-shipping-continue");
    public static By orderConfirmationMessage = By.id("confirmation-message");

    // ===== Receipt & Navigation =====
    public static By downloadReceiptButton = By.id("downloadpdf");
    public static By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");
    public static By ordersMenu = By.id("orders");

    // ===== Logout =====
    public static By logoutButton = By.xpath("//span[@id='signin' and text()='Logout']");
    public static By signInVisibleText = By.xpath("//span[@id='signin' and text()='Sign In']");
}

package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;
import utils.WebDriverFactory;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverFactory.initDriver();
        driver = WebDriverFactory.getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}

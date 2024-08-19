package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTest extends BaseTest {
    private final By successAuth = By.xpath(String.format(PARTICULAR_TEXT_XPATH,
            "Congratulations! You must have the proper credentials"));

    @Test
    public void basicAuthTest() {
        // Perform Basic Authentication
        String username = "admin";
        String password = "admin";
        String baseUrl = "http://the-internet.herokuapp.com/basic_auth";
        String authUrl = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";

        driver.get(authUrl);  // Navigate to the URL with authentication

        // Verify the success message
        Assert.assertTrue(driver.findElement(successAuth).isDisplayed(), "Message is not displayed");
    }
}

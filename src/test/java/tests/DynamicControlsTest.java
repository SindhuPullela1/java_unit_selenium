package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;

public class DynamicControlsTest extends BaseTest {
    private final By dynamicControls = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Controls"));
    private final By enableButton = By.xpath(String.format(PRECISE_TEXT_XPATH, "Enable"));
    private final By inputField = By.xpath("//input[@type='text']");
    private final By disableButton = By.xpath(String.format(PRECISE_TEXT_XPATH, "Disable"));

    @Test
    public void dynamicControlsTest() {
        // Step 1: Click "Dynamic Controls" link on the Main page
        driver.findElement(dynamicControls).click();

        // Step 2: Click "Enable" button and wait until input field is enabled
        driver.findElement(enableButton).click();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputField));

        // Assert the input field is enabled
        Assert.assertTrue(input.isEnabled(), "The input field is not enabled");

        // Step 3: Send random text to the input field
        String randomText = generateRandomText(10);
        input.sendKeys(randomText);

        // Assert the inputted text is correct
        Assert.assertEquals(input.getAttribute("value"), randomText, "The inputted text does not match the random text");
    }

    // Utility method to generate random text of a specified length
    private String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder text = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            text.append(characters.charAt(random.nextInt(characters.length())));
        }
        return text.toString();
    }
}

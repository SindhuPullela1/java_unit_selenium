package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest {
    private final By jsAlertLink = By.linkText("JavaScript Alerts");
    private final By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private final By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private final By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");
    private final By resultMessage = By.id("result");

    @Test
    public void jsAlertTest() {
        driver.findElement(jsAlertLink).click();
        driver.findElement(jsAlertButton).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        String expectedMessage = "You successfully clicked an alert";
        String actualMessage = driver.findElement(resultMessage).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message for JS Alert is not displayed");
    }

    @Test
    public void jsConfirmTest() {
        driver.findElement(jsAlertLink).click();
        driver.findElement(jsConfirmButton).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        String expectedMessage = "You clicked: Cancel";
        String actualMessage = driver.findElement(resultMessage).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message for JS Confirm is not displayed");
    }

    @Test
    public void jsPromptTest() {
        driver.findElement(jsAlertLink).click();
        driver.findElement(jsPromptButton).click();

        Alert alert = driver.switchTo().alert();
        String inputText = "Test Input";
        alert.sendKeys(inputText);
        alert.accept();

        String expectedMessage = "You entered: " + inputText;
        String actualMessage = driver.findElement(resultMessage).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message for JS Prompt is not displayed");
    }
}

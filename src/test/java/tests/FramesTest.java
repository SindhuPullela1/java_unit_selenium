package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class FramesTest extends BaseTest {
    private final String initText = "Your content goes here.";
    private final String textIsNotDisplayedMsg = "Text is not displayed";
    private final By frames = By.xpath(String.format(PRECISE_TEXT_XPATH, "Frames"));
    private final By iframe = By.xpath(String.format(PRECISE_TEXT_XPATH, "iFrame"));
    private final By edit = By.xpath(String.format(PRECISE_TEXT_XPATH, "Edit"));
    private final By undo = By.xpath(String.format(PRECISE_TEXT_XPATH, "Undo"));
    private final String randomText = UUID.randomUUID().toString();

    @BeforeClass
    public void setup() {
        // Initialize WebDriver if not already done in BaseTest
       // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void iFrameTest() {
        driver.get("https://the-internet.herokuapp.com/");

        // Navigate through the frames
        driver.findElement(frames).click();
        driver.findElement(iframe).click();

        // Switch to iframe
        WebDriver iframeDriver = driver.switchTo().frame("mce_0_ifr");

        // Input text into the text field
        WebElement textArea = iframeDriver.findElement(By.id("tinymce"));
        textArea.clear();
        textArea.sendKeys(initText + randomText);

        // Assert text is displayed
        Assert.assertTrue(textArea.getText().contains(initText + randomText),
                "Text is not displayed as expected");

        // Switch back to the main content
        driver.switchTo().defaultContent();

        // Click "Edit" and "Undo"
        driver.findElement(edit).click();
        driver.findElement(undo).click();

        // Switch back to iframe
        iframeDriver = driver.switchTo().frame("mce_0_ifr");

        // Assert text is not displayed
        Assert.assertFalse(textArea.getText().contains(initText + randomText),
                textIsNotDisplayedMsg);

        // Clean up
        driver.quit();
    }
}

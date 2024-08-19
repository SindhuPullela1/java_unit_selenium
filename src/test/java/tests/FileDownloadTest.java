package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileDownloadTest extends BaseTest {
    private final String fileName = "test.txt";
    private final By fileDownload = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Download"));
    private final By fileNameXpath = By.linkText(fileName); // Using linkText instead of XPath

    @Test
    public void fileDownloadTest() {
        driver.findElement(fileDownload).click();

        // Wait until the file link is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(fileNameXpath));

        Assert.assertTrue(fileLink.isDisplayed(), "File link is not displayed");

        fileLink.click();

        // Verify file is downloaded
        String downloadPath = System.getProperty("user.home") + "/Downloads/" + fileName;
        File downloadedFile = new File(downloadPath);
        wait.until(d -> downloadedFile.exists());

        Assert.assertTrue(downloadedFile.exists(), "File was not downloaded");

        // Clean up: delete the downloaded file
        if (downloadedFile.exists()) {
            boolean deleted = downloadedFile.delete();
            Assert.assertTrue(deleted, "Downloaded file was not deleted");
        }
    }
}

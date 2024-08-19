package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest extends BaseTest {
    private final String fileName = "file.json";
    private final String filePath = RELATIVE_RESOURCE_PATH + fileName;
    private final By fileUploadLink = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Upload"));
    private final By chooseFileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");

    @Test
    public void fileUploadTest() {
        // Step 1: Click "File Upload" link on the Main page
        driver.findElement(fileUploadLink).click();

        // Step 2: Upload the file and click the Upload button
        File fileToUpload = new File(filePath);
        WebElement fileInput = driver.findElement(chooseFileInput);
        fileInput.sendKeys(fileToUpload.getAbsolutePath());

        driver.findElement(uploadButton).click();

        // Step 3: Assert that the file name on the upload page is correct
        String uploadedFileName = driver.findElement(uploadedFiles).getText();
        Assert.assertEquals(uploadedFileName, fileName, "Uploaded file name is not as expected.");
    }
}

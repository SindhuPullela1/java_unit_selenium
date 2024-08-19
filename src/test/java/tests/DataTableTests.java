package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DataTableTests extends BaseTest {
    private final By sortableDataTables = By.xpath(String.format(PRECISE_TEXT_XPATH, "Sortable Data Tables"));
    private final By dueColumn = By.xpath("//table[@id='table1']//tr/td[4]");

    @Test
    public void dataTableTest() {
        driver.findElement(sortableDataTables).click();

        // Find all due values
        List<WebElement> dueValues = driver.findElements(dueColumn);
        double sum = 0.0;

        // Sum the due values
        for (WebElement value : dueValues) {
            String dueText = value.getText().replace("$", "");
            sum += Double.parseDouble(dueText);
        }
        // Assert the sum equals 251.0
        Assert.assertEquals(sum, 251.0, "The sum of the 'Due' values does not equal 251.0");
    }
}

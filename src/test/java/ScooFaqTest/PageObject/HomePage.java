package ScooFaqTest.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    private int numberOfItem;

    public WebElement getNumberOfAccordionItem (int numberOfItem) {

        List<WebElement> accordionItems = driver.findElements(By.xpath(".//div[@class = 'accordion']//div[@class = 'accordion__item]//"));
        return accordionItems.get(numberOfItem);
    }
}

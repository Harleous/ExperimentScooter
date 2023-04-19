package ScooFaqTest.Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.*;


@RunWith(Parameterized.class)
public class Chtest {


    WebDriver driver;

    private final int index;
    private final int panelNumber;
    private final String panelText;
    private final boolean result;


    public Chtest(int index, int panelNumber, String panelText, boolean result) {
        this.index = index;
        this.panelNumber = panelNumber;
        this.panelText = panelText;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] openFaqAnswer() {
        return new Object[][]{

                {0, 0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true},
                {1, 1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", true},
                {2, 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", true},
                {3, 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true},
                {4, 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                {5, 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", true},
                {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true},
                {7, 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", true},
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");



    }


    @Test
    public void mainPage_fAQ_checkText() {

        List<WebElement> elements = driver.findElements(By.xpath(".//div[@class = 'accordion__item']"));
        WebElement elementsItem = elements.get(index);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                elementsItem);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(elementsItem));
        elementsItem.click();



        var panelId =  "accordion__panel-" + panelNumber;
        WebElement panel = id(panelId).findElement(driver);
        assertEquals(result, panel.isDisplayed());
        assertEquals(panelText, panel.getText());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
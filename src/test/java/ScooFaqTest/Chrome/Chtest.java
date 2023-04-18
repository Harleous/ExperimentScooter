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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.*;

@RunWith(Parameterized.class)
public class Chtest {


    WebDriver driver;

    private final String index;
    private final int panelNumber;
    private final String panelText;
    private final boolean result;


    public Chtest(String index, int panelNumber, String panelText, boolean result) {
        this.index = index;
        this.panelNumber = panelNumber;
        this.panelText = panelText;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] openFaqAnswer() {
        return new Object[][]{

                {".//div[@class = 'accordion__item'][1]", 0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true},
                {".//div[@class = 'accordion__item'][2]", 1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", true},
                {".//div[@class = 'accordion__item'][3]", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", true},
                {".//div[@class = 'accordion__item'][4]", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true},
                {".//div[@class = 'accordion__item'][5]", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                {".//div[@class = 'accordion__item'][6]", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", true},
                {".//div[@class = 'accordion__item'][7]", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true},
                {".//div[@class = 'accordion__item'][8]", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", true},
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
        var panelId =  "accordion__panel-" + panelNumber;


        WebElement heading = xpath(index).findElement(driver);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                heading);
        WebElement accordionItem = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(index).findElement(driver)));

        accordionItem.click();


        WebElement panel = id(panelId).findElement(driver);
        assertEquals(result, panel.isDisplayed());
        assertEquals(panelText, panel.getText());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
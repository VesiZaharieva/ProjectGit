package selenium.base;
import dev.selenium.driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public WebDriverWait waitToBeStale;


    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        waitToBeStale  =new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementTobeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeStale(WebElement element) {
        waitToBeStale.until(ExpectedConditions.stalenessOf(element));
    }
    public void waitForElementColor(WebElement element, String color){
        wait.until(ExpectedConditions.attributeToBe(element, "color",color));
    }

    public void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollIntoViewWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }



}


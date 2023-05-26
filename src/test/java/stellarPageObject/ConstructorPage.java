package stellarPageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;

    public boolean isTabSelected(String burgerTab) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(burgerTab)));
        driver.findElement(By.xpath(burgerTab)).click();

        String getAttribute = driver.findElement(By.xpath(burgerTab)).getAttribute("class");

        return (getAttribute.contains("current"));
    }

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
}

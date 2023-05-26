package stellarPageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;

    public void clickOnTab(String burgerTab) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(burgerTab)));
        driver.findElement(By.xpath(burgerTab)).click();
    }

    public boolean isTabSelected(String currentTab) {
        String getAttribute = driver.findElement(By.xpath(currentTab)).getAttribute("class");

        return (getAttribute.contains("current"));
    }

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
}

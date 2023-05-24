package stellarPageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;

    public boolean isTabSelected(String burgerTab) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(burgerTab)));
        driver.findElement(By.xpath(burgerTab)).click();
//        new WebDriverWait(driver, Duration.ofSeconds(3))
//                .until(ExpectedConditions.attributeToBe(By.xpath(burgerTab), "class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"));
        String getAttribute = driver.findElement(By.xpath(burgerTab)).getAttribute("class");

        return (getAttribute.contains("current"));
    }

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
}

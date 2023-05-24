package stellarPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;


    public String getTextOfTab(String tabText, String burgerTab) {
        driver.findElement(By.xpath(burgerTab)).click();
                new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.attributeToBe(By.xpath(burgerTab), "class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"));

        return driver.findElement(By.xpath(tabText)).getText();
    }

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
}

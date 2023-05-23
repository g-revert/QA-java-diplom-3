package stellarPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPage {
    private WebDriver driver;
    private static final By CONSTRUCTOR_TEXT = By.xpath(".//main/section/h1");


    //метод для ожидания загрузки страницы
    public void waitForPageLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //метод заходит в конструктор
    public void enterConstructor(String constructorButton) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(constructorButton)));
        driver.findElement(By.xpath(constructorButton)).click();
    }
    //метод берет текст из страницы с конструктором
    public String getLoginPageText(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(CONSTRUCTOR_TEXT));
        return driver.findElement(CONSTRUCTOR_TEXT).getText();
    }

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }
}

package stellarPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private static final By NAME_FIELD = By.xpath("(.//div/input)[1]");
    private static final By PASSWORD_FIELD = By.xpath("(.//div/input)[2]");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");

    //метод для ожидания загрузки страницы
    public void waitForPageLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    //метод заходит на страницу логина
    public void enterLoginPage(String button) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(button)));
        driver.findElement(By.xpath(button)).click();
    }

    //метод заполняет поле Имя
    public void setLoginName(String loginName) {
        driver.findElement(NAME_FIELD).sendKeys(loginName);
    }

    //метод заполняет поле Пароль
    public void setLoginPassword(String loginPassword) {
        driver.findElement(PASSWORD_FIELD).sendKeys(loginPassword);
    }
    //метод кликает на кнопку Зарегистрироваться
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }
    public void loginUserPage(String loginName, String loginPassword){
        setLoginName(loginName);
        setLoginPassword(loginPassword);
        clickLoginButton();
    }
    //метод проверяет что мы вошли по тому, что видна кнопка Оформить заказ
    public String createOrderButton() {
        return driver.findElement(By.xpath(".//main/section[2]/div/button")).getText();
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
}

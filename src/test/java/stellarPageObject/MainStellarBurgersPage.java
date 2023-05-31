package stellarPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainStellarBurgersPage {
    private WebDriver driver;
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//div/header/nav/a[@href='/account']");
    private static final By AUTHORIZED_USER_PAGE_TEXT = By.xpath(".//main/div/nav/p");
    private static final By LOGOUT_FROM_ACCOUNT_BUTTON = By.xpath(".//div/nav/ul/li/button[text()='Выход']");
    private static final By LOGIN_PAGE_TEXT_AFTER_LOGOUT = By.xpath(".//main/div/h2[text()]");

    //метод кликает по кнопке Личный кабинет
    public void checkPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
    }

    //метод получает текст страницы авторизованного юзера
    public String getAuthorizedAccountPageText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(AUTHORIZED_USER_PAGE_TEXT));
       return driver.findElement(AUTHORIZED_USER_PAGE_TEXT).getText();
    }

    //метод производит выход из аккаунта
    public void logoutFromAccount(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(LOGOUT_FROM_ACCOUNT_BUTTON));
        driver.findElement(LOGOUT_FROM_ACCOUNT_BUTTON).click();
    }
    public String getLoginPageTextAfterLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PAGE_TEXT_AFTER_LOGOUT));
        return driver.findElement(LOGIN_PAGE_TEXT_AFTER_LOGOUT).getText();
    }

    public MainStellarBurgersPage(WebDriver driver) {
        this.driver = driver;
    }
}

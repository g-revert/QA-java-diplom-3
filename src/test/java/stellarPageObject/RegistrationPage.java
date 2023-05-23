package stellarPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    //Локаторы элементов на странице регистрации
    private static final By NAME_FIELD = By.xpath("(.//div/input)[1]");
    private static final By EMAIL_FIELD = By.xpath("(.//div/input)[2]");
    private static final By PASSWORD_FIELD = By.xpath("(.//div/input)[3]");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By SIGN_UP_BUTTON = By.xpath(".//div/p/a[@href='/register']");
    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By SUCCESSFUL_REGISTRATION_PAGE = By.xpath(".//div/h2[text()='Вход']");
    private static final By INCORRECT_PASSWORD_MESSAGE= By.xpath("(.//div/p)[1]");

    //метод для ожидания загрузки страницы
    public void waitForPageLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //метод кликает на кнопку войти и зарегистрироваться
    public void loginToSignUp() {
        driver.findElement(LOGIN_BUTTON).click();
        driver.findElement(SIGN_UP_BUTTON).click();
    }
    //метод заполняет поле Имя
    public void setRegisterName(String registerName) {
        driver.findElement(NAME_FIELD).sendKeys(registerName);
    }
    //метод заполняет поле Почта
    public void setRegisterEmail(String registerEmail) {
        driver.findElement(EMAIL_FIELD).sendKeys(registerEmail);
    }
    //метод заполняет поле Пароль
    public void setRegisterPassword(String registerPassword) {
        driver.findElement(PASSWORD_FIELD).sendKeys(registerPassword);
    }
    //метод кликает на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }
    //метод получает сообщение ошибки при вводе некорректного пароля
    public String getIncorrectPasswordMessage(){
    return driver.findElement(INCORRECT_PASSWORD_MESSAGE).getText();
    }
    public String getLoginPageText(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(SUCCESSFUL_REGISTRATION_PAGE));
        return driver.findElement(SUCCESSFUL_REGISTRATION_PAGE).getText();
    }

    public void registerUserPage(String registerName, String registerEmail, String registerPassword){
        loginToSignUp();
        setRegisterName(registerName);
        setRegisterEmail(registerEmail);
        setRegisterPassword(registerPassword);
        clickRegisterButton();
    }



    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
}

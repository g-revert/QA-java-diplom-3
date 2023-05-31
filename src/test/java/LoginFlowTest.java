import client.UserSteps;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import stellarPageObject.LoginPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginFlowTest {
    private UserSteps userSteps;
    private String token;
    private String[] buttons;

    public LoginFlowTest(String[] buttons) {
        this.buttons = buttons;
    }

    private WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void setUp() {
        driver = createWebDriver();
        userSteps = new UserSteps();
    }

    @Parameterized.Parameters
    public static Object[][] getButtons() {
        return new Object[][] {
                {new String[]{".//div/button[text()='Войти в аккаунт']"}},
                {new String[]{".//a/p[text()='Личный Кабинет']"}},
                {new String[]{".//a/p[text()='Личный Кабинет']", ".//p/a[text()='Зарегистрироваться']", ".//p/a[text()='Войти']"}},
                {new String[]{".//a/p[text()='Личный Кабинет']", ".//p/a[text()='Восстановить пароль']", ".//p/a[text()='Войти']"}}
        };
    }

    @Test
    public void logIntoAccountTest() {
        User user = new User("jwervjwbebvjwjevw@yandex.ru", "weljweklnvvljwv", "evljnkev");
        ValidatableResponse createResponse = userSteps.create(user);
        driver.get(PAGE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoading();
        for(String button : buttons){
            loginPage.enterLoginPage(button);
        }
        loginPage.loginUserPage("jwervjwbebvjwjevw@yandex.ru", "weljweklnvvljwv");
        token = userSteps.login(UserCredentials.from(user)).extract().path("accessToken");

        String successMessage = createResponse.extract().path("success").toString();
        String expectedButtonForAuthorizedUser = "Оформить заказ";
        String actualButtonForAuthorizedUser = loginPage.createOrderButton();
        assertEquals(successMessage, "true");
        assertEquals(expectedButtonForAuthorizedUser, actualButtonForAuthorizedUser);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

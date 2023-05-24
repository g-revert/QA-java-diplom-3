import client.UserSteps;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import model.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import stellarPageObject.RegistrationPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

public class RegistrationPageTest {
    private UserSteps userSteps;
    private String token;
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

    @Test
    public void successfulRegistrationTest() {
        UserCredentials userCredentials = new UserCredentials("ererdfd@yandex.ru", "lwpfjswe");
        driver.get(PAGE_URL);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForPageLoading();
        registrationPage.registerUserPage("tetetettet","ererdfd@yandex.ru", "lwpfjswe");
        ValidatableResponse loginResponse = userSteps.login(userCredentials);
        token = loginResponse.extract().path("accessToken");

        String expectedText = "Вход";
        String actualText = registrationPage.getLoginPageText();
        assertEquals(expectedText, actualText);
    }
    @Test
    public void incorrectPasswordMessageTest() {
        driver.get(PAGE_URL);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForPageLoading();
        registrationPage.registerUserPage("7675h3kh@yandex.ru","erhvbkb@yandex.ru", "12345");
        token = "null";
        String expectedMessage = "Некорректный пароль";
        String actualMessage = registrationPage.getIncorrectPasswordMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

import client.UserSteps;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import stellarPageObject.ConstructorPage;
import stellarPageObject.LoginPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

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
    public void constructorTabsTest() {
        User user = new User("knnwcowe33weoc@yandex.ru", "pwieeciweknewn", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("knnwcowe33weoc@yandex.ru", "pwieeciweknewn");
        token = createResponse.extract().path("accessToken");

        //кликаем по очереди на каждую вкладку, проверяя атрибуты классов элементов
        boolean isSauceElementActual = constructorPage.isTabSelected("(.//section/div/div)[2]");
        boolean isFillingElementActual = constructorPage.isTabSelected("(.//section/div/div)[3]");
        boolean isBunsElementActual = constructorPage.isTabSelected("(.//section/div/div)[1]");

        assertTrue(isSauceElementActual);
        assertTrue(isFillingElementActual);
        assertTrue(isBunsElementActual);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

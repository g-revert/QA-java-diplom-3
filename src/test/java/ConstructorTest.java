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
import org.openqa.selenium.WebDriver;
import stellarPageObject.ConstructorPage;
import stellarPageObject.LoginPage;

import static driver.WebDriverCreator.createWebDriver;
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
    public void bunTabTest() {
        User user = new User("wjkebbjkwbjkwevwevwev@yandex.ru", "pwieeciweknewn", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("wjkebbjkwbjkwevwevwev@yandex.ru", "pwieeciweknewn");
        token = createResponse.extract().path("accessToken");
        loginPage.waitForPageLoading();

        boolean isBunsElementActual = constructorPage.isTabSelected("(.//section/div/div)[1]");
        assertTrue(isBunsElementActual);
    }

    @Test
    public void sauceTabTest() {
        User user = new User("kbvbwevjkbewjkbvwebjk@yandex.ru", "pwieeciweknewn", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("kbvbwevjkbewjkbvwebjk@yandex.ru", "pwieeciweknewn");
        token = createResponse.extract().path("accessToken");

        constructorPage.clickOnTab(".//div/div/span[text()='Соусы']");
        boolean isSauceElementActual = constructorPage.isTabSelected("(.//section/div/div)[2]");
        assertTrue(isSauceElementActual);
    }

    @Test
    public void fillingTabTest() {
        User user = new User("woiehvoiwhevoiwevwev@yandex.ru", "pwieeciweknewn", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("woiehvoiwhevoiwevwev@yandex.ru", "pwieeciweknewn");
        token = createResponse.extract().path("accessToken");

        constructorPage.clickOnTab(".//div/div/span[text()='Начинки']");
        boolean isFillingElementActual = constructorPage.isTabSelected("(.//section/div/div)[3]");
        assertTrue(isFillingElementActual);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

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
import org.openqa.selenium.chrome.ChromeDriver;
import stellarPageObject.ConstructorPage;
import stellarPageObject.LoginPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

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
        User user = new User("ienrvioewcenrve@yandex.ru", "pwieciweknewn", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("ienrvioewcenrve@yandex.ru", "pwieciweknewn");
        token = createResponse.extract().path("accessToken");

        //кликаем по очереди на каждую вкладку, проверяя вкладки
        String sauceElementActual = constructorPage.getTextOfTab(".//section/div[2]/h2[2]", "(.//section/div/div)[2]");
        String fillingElementActual = constructorPage.getTextOfTab(".//section/div[2]/h2[3]", "(.//section/div/div)[3]");
        String bunsElementActual = constructorPage.getTextOfTab(".//section/div[2]/h2[1]", "(.//section/div/div)[2]");

        assertEquals("Соусы", sauceElementActual);
        assertEquals("Начинки", fillingElementActual);
        assertEquals("Булки", bunsElementActual);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

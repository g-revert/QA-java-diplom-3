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
import stellarPageObject.LoginPage;
import stellarPageObject.MainStellarBurgersPage;
import stellarPageObject.UserPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ReachConstructorTest {
    private String constructorButton;

    public ReachConstructorTest(String constructorButton) {
        this.constructorButton = constructorButton;
    }

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

    @Parameterized.Parameters
    public static Object[][] getConstructorButtons() {
        return new Object[][] {
               {".//li/a/p[text()='Конструктор']"},
               {".//div/a[@href='/']"}
        };
    }

    @Test
    public void switchingToConstructorTest() {
        User user = new User("oiwhvowevberv@yandex.ru", "weljwerklnvvljwv", "evljnkev");
        ValidatableResponse createResponse = userSteps.create(user);
        UserPage userPage = new UserPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainStellarBurgersPage mainStellarBurgersPage = new MainStellarBurgersPage(driver);
        driver.get(PAGE_URL);
        userPage.waitForPageLoading();
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("oiwhvowevberv@yandex.ru", "weljwerklnvvljwv");
        mainStellarBurgersPage.checkPersonalAccount();
        token = createResponse.extract().path("accessToken");
        userPage.enterConstructor(constructorButton);

        String expectedConstructorText = "Соберите бургер";
        String actualConstructorText = userPage.getLoginPageText();
        assertEquals(expectedConstructorText, actualConstructorText);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

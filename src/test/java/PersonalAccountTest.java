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
import stellarPageObject.LoginPage;
import stellarPageObject.MainStellarBurgersPage;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

public class PersonalAccountTest {
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
    public void SwitchToPersonalAccountPageTest() {
        User user = new User("opopjksdkjsdds@yandex.ru", "wewefxccbcb", "evljnkerv");
        ValidatableResponse createResponse = userSteps.create(user);
        LoginPage loginPage = new LoginPage(driver);
        MainStellarBurgersPage mainStellarBurgersPage = new MainStellarBurgersPage(driver);
        driver.get(PAGE_URL);
        loginPage.enterLoginPage(".//div/button[text()='Войти в аккаунт']");
        loginPage.loginUserPage("opopjksdkjsdds@yandex.ru", "wewefxccbcb");
        mainStellarBurgersPage.checkPersonalAccount();
        token = createResponse.extract().path("accessToken");

        String expectedPersonalAccountText = "В этом разделе вы можете изменить свои персональные данные";
        String actualPersonalAccountText = mainStellarBurgersPage.getAuthorizedAccountPageText();
        assertEquals(expectedPersonalAccountText, actualPersonalAccountText);
    }

    @After
    public void tearDownAndClearData() {
        driver.quit();
        userSteps.delete(token);
    }
}

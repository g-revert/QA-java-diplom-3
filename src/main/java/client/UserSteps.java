package client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserSteps extends UserRestClient {
    private static final String USER_URI = BASE_URI + "auth/";

    @Step("Create user {createUser}")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseReqSpec()) //пишем ".spec" вместо ".header" и обращаемся все время к getBaseReqSpec()
                .body(user)
                .when()
                .post(USER_URI + "register/")
                .then();
    }

    @Step("Login user {loginUser}")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseReqSpec())
                .body(userCredentials)
                .when()
                .post(USER_URI + "login/")
                .then();
    }

    @Step("Delete user {deleteUser}")
    public ValidatableResponse delete(String token ) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization", token)
                .when()//при удалении в тело запроса ничего не передается (не пишем body)
                .delete(USER_URI+"user/")
                .then();
    }
}

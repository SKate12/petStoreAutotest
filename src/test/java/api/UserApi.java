package api;

import com.google.gson.Gson;
import config.CommonParams;
import dto.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private final static String uri = CommonParams.getUri() + "/user";

    @Step("POST Add new user")
    public static Response addNewUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(json)
                .when()
                .post(uri);
    }

    @Step("GET User by username")
    public static Response getUserByName(String username) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .get(uri + username);
    }

    @Step("DELETE User by username")
    public static Response deleteUserByUserName(String username) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .delete(uri + username);
    }
}
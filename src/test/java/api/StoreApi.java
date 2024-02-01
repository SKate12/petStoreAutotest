package api;

import com.google.gson.Gson;
import config.CommonParams;
import dto.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreApi {
    private final static String uri = CommonParams.getUri() + "/store";

    @Step("POST New order")
    public static Response addNewOrder(Order order) {
        Gson gson = new Gson();
        String json = gson.toJson(order);
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(json)
                .when()
                .post(uri + "/order/");
    }

    @Step("GET Order by Id")
    public static Response getOrderById(String orderId) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .get(uri + "/order/" + orderId);
    }

    @Step("DELETE Order by Id")
    public static Response deleteUserByUserName(String id) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .delete(uri + "/" + id);
    }
}
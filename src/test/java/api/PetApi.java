package api;

import com.google.gson.Gson;
import config.CommonParams;
import dto.Pet;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetApi {
    private final static String uri = CommonParams.getUri() + "/pet";

    @Step("POST Add new pet")
    public static Response addNewPet(Pet pet) {
        Gson gson = new Gson();
        String json = gson.toJson(pet);
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(json)
                .when()
                .post(uri);
    }

    @Step("GET Pet by ID")
    public static Response getPetById(String petId) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .get(uri + "/" + petId);
    }

    @Step("DELETE Pet by ID")
    public static Response deletePetById(String petId) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .when()
                .delete(uri + "/" + petId);
    }
}
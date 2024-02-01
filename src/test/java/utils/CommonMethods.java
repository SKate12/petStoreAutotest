package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class CommonMethods {
    @Step("Check that the response code status matches the expected")
    public static void checkResponseStatusCode(Response response, Integer expected) {
        Assert.assertEquals(response.getStatusCode(), expected,
                "Actual status code: " + response.getStatusCode()
                        + " does not match what is expected: " + expected + ".");
    }

    @Step("Check that the response found by jsonPath matches the expected text")
    public static void checkResponseByJsonPathEqualsExpectedText(Response response, String jsonPath, String expected) {
        Assert.assertEquals(expected, response.getBody().jsonPath().get(jsonPath).toString(),
                "Actual text is :" + response.getBody().jsonPath().get(jsonPath).toString()
                        + " does not match what is expected: " + expected + ".");
    }

    @Step("Check that the response found by jsonPath is not empty")
    public static void checkResponseByJsonPathNotEmpty(Response response, String jsonPath) {
        Assert.assertFalse(response.getBody().jsonPath().get(jsonPath).toString().isEmpty(),
                "The response found by jsonPath must not be empty");
    }

    @Step("Get text from response using JsonPath")
    public static String getTextFromResponseByJsonPath(Response response, String jsonPath) {
        return response.jsonPath().get(jsonPath).toString();
    }

}

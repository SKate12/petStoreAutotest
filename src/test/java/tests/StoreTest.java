package tests;

import api.StoreApi;
import dto.Order;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static api.StoreApi.getOrderById;
import static utils.CommonMethods.*;

@Listeners(AllureTestNg.class)
public class StoreTest {
    private static final Logger logger = LogManager.getLogger(PetTest.class);
    String id;

    @AfterMethod
    public void afterMethod() {
        logger.info("After Test clean up starting");
        Response response = StoreApi.deleteUserByUserName(id);
        checkResponseStatusCode(response, 200);
        logger.info("Delete order with id = " + id + " done");
        logger.info("After Test clean up done");
    }

    @Feature("Check order")
    @Description("Check add new order to petstore")
    @Test
    public void addNewOrderTest() {
        Order order = new Order();

        Response response = StoreApi.addNewOrder(order);
        checkResponseStatusCode(response, 200);
        logger.info("new order added");
        checkResponseByJsonPathNotEmpty(response, "id");
        logger.info("response not empty");

        id = getTextFromResponseByJsonPath(response, "id");
        logger.info("new order id updated to " + id);
    }

    @Feature("Check order")
    @Description("Check get order from petstore by ID")
    @Test
    public void getOrderByIdTest() {
        Order order = new Order("4");

        Response orderById = getOrderById(order.getId());
        checkResponseStatusCode(orderById, 200);
        logger.info("order by Id got");

        checkResponseByJsonPathNotEmpty(orderById, "id");
        logger.info("response not empty");
        checkResponseByJsonPathEqualsExpectedText(orderById, "status", "placed");
        logger.info("order status is placed");
    }
}
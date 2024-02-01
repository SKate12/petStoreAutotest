package tests;

import api.UserApi;
import dto.User;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.UUID;

import static utils.CommonMethods.*;

@Listeners(AllureTestNg.class)
public class UserTest {
    private static final Logger logger = LogManager.getLogger(PetTest.class);
    String userName;

    @AfterMethod
    public void afterMethod() {
        logger.info("After Test clean up starting");
        Response response = UserApi.deleteUserByUserName(userName);
        checkResponseStatusCode(response, 404);
        logger.info("Delete user with username = " + userName + " done");
        logger.info("After Test clean up done");
    }

    @Feature("Check of User")
    @Description("Check add new user to petstore")
    @Test
    public void addNewUserTest() {
        User user = new User("nafanya" + UUID.randomUUID());

        Response response = UserApi.addNewUser(user);
        logger.info("new user added");
        checkResponseStatusCode(response, 200);
        logger.info("response status code is 200");
        checkResponseByJsonPathNotEmpty(response, "type");
        logger.info("response not empty");

        userName = user.getUserName();
        logger.info("new user name updated to " + userName);
    }

    @Feature("Check of User")
    @Description("Check get user from petstore by userName")
    @Test
    public void getUserByUserNameTest() {
        User user = new User("user1");

        Response userByName = UserApi.getUserByName(user.getUserName());
        //в данный момент не работает вызов этого метода на сваггере
        checkResponseStatusCode(userByName, 200);
        logger.info("user by name got");
        checkResponseByJsonPathNotEmpty(userByName, "id");
        logger.info("user by name got not empty id");
        checkResponseByJsonPathEqualsExpectedText(userByName, "username", "user1");
        logger.info("username from response equals expected");
    }
}
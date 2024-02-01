package tests;

import api.PetApi;
import dto.Pet;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static utils.CommonMethods.*;

@Listeners(AllureTestNg.class)
public class PetTest {
    private static final Logger logger = LogManager.getLogger(PetTest.class);
    String id;

    @AfterMethod
    public void afterMethod() {
        logger.info("After Test clean up starting");
        Response response = PetApi.deletePetById(id);
        checkResponseStatusCode(response, 200);
        logger.info("Delete pet with id = " + id + " done");
        logger.info("After Test clean up done");
    }

    @Feature("Check pet")
    @Description("Check add new pet to petstore")
    @Test
    public void addNewPetTest() {
        Pet pet = new Pet("DoggieTest_" + UUID.randomUUID(), List.of("somePicture"));
        Response response = PetApi.addNewPet(pet);
        logger.info("new pet added");
        checkResponseStatusCode(response, 200);
        logger.info("response status code is 200");
        checkResponseByJsonPathEqualsExpectedText(response, "name", pet.getName());
        logger.info("response contains pet name");

        id = getTextFromResponseByJsonPath(response, "id");
        logger.info("new pet id updated to " + id);
    }

    @Feature("Check pet")
    @Description("Check get pet from petstore by ID")
    @Test
    public void findPetByIdTest() {
        Pet pet = new Pet("DoggieTest_" + UUID.randomUUID(), List.of("somePicture"));
        Response response = PetApi.addNewPet(pet);
        logger.info("new pet added");
        id = getTextFromResponseByJsonPath(response, "id");
        logger.info("new pet id updated to " + id);

        Response petById = PetApi.getPetById(id);
        logger.info("got new pet by id");
        checkResponseStatusCode(petById, 200);
        logger.info("response status code is 200");
        checkResponseByJsonPathEqualsExpectedText(petById, "name", pet.getName());
        logger.info("response contains pet name");
    }
}
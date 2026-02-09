package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.RandomUtils;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

public class CreateUserTest {
    private static final String API_URL = "https://petstore.swagger.io";
    private static final String BASE_PATH = "/v2";
    private static final String CREATE_USER_ENDPOINT = "/user";

    @Feature("User API")
    @Story("Create user")
    @Description("Verify user can be created successfully")
    @Test(description = "Create a user - 200 Success")
    public void createUser() {
        User user = new User();
        user.setUsername(RandomUtils.getRandomAlphabeticString());
        user.setFirstName("MARGO");
        user.setLastName("KAPLINSKAYA");
        user.setEmail(RandomUtils.getRandomAlphabeticString());
        user.setPassword(RandomUtils.getRandomAlphabeticString());
        user.setPhone(RandomUtils.getRandomAlphabeticString());
        user.setUserStatus(0);

        given()
                .baseUri(API_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body(user, ObjectMapperType.JACKSON_2)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}

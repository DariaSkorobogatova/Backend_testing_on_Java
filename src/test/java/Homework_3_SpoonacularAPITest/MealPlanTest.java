package Homework_3_SpoonacularAPITest;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class MealPlanTest extends AbstractTest{
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "9be7df76ff276b88fa1cd000e809f9a7264b9f59")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1655208000,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/dasha1/items")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "9be7df76ff276b88fa1cd000e809f9a7264b9f59")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/dasha1/items/" + id)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }
}

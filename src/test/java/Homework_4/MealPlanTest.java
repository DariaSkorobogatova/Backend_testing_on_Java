package Homework_4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class MealPlanTest extends AbstractTest {

    @Test
    void addMealTest() {

//    AddMealRequest request = new AddMealRequest();
//    request.setDate(1655208000);
//    request.setSlot(1);
//    request.setPosition(0);
//    request.setType("INGREDIENTS");
//    Ingredient ingredient = new Ingredient("1 banana");
//    Value value = new Value(ingredient);
//    request.setValue(value);

        String id = given().spec(getRequestMealPlan())
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
                .post("https://api.spoonacular.com/mealplanner/{name}/items", getName())
                .then()
                .spec(getResponseMealPlan())
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given().spec(getRequestMealPlan())
                .delete("https://api.spoonacular.com/mealplanner/{name}/items/" + id,  getName())
                .then()
                .spec(getResponseMealPlan());
    }
}

package Homework_4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;


public class ComplexSearchTest extends AbstractTest {

    @Test
    void exampleFromDoc() {
        given().spec(getRequestAPIkeyLog())
                .queryParam("query", "pasta")
                .queryParam("maxFat", 25)
                .queryParam("number", 2)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseComplexSearch())
                .assertThat()
                .body("number", equalTo(2))
                .body("results[0].nutrition.nutrients[0].amount", lessThan(25.0F))
                .body("results[1].nutrition.nutrients[0].amount", lessThan(25.0F))
                .body("results[0].title", containsString("Pasta"));
    }
    @Test
    void minCarbs() {
        given().spec(getRequestAPIkeyLog())
                .queryParam("query", "Pizza")
                .queryParam("maxFat", 100)
                .queryParam("number", 2)
                .queryParam("minCarbs", 67)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseComplexSearch())
                .assertThat()
                .body("number", equalTo(2))
                .body("results[0].nutrition.nutrients[0].amount", lessThan(100.0F))
                .body("results[1].nutrition.nutrients[0].amount", lessThan(100.0F))
                .body("results[0].nutrition.nutrients[1].amount", greaterThan (67.0F))
                .body("results[1].nutrition.nutrients[1].amount", greaterThan(67.0F))
                .body("results[0].title", containsString("Pizza"));
    }
    @Test
    void titleMatch() {
        given().spec(getRequestAPIkeyLog())
                .queryParam("titleMatch", "Chicken")
                .queryParam("number", 3)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseComplexSearch())
                .assertThat()
                .body("number", equalTo(3))
                .body("results[0].title", containsString("Chicken"))
                .body("results[1].title", containsString("Chicken"))
                .body("results[2].title", containsString("Chicken"));
    }
    @Test
    void RecipeBoxId() {
        given().spec(getRequestAPIkeyLog())
                .queryParam("recipeBoxId", 2468)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseComplexSearch())
                .assertThat()
                .body("number", equalTo(10));
    }
    @Test
    void noSuchCuisine() {
        given().spec(getRequestAPIkeyLog())
                .queryParam("cuisine", "Russian")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseComplexSearch())
                .assertThat()
                .body("number", equalTo(10))
                .body("results.size()", equalTo(0))
                .body("totalResults", equalTo(0));
    }
}
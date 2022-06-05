package Homework_3_SpoonacularAPITest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;


public class ComplexSearchTest extends AbstractTest {

    @Test
    void exampleFromDoc() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("maxFat", 25)
                .queryParam("number", 2)
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("offset", equalTo(0))
                .body("number", equalTo(2))
                .body("results[0].nutrition.nutrients[0].amount", lessThan(25.0F))
                .body("results[1].nutrition.nutrients[0].amount", lessThan(25.0F))
                .body("results[0].title", containsString("Pasta"))
                .contentType(ContentType.JSON);
    }
    @Test
    void minCarbs() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pizza")
                .queryParam("maxFat", 100)
                .queryParam("number", 2)
                .queryParam("minCarbs", 67)
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("offset", equalTo(0))
                .body("number", equalTo(2))
                .body("results[0].nutrition.nutrients[0].amount", lessThan(100.0F))
                .body("results[1].nutrition.nutrients[0].amount", lessThan(100.0F))
                .body("results[0].nutrition.nutrients[1].amount", greaterThan (67.0F))
                .body("results[1].nutrition.nutrients[1].amount", greaterThan(67.0F))
                .body("results[0].title", containsString("Pizza"))
                .contentType(ContentType.JSON);
    }
    @Test
    void titleMatch() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", "Chicken")
                .queryParam("number", 3)
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("offset", equalTo(0))
                .body("number", equalTo(3))
                .body("results[0].title", containsString("Chicken"))
                .body("results[1].title", containsString("Chicken"))
                .body("results[2].title", containsString("Chicken"))
                .contentType(ContentType.JSON);
    }
    @Test
    void RecipeBoxId() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("recipeBoxId", 2468)
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .contentType(ContentType.JSON);
    }
    @Test
    void noSuchCuisine() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Russian")
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("results.size()", equalTo(0))
                .body("totalResults", equalTo(0))
                .contentType(ContentType.JSON);
    }
}
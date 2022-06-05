package Homework_3_SpoonacularAPITest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class CuisineTest extends AbstractTest {
    @Test
    void exampleFromDoc() {
        given()
                .queryParam("apiKey", getApiKey())
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines[0]", equalTo("Mediterranean"))
                .body("cuisines[1]", equalTo("European"))
                .body("cuisines[2]", equalTo("Italian"))
                .contentType(ContentType.JSON);
    }
    @Test
    void byTitle() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("cuisine", equalTo("Chinese"))
                .body("cuisines[0]", equalTo("Chinese"))
                .body("cuisines[1]", equalTo("Asian"))
                .contentType(ContentType.JSON);
    }
    @Test
    void wrongMethod() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 405 Method Not Allowed")
                .statusCode(405);
    }
    @Test
    void noneExistingTitle() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","38927481!@@#$@%")
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive")
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines[0]", equalTo("Mediterranean"))
                .body("cuisines[1]", equalTo("European"))
                .body("cuisines[2]", equalTo("Italian"))
                .contentType(ContentType.JSON);
    }
    @Test
    void noAPIkey() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(401)
                .statusLine("HTTP/1.1 401 Unauthorized")
                .contentType(ContentType.JSON);
    }
}

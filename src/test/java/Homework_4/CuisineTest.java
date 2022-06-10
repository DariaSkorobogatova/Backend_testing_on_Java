package Homework_4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class CuisineTest extends AbstractTest {
    @Test
    void exampleFromDoc() {
        given().spec(getRequestAPIkeyLog())
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseCuisineTest())
                .assertThat()
                .body("cuisine", equalTo("Italian"))
                .body("cuisines[0]", equalTo("Italian"))
                .body("cuisines[1]", equalTo("Mediterranean"))
                .body("cuisines[2]", equalTo("European"));
    }
    @Test
    void byTitle() {
        given().spec(getRequestAPIkeyLogUrlencoded())
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseCuisineTest())
                .assertThat()
                .body("cuisine", equalTo("Chinese"))
                .body("cuisines[0]", equalTo("Chinese"))
                .body("cuisines[1]", equalTo("Asian"));
    }
    @Test
    void wrongMethod() {
        given().spec(getRequestAPIkeyLogUrlencoded())
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .when()
                .get(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 405 Method Not Allowed")
                .statusCode(405);
    }
    @Test
    void noneExistingTitle() {
        given().spec(getRequestAPIkeyLogUrlencoded())
                .formParam("title","38927481!@@#$@%")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseCuisineTest())
                .assertThat()
                .body("cuisine", equalTo("Italian"))
                .body("cuisines[0]", equalTo("Italian"))
                .body("cuisines[1]", equalTo("Mediterranean"))
                .body("cuisines[2]", equalTo("European"));
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

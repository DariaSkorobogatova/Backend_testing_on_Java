package Homework_4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;

public abstract class AbstractTest {

        static Properties prop = new Properties();
        private static InputStream configFile;
        private static String apiKey;
        private static String baseUrl;
        protected static ResponseSpecification responseComplexSearch;
        protected static ResponseSpecification responseCuisineTest;
        protected static ResponseSpecification responseMealPlan;
        protected static RequestSpecification requestAPIkeyLog;
        protected static RequestSpecification requestAPIkeyLogUrlencoded;
        protected static RequestSpecification requestMealPlan;
        private static String name = "dasha1";

        @BeforeAll
        static void initTest() throws IOException {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            configFile = new FileInputStream("src/main/resources/my.properties");
            prop.load(configFile);

            apiKey =  prop.getProperty("apiKey");
            baseUrl= prop.getProperty("base_url");

            responseComplexSearch = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectStatusLine("HTTP/1.1 200 OK")
                    .expectHeader("Connection", "keep-alive")
                    .expectBody("offset", equalTo(0))
                    .expectContentType(ContentType.JSON)
                    .build();

            responseCuisineTest = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectStatusLine("HTTP/1.1 200 OK")
                    .expectHeader("Connection", "keep-alive")
                    .expectContentType(ContentType.JSON)
                    .build();

            responseMealPlan = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectStatusLine("HTTP/1.1 200 OK")
                    .expectBody("status", equalTo("success"))
                    .expectContentType(ContentType.JSON)
                    .build();

            requestAPIkeyLog = new RequestSpecBuilder()
                    .addQueryParam("apiKey", apiKey)
                    .log(LogDetail.ALL)
                    .build();

            requestAPIkeyLogUrlencoded = new RequestSpecBuilder()
                    .addQueryParam("apiKey", apiKey)
                    .setContentType("application/x-www-form-urlencoded")
                    .log(LogDetail.ALL)
                    .build();

            requestMealPlan = new RequestSpecBuilder()
                    .addQueryParam("hash", "9be7df76ff276b88fa1cd000e809f9a7264b9f59")
                    .addQueryParam("apiKey", apiKey)
                    .log(LogDetail.ALL)
                    .build();

        }
        public static String getApiKey() {
            return apiKey;
        }

        public static String getBaseUrl() {
            return baseUrl;
        }

        public RequestSpecification getRequestAPIkeyLog(){
            return requestAPIkeyLog;
        }
        public RequestSpecification getRequestAPIkeyLogUrlencoded(){
            return requestAPIkeyLogUrlencoded;
        }
        public RequestSpecification getRequestMealPlan(){
            return requestMealPlan;
        }
        public ResponseSpecification getResponseComplexSearch(){
            return responseComplexSearch;
        }
        public ResponseSpecification getResponseCuisineTest(){
            return responseCuisineTest;
        }
        public ResponseSpecification getResponseMealPlan(){
            return responseMealPlan;
        }
        public String getName(){
            return name;
        }

    }


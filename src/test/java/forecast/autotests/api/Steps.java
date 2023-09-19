package forecast.autotests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import forecast.autotests.pojos.Location;
import forecast.autotests.pojos.Request;
import forecast.autotests.pojos.Root;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static forecast.autotests.BaseTest.req;
import static forecast.autotests.utils.Const.*;
import static forecast.autotests.utils.Utils.setFile;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Steps {


    ObjectMapper mapper = new ObjectMapper();


    @SneakyThrows
    @Given("first step with params {string}")
    public void firstApiTest(String city) {
        Root root = given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN + "&query={city}", city)
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/body.json")).log().all()
                .extract().body().jsonPath().getObject("", Root.class);

        Request request = mapper.readValue(setFile("requestBody", city), Request.class);
        assertAll("some message",
                () -> Assertions.assertEquals(request.getLanguage(), root.request.getLanguage()),
                () -> Assertions.assertEquals(request.getUnit(), root.request.getUnit()),
                () -> Assertions.assertEquals(request.getType(), root.request.getType()));


        Location location = mapper.readValue(setFile("locationBody", city), Location.class);
        assertAll("locationBody params doesnt match",
                () -> Assertions.assertEquals(location.getCountry(), root.location.getCountry()),
                () -> Assertions.assertEquals(request.getType(), root.request.getType()),
                () -> Assertions.assertEquals(request.getQuery(), root.request.getQuery()));
    }


    @Given("Method GET negative step")
    public void negativeStep(List<String> cities) {
        given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + INVALID_TOKEN + "&query={city}", cities.get(0))
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/negativeBody.json")).log().all()
                .and()
                .assertThat().extract().body().jsonPath().get("success").equals("false");

    }


    @Then("Method GET invalid city {string}")
    public void anotherNegativeStep(String city) {
        given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN + "&query={city}", city)
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/negativeBody.json")).log().all()
                .and().body("error.code", equalTo(615))
                .and().body("error.type", equalTo("request_failed"))
                .and().body("error.info", equalTo("Your API request failed. Please try again or contact support."));
    }


    @Then("Method GET missing query")
    public void missingQuery() {
        given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN)
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/negativeBody.json")).log().all()
                .and().body("error.code", equalTo(601))
                .and().body("error.type", equalTo("missing_query"))
                .and().body("error.info", equalTo("Please specify a valid location identifier using the query parameter."));
    }

    @Then("Method GET several cities")
    public void missingSeveralCities() {
        given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN+ "&query={city}","London;Singapur;Shanghai")
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/negativeBody.json")).log().all()
                .and().body("error.code", equalTo(604))
                .and().body("error.type", equalTo("bulk_queries_not_supported_on_plan"))
                .and().body("error.info", equalTo("Your current subscription plan does not support bulk queries. Please upgrade your account to use this feature."));
    }



}

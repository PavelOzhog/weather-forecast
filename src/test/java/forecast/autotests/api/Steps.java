package forecast.autotests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import forecast.autotests.pojos.Location;
import forecast.autotests.pojos.Request;
import forecast.autotests.pojos.Root;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static forecast.autotests.BaseTest.req;
import static forecast.autotests.utils.Const.BASE_URL;
import static forecast.autotests.utils.Const.TOKEN;
import static forecast.autotests.utils.Utils.setFile;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Steps {


    ObjectMapper mapper = new ObjectMapper();



    @SneakyThrows
    @Given("first step with params {string}")
    public void firstApiTest(String city)  {
        Root root = given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN + "&query={city}",city)
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/body.json")).log().all()
                .extract().body().jsonPath().getObject("", Root.class);

        Request request = mapper.readValue(setFile("requestBody",city), Request.class);
        assertAll("some message",
                () -> Assertions.assertEquals(request.getLanguage(), root.request.getLanguage()),
                () -> Assertions.assertEquals(request.getUnit(), root.request.getUnit()),
                () -> Assertions.assertEquals(request.getType(), root.request.getType()));


        Location location = mapper.readValue(setFile("locationBody",city), Location.class);
        assertAll("locationBody params doesnt match",
                () -> Assertions.assertEquals(location.getCountry(), root.location.getCountry()),
                () -> Assertions.assertEquals(request.getType(), root.request.getType()),
                () -> Assertions.assertEquals(request.getQuery(), root.request.getQuery()));
    }


    @SneakyThrows
    @Test
    void test() {
        Root root = given()
                .spec(req).log().all()
                .when().get("/current?access_key=" + TOKEN + "&query=Moscow")
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ApiJson/body.json")).log().all()
                .extract().body().jsonPath().getObject("", Root.class);

        Request request = mapper.readValue(setFile("requestBody","city"), Request.class);
        assertAll("request params doesnt match",
                () -> Assertions.assertEquals(request.getLanguage(), root.request.getLanguage()),
                () -> Assertions.assertEquals(request.getUnit(), root.request.getUnit()),
                () -> Assertions.assertEquals(request.getUnit(), root.request.getUnit()));


        Location location = mapper.readValue(setFile("locationBody","city"), Location.class);
        assertAll("locationBody params doesnt match",
                () -> Assertions.assertEquals(location.getCountry(), root.location.getCountry()),
                () -> Assertions.assertEquals(request.getType(), root.request.getType()),
                () -> Assertions.assertEquals(request.getQuery(), root.request.getQuery()));


    }


    @Then("get order by param {string}, {string}")
    public void getOrder(String idOrder, String quantityOrder) {
        Integer idOrder1 = Integer.parseInt(idOrder);
        Integer quantityOrder1 = Integer.parseInt(quantityOrder);
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().get("PATH_GET_DELETE" + idOrder1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "id", equalTo(idOrder1),
                        "quantity", equalTo(quantityOrder1)
                );
    }

    @Then("delete order by {string}")
    public void deleteOrderBy(String idOrder) {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().delete("PATH_GET_DELETE" + idOrder)
                .then()
                .statusCode(HttpStatus.SC_OK);


    }

}

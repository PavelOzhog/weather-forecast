package sogaz.autotests.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static sogaz.autotests.BaseTest.*;
import static sogaz.autotests.utils.Const.*;

public class Steps {

    @Given("first step with params {string}, {string}, {string}")
    public void firstApiTest(String id, String quantity, String complete) throws IOException {
        given()
                .spec(req)
                .body(setBody(setFile(BODY_FILE), id, quantity, complete))
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id",equalTo(Integer.parseInt(id)));

    }


    @Then("get order by param {string}, {string}")
    public void getOrder(String idOrder, String quantityOrder) {
        Integer idOrder1 = Integer.parseInt(idOrder);
        Integer quantityOrder1 = Integer.parseInt(quantityOrder);
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().get(PATH_GET_DELETE + idOrder1)
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
                .when().delete(PATH_GET_DELETE + idOrder)
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

}

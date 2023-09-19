package forecast.autotests;

import com.github.tsohr.JSONObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static forecast.autotests.utils.Const.*;

public class BaseTest {

// @BeforeAll
// public static void setup() {
//  RestAssured.baseURI = BASE_URL;
// }

    public static RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
}

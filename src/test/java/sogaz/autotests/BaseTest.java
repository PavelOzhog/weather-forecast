package sogaz.autotests;

import com.github.tsohr.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static sogaz.autotests.utils.Const.*;

public class BaseTest {


 public static File setFile(String fileName) {
  return new File("src/test/resources/ApiJson/" + fileName + ".json");
 }



 public  static String setBody(File file, String id, String quantity, String complete) throws IOException {
  JSONObject json1 = new JSONObject(Files.readString(Paths.get(file.getPath())));
  json1.put("id", id);
  json1.put("quantity", quantity);
  json1.put("complete", complete);
  return json1.toString();
 }




//    public Map<String, Object> setBody() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 99);
//        map.put("petId", 103);
//        map.put("quantity", 777);
//        map.put("shipDate", "2022-08-10T15:01:27.971Z");
//        map.put("status", "approved");
//        return map;
//    }


 public static   RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(PATH_POST)
            .build();



}

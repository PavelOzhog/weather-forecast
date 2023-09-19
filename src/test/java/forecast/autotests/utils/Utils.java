package forecast.autotests.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class Utils {


    public static File setFile(String fileName, String city) {

        return switch (city) {
            case "Boston" ->
                    new File("src/test/resources/ApiJson/PojoTestData/" + city + "/" + fileName + city + ".json");
            case "Moscow" ->
                    new File("src/test/resources/ApiJson/PojoTestData/" + city + "/" + fileName + city + ".json");
            case "Tokio" ->
                    new File("src/test/resources/ApiJson/PojoTestData/" + city + "/" + fileName + city + ".json");
            case "New York" ->
                    new File("src/test/resources/ApiJson/PojoTestData/" + city.replaceFirst("\\s", "") + "/" + fileName + city.replaceFirst("\\s", "") + ".json");
            default -> null;
        };

    }


}

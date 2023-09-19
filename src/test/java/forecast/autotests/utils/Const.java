package forecast.autotests.utils;

import java.util.Map;

public class Const {
    public static final String BASE_URL = "http://api.weatherstack.com";
    public static final String PATH_GET = "/current?access_key=b0e0cc5db87a88cd46321169ecec8226&query=New York";
    public static final String TOKEN = "b0e0cc5db87a88cd46321169ecec8226";
//    public static final String PATH_GET_DELETE = "/store/order/";
//     public static final String BODY_FILE = "body";

    public static final Map<String, String> testMap = Map.of("access_key", "b0e0cc5db87a88cd46321169ecec8226",
            "query", "New York");
}

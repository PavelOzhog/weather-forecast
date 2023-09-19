package forecast.autotests.utils;


public class Const {
    public static final String BASE_URL = "http://api.weatherstack.com";
    public static final String TOKEN = "b0e0cc5db87a88cd46321169ecec8226";
    public static final String INVALID_TOKEN = new StringBuilder(TOKEN).reverse().toString();
}

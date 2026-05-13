package data;

public class CourierData {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    public static final String LOGIN = "ninja" + System.currentTimeMillis();
    public static final String PASSWORD = "1234";
    public static final String FIRSTNAME = "saske";

    public static final String INVALID_LOGIN_COURIER_DATA = "{\"password\": \"%s\"," + "\"firstName\": \"%s\"}";
    public static final String INVALID_PASSWORD_COURIER_DATA = "{\"login\": \"%s\"," + "\"firstName\": \"%s\"}";
public static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/";
}

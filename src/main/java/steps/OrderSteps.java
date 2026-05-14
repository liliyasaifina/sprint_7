package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.OrderModel;
import static data.OrderData.CANCEL_ORDER_PATH;
import static data.OrderData.CREATE_ORDER_PATH;
import static io.restassured.RestAssured.given;


public class OrderSteps {
    @Step("Creation scooter order with valid data set")
    public static Response createOrder(OrderModel order){
        return  given()
                .contentType("application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH).then().extract().response();
    }
    @Step("Cancelling existent order with valid data set")
    public static  Response orderCancelling(String track){
        return  given()
                .contentType("application/json")
                .when()
                .put(CANCEL_ORDER_PATH + track).then().extract().response();
    }

    @Step("Receipt of the list of orders ")
    public static Response orderListGetting() {
        return given()
                .when()
                .get(CREATE_ORDER_PATH)
                .then()
                .extract()
                .response();
    }
}

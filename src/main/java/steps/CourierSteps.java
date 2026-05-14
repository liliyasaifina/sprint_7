package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CourierCreateModel;
import model.CourierLoginModel;
import static data.CourierData.*;
import static io.restassured.RestAssured.given;
public class CourierSteps {

    @Step("Creation courier account")
    public  static Response createCourier(CourierCreateModel courier){
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .extract().response();
}

    @Step("Creation courier account with existent courier account data")
    public static Response createExistentCourier(CourierCreateModel courier){
        return given()
                .contentType("application/json")
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .extract().response();
}

    @Step("Creation courier account with invalid/absence login data")
    public static Response invalidLoginCourier(CourierCreateModel courier){
        return given()
             .contentType("application/json")
             .body(courier)
             .when()
             .post(CREATE_COURIER_PATH).then().extract().response();
 }

    @Step("Creation courier account with invalid/absence password data")
    public static Response invalidPasswordCourier(CourierCreateModel courier){
        return given()
                .contentType("application/json")
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with valid data")
    public static io.restassured.response.Response logInCourier(CourierLoginModel courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with invalid/absence login data")
    public static Response absenceLogin(CourierLoginModel courier){
        return given()
            .header("Content-type", "application/json")
            .and()
            .body(courier)
            .when()
            .post(LOGIN_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with invalid/absence password data")
    public static Response absencePassword(CourierLoginModel courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH).then().extract().response();
    }

    @Step("Login courier account with invalid login data of non-existent courier")
    public static Response wrongLogin(CourierLoginModel courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH).then().extract().response();
    }

    @Step("Removal existent courier account with valid login and password data")
    public static Response courierRemoval(Integer courierId){
        return given()
                   .contentType("application/json")
                   .when()
                   .delete(DELETE_COURIER_PATH + courierId).then().extract().response();
    }
}

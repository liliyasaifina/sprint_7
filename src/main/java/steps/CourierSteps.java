package steps;




import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CourierModel;
import static data.CourierData.*;
import static io.restassured.RestAssured.given;
public class CourierSteps {

    @Step("Creation courier account")
    public  static Response createCourier(CourierModel courier){
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
public static Response createExistentCourier(CourierModel courier){
    Gson gson = new Gson();
    String duplicateCourierData = gson.toJson(courier);
        return given()
                .contentType("application/json")
                .body(duplicateCourierData)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .extract().response();
}

    @Step("Creation courier account with invalid/absence login data")
 public static Response invalidLoginCourier(String jsonBody){
     return given()
             .contentType("application/json")
             .body(jsonBody)
             .when()
             .post(CREATE_COURIER_PATH).then().extract().response();
 }

    @Step("Creation courier account with invalid/absence password data")
public static Response invalidPasswordCourier(String jsonBody){
        return given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(CREATE_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with valid data")
public static io.restassured.response.Response logInCourier(CourierModel courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with invalid/absence login data")
public static Response absenceLogin(String jsonBody){
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(jsonBody)
            .when()
            .post(LOGIN_COURIER_PATH).then().extract().response();
}

    @Step("Login courier account with invalid/absence password data")
    public static Response absencePassword(String jsonBody){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post(LOGIN_COURIER_PATH).then().extract().response();
    }

    @Step("Login courier account with invalid login data of non-existent courier")
    public static Response wrongLogin(String jsonBody){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
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

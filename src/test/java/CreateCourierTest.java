
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.CourierModel;
import org.junit.After;

import org.junit.Test;


import java.util.logging.Logger;

import static data.CourierData.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;


public class CreateCourierTest extends BaseAPITest{

    @Test
    @DisplayName("Successful Courier account creation")
    @Description("Basic test for /api/v1/courier endpoint")
        public void successfulCourierCreationTest() {

        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

       createCourier(courier)
              .then()
                .statusCode(201).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Negative duplicate Courier data test")
    @Description("Test the prohibition of creating two identical couriers/creating a user with an existing login")
        public void errorExistingCourierCreationTest() {

        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
            createCourier(courier)
                .then().statusCode(201).body("ok", equalTo(true));
               createExistentCourier(courier) .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Creation courier test the invalid login/absence login data field")
        public void invalidLoginTest() {

        String invalidLogin = String.format(INVALID_LOGIN_COURIER_DATA, PASSWORD, FIRSTNAME);
        invalidLoginCourier(invalidLogin).then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи")); // проверяем, что есть поле error с сообщением
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Creation courier test the invalid password/absence password data field")
    public void invalidPasswordTest() {

        String invalidPassword = String.format(INVALID_PASSWORD_COURIER_DATA, LOGIN, FIRSTNAME);
        invalidPasswordCourier(invalidPassword).then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void cleanUp() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        Response idResponse = logInCourier(courier);
        JsonPath jsonId = idResponse.jsonPath();

        Integer courierId = jsonId.get("id");
        if (courierId != null) {
            courierRemoval(courierId).then()
                    .statusCode(200);
        } else {
            System.out.println("Id курьера не найден");
        }
    }

}



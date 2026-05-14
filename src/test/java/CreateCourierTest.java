
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.CourierCreateModel;
import model.CourierLoginModel;
import org.junit.After;
import org.junit.Test;
import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;


public class CreateCourierTest extends BaseAPITest{

    @Test
    @DisplayName("Successful Courier account creation")
    @Description("Basic test for /api/v1/courier endpoint")
    public void successfulCourierCreationTest() {
        CourierCreateModel courier = new CourierCreateModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
              .then()
                .statusCode(201).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Negative duplicate Courier data test")
    @Description("Test the prohibition of creating two identical couriers/creating a user with an existing login")
    public void errorExistingCourierCreationTest() {
        CourierCreateModel courier = new CourierCreateModel(LOGIN, PASSWORD, FIRSTNAME);
            createCourier(courier)
                .then().statusCode(201).body("ok", equalTo(true));
            createExistentCourier(courier).then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Creation courier test the invalid login/absence login data field")
    public void invalidLoginTest() {
        CourierCreateModel courier = new CourierCreateModel(INVALID_LOGIN_COURIER_DATA, PASSWORD, FIRSTNAME);
            invalidLoginCourier(courier)
                .then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Creation courier test the invalid password/absence password data field")
    public void invalidPasswordTest() {
        CourierCreateModel courier = new CourierCreateModel(LOGIN, INVALID_PASSWORD_COURIER_DATA, FIRSTNAME);
            invalidPasswordCourier(courier).then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void cleanUp() {
        CourierLoginModel courier = new CourierLoginModel(LOGIN, PASSWORD);
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



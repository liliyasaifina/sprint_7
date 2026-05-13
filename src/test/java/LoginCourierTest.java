import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.CourierModel;
import org.junit.After;
import org.junit.Test;
import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.*;


public class LoginCourierTest extends BaseAPITest{

    @Test
    @DisplayName("Successful Courier account login")
    @Description("Basic test for /api/v1/courier/login endpoint")
    public void successfulCourierLogInTest(){
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then().statusCode(201).body("ok", equalTo(true));
        logInCourier(courier)
                .then().statusCode(200).body("id",notNullValue());
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Login test the invalid login/absence login data field")
    public void errorLoginAbsenceTest(){
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then().statusCode(201).body("ok", equalTo(true));
        String loginAbsence = String.format(INVALID_LOGIN_COURIER_DATA, PASSWORD, FIRSTNAME);
        absenceLogin(loginAbsence)
                .then().statusCode(400).body("message",equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Login test the invalid password/absence password data field")
    public void errorPasswordAbsenceTest(){
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then().statusCode(201).body("ok", equalTo(true));
        String passwordAbsence = String.format(INVALID_PASSWORD_COURIER_DATA, LOGIN,  FIRSTNAME);
        absencePassword(passwordAbsence)
                .then().statusCode(400).body("message",equalTo("Недостаточно данных для входа"));
    }

@Test
@DisplayName("Negative invalid Courier data test")
@Description("Login test the wrong login data field")
public void errorWrongLoginTest(){
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
    createCourier(courier)
            .then().statusCode(201).body("ok", equalTo(true));
    String newLogin = "wrong_" + LOGIN;
    CourierModel wrongLoginCourier = new CourierModel(newLogin, PASSWORD, FIRSTNAME);
    Gson gson = new Gson();
    String wrongLoginJson = gson.toJson(wrongLoginCourier);
    wrongLogin(wrongLoginJson)
            .then().statusCode(404).body("message",equalTo("Учетная запись не найдена"));
}

    @Test
    @DisplayName("Negative invalid Courier data test")
    @Description("Login test a non-existent Courier account")
    public void nonExistentCourierTest(){
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        logInCourier(courier)
                .then().statusCode(404).body("message",equalTo("Учетная запись не найдена"));
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

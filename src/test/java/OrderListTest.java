

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.orderListGetting;

public class OrderListTest extends BaseAPITest {

    @Test
    @DisplayName("Successful receipt of the list of orders")
    @Description("Basic test for /api/v1/orders endpoint")
    public void getOrdersListTest()  {
        orderListGetting().then()
                .statusCode(SC_OK).body("orders", notNullValue());
    }
}


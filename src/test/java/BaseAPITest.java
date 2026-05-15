import io.restassured.RestAssured;
import org.junit.Before;
import static data.CourierData.BASE_URI;

public class BaseAPITest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
}

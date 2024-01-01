package TestCases;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class TC_01_validateLogin {
    @Test(priority = 0)
    public void loginWithValidInput(){
        RestAssured.given()
                .header("Content-type", "application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .log().body();
    }
}

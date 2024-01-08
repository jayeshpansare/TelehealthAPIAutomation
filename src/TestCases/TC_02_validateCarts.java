package TestCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC_02_validateCarts {
    RequestSpecification headers;

    @BeforeMethod
    public void start(){
        Map<String, String > Headers = new HashMap<>();
        Headers.put("Content-type", "application/json");

         headers = given()
                .headers(Headers)
                .baseUri("https://dummyjson.com");
    }
    @Test
    public void getAllCarts(){
        RestAssured.given()
                .spec(headers)
                .when()
                .get("/carts")
                .then()
                .assertThat()
                .body("carts[0].products[0].title", equalTo("Spring and summershoes"),
                        "carts[0].products[0].id", CoreMatchers.equalTo(59),
                        "carts[0].products[0].price", CoreMatchers.equalTo(20)
                        )
                .body("carts[0].products[1].title", equalTo("TC Reusable Silicone Magic Washing Gloves"),
                        "carts[0].products[1].id", CoreMatchers.equalTo(88),
                        "carts[0].products[1].price", CoreMatchers.equalTo(29));
    }
    @Test
    public void getSingleCart(){
        RestAssured.given().spec(headers)
                .when()
                .get("/carts/1")
                .then();
    }
    @Test
    public void getUserCart(){
        RestAssured.given().spec(headers)
                .when()
                .get("/carts/user/5")
                .then();
    }
    @Test
    public void addCart(){
        File objCartFile = new File("C:\\Users\\jayes\\IdeaProjects\\TelehealthAPIAutomation\\src\\Data\\cart.json");

        RestAssured.given()
                .spec(headers)
                .body(objCartFile)
                .when()
                .post("/carts/add")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void updateCart(){
        File objCartFile2 = new File("C:\\Users\\jayes\\IdeaProjects\\TelehealthAPIAutomation\\src\\Data\\cart2.json");
        RestAssured.given().spec(headers)
                .body(objCartFile2)
                .when()
                .put("/carts/1")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void deleteCart(){
        Response objDelete = RestAssured.given()
                .spec(headers)
                .when()
                .delete("/carts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(objDelete.path("userId").toString(), "97");
    }
}

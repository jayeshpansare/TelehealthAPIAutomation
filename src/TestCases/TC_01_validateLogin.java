package TestCases;

import Lib.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class TC_01_validateLogin extends Auth {
    RequestSpecification headerspec;

    @BeforeMethod
    public void start(){
        Map<String, String > Headers = new HashMap<>();
        Headers.put("Content-type", "application/json");

        headerspec = given().headers(Headers)
                .baseUri("https://dummyjson.com");
    }

    @Test(priority = 1, groups = {"smoke"})
    public void loginWithValidInput(){
        File objLoginFile = new File("C:\\Users\\jayes\\IdeaProjects\\TelehealthAPIAutomation\\src\\Data\\login.json");

        Response loginResult =  given().spec(headerspec)
                .body(objLoginFile)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .header("Content-type", "application/json; charset=utf-8")
                .body("id", is(15),
                        "username", equalTo("kminchelle"),
                        "email", equalTo("kminchelle@qq.com"),
                        "firstName", equalTo("Jeanne"),
                        "lastName", equalTo("Halvorson"),
                        "gender", equalTo("female"),
                        "token", notNullValue())
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();
    }
    @Test
    public void searchProduct(){
        Response resSearch = RestAssured.given()
                .spec(headerspec)
                .param("q","phone")
                .when()
                .get("/products/search")
                .then()
                .assertThat()
                .log()
                .body()
                .statusCode(200)
                .extract().response();
    }
    @Test
    public void getAllProduct(){
        RestAssured.given().spec(headerspec)
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("productResponce.json"));

    }
    @Test
    public void getSingleproduct(){
        Response objSingleProd = (Response) RestAssured.given().spec(headerspec)
                .when()
                .get("/products/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("iPhone X"),
                        "price", CoreMatchers.equalTo(899),
                        "discountPercentage", CoreMatchers.equalTo(17.94F),
                        "category", equalTo("smartphones"))
                .log()
                .body()
                .extract();
        ArrayList<String>  res= objSingleProd.path("images");
        System.out.println(res);
    }
    @Test(priority = 2, groups = {"smoke"})
    public void linitAndSkipproduct(){
        RestAssured.given()
                .spec(headerspec)
                .queryParams("limit", 10, "skip", 10, "select", "title,price")
                .when()
                .get("/products")
                .then()
                .assertThat()
                .body("products.id[0]", CoreMatchers.equalTo(11))
                .statusCode(200)
                .log()
                .body()
                .extract();
    }
    @Test(priority = 3, groups = {"smoke"})
    public void getCategory(){
        RestAssured.given()
                .spec(headerspec)
                .when()
                .get("https://dummyjson.com/products/category/smartphones")
                .then()
                .statusCode(200)
                .assertThat()
                .body("total", CoreMatchers.equalTo(5))
                .extract();
    }
    @Test(priority = 4, groups = {"smoke"})
    public void ProductWithValidInpts(){
        Map<String, String> objproduct = new HashMap<>();
        objproduct.put("title", "BMW Pencil test");

        Response objAddproduct = (Response) RestAssured.given()
                .spec(headerspec)
                .body(objproduct)
                .when()
                .post("/products/add")
                .then()
                .assertThat()
                .body("title", equalTo("BMW Pencil test"),
                        "id", CoreMatchers.equalTo(101))
                .extract();

//        int productId = objAddproduct.path("id");
        int productId = 1;
        System.out.println(objAddproduct.path("title").toString());

        Map<String, String> objupdateproduct = new HashMap<>();
        objupdateproduct.put("title", "BMW Pencil update");

        RestAssured.given()
                .spec(headerspec)
                .body(objupdateproduct)
                .when()
                .put("/products/"+productId)
                .then();

        RestAssured.given()
                .spec(headerspec)
                .when()
                .delete("/products/"+productId)
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("iPhone 9"));
    }
}

package Lib;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class Auth {
    public Response getLoginResponce(){
        String getURL = BaseClass.readProperty().getProperty("URL");

        Map<String, String> payload = new HashMap<String, String>();
        payload.put("username", "sn.frontdesk1@gmail.com");
        payload.put("grant_type", "password");
        payload.put("password", "639054b2ecf8e43a580763da1b835348692354bdee2aad4644eee03993ef5096");
        JSONObject payloadData = new JSONObject(payload);

        Response loginResult =  given()
                .header("Content-type", "application/json")
                .header("Authorization", "Basic YjczMGVjMTctNDZlNy00NjdlLTlkOTItZThmNzZmMmZiNTM5OmYwMGVhZTBjLTdkYTMtNDcyOC1hMDEzLTA1YTIzZjBlMDBkOQ==")
                .body(payloadData.toJSONString())
                .when()
                .post("https://hospitals.knowyourmeds.com/api/v1/login")
                .then()
                .log()
                .body()
                .extract()
                .response();
        System.out.println(loginResult.getBody().toString());
        return loginResult;
    }
}

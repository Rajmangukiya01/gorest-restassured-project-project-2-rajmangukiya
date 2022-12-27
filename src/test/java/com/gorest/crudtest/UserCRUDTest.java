package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Leo Messi");
        userPojo.setEmail("leo123@gmail.com");
        userPojo.setGender("Male");
        userPojo.setStatus("Active");
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b7ab41823a5979e6fbb7cb153d7f46693768cf79d27a44174e55f90d5a40c89b")
                .body(userPojo)
                .when()
                .post("/users");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Leo Messi");
        userPojo.setEmail("leo123@gmail.com");
        userPojo.setGender("Male");
        userPojo.setStatus("Inactive");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b7ab41823a5979e6fbb7cb153d7f46693768cf79d27a44174e55f90d5a40c89b")
                .body(userPojo)
                .when()
                .put("/users/11261");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteUser() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b7ab41823a5979e6fbb7cb153d7f46693768cf79d27a44174e55f90d5a40c89b")
                .when()
                .delete("/users/11261");
        response.then().statusCode(204);
        response.prettyPrint();
    }
}

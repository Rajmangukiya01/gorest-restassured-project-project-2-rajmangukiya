package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        List<Object> idTotal = response.extract().path("id");
        System.out.println(idTotal.size());
        response.body("idTotal.size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        response.body("[2].name", equalTo("Kashyap Prajapat"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)

    @Test
    public void test003() {
        response.body("name", hasItem("Meghnad Jain"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("name", hasItems("Meghnad Jain", "Kashyap Prajapat", "Mrs. Aruna Sharma"));
    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("[4].email", equalTo("nagabhushanam_johar@abshire-tromp.net"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("[7].status", equalTo("active"));
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("[10].gender", equalTo("male"));
    }

}




package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in/public/v2";

        response =given()
                .when()
                .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Object> totalId = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total records are : " + totalId.size());
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> allName = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of the All store is : " + allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String objectName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of 5th store : " + objectName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<?> allInactiveStatus = response.extract().path("findAll{it.status=='inactive'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Status of Inactive: " + allInactiveStatus);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<?> allActiveStatusGender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Status of Active: " + allActiveStatusGender);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<?> allObjectWithGenderFemale = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allObjectWithGenderFemale);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<?> allEmailsOfStatusInactive = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Inactive : " + allEmailsOfStatusInactive);
        System.out.println("------------------End of Test---------------------------");

    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<?> allIdWithGenderMale = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allIdWithGenderMale);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<Integer> statusList = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Status list : " + statusList.size());
        System.out.println("------------------End of Test---------------------------");

    }

    //10. Get email of the object where name = Chandrakin Johar
    @Test
    public void test010() {
        List<HashMap<String,?>>  emailsOfChandrakinJohar = response.extract().path("findAll{it.name == 'Chandrakin Johar'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Chandrakin Johar : " + emailsOfChandrakinJohar);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get gender of id = 5393
    @Test
    public void test011() {
        List<?> getGenderOfId5393 = response.extract().path("findAll{it.id == '5393'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender Of Id 5393 : " + getGenderOfId5393);
        System.out.println("------------------End of Test---------------------------");
    }
}

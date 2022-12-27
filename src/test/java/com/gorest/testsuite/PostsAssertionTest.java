package com.gorest.testsuite;

import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .get("https://gorest.co.in/public/v2/posts?page=1&per_page=25%27")
                .then()
                .statusCode(200);

    }

    //1. Verify the if the total record is 25
    @Test
    public void test01() {
        List<Object> idTotal = response.extract().path("id");
        System.out.println(idTotal.size());
        response.body("idTotal.size()", equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Demoror cattus summisse cuppedia aro dolor tunc unde thema est suspendo adsum velit textor tener texo.”
    @Test
    public void test002() {
        response.body("[2].title", equalTo("Demoror cattus summisse cuppedia aro dolor tunc unde thema est suspendo adsum velit textor tener texo."));
    }

    //3. Check the single user_id in the Array list (5431)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5431));
    }

    //4. Check the multiple ids in the ArrayList (2670, 2662, 2652)
    @Test
    public void test004() {
        response.body("id", hasItems(2670, 2662, 2652));
    }

    /*5. Verify the body of userid = 5428 is equal “Conicio voluptatem utroque. Clarus suadeo vivo. Conscendo vulgaris theca. In tamisium appono. Degero callide omnis. Ceno placeat sortitus. Angustus constans adipisci. Deludo quod sint. Officiis absque dolorem. Usitas et adficio. Verbum ipsa antepono. Pel ullus defluo. Deserunt comitatus studio. Voluptas possimus cum. Deprecator turbo vel. Rerum ut aestus. Colloco vel avarus. Adulatio sol ubi. Defungo adfero natus. Ascisco fugit celo. Umerus stipes tamquam."”*/

    @Test
    public void test005() {
        response.body("[12].body", equalTo("Conicio voluptatem utroque. Clarus suadeo vivo. Conscendo vulgaris theca. In tamisium appono. Degero callide omnis. Ceno placeat sortitus. Angustus constans adipisci. Deludo quod sint. Officiis absque dolorem. Usitas et adficio. Verbum ipsa antepono. Pel ullus defluo. Deserunt comitatus studio. Voluptas possimus cum. Deprecator turbo vel. Rerum ut aestus. Colloco vel avarus. Adulatio sol ubi. Defungo adfero natus. Ascisco fugit celo. Umerus stipes tamquam."));
    }
}

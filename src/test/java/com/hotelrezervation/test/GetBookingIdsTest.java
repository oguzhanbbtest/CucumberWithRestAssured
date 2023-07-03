package com.hotelrezervation.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingIdsTest extends BaseTest {
    //Request
    //Response Controller
    // curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .log().all() // response'un tümünü log olarak atıyor
                .statusCode(200); //
        // status 200 dönüyor mu diye kontrol ediyoruz.
    }

    @Test
    public void getBookingByIDTest(){
        Response response = given(spec)
                .when()
                .get("/booking/"+ createBookingId());

        response
                .then()
                .statusCode(200);

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Boby",firstname);
        Assertions.assertEquals("Ram",lastname);
        Assertions.assertEquals(500,totalprice);
    }

}

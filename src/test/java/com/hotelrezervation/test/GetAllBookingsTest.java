package com.hotelrezervation.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTest extends BaseTest{

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
    public void getBookingWithFirstNameFilter(){
        int bookingId = createBookingId();

        spec.queryParam("firstname","Boby");
        spec.queryParam("lastname","Ram");

        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> filtedRezerve = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtedRezerve.contains(bookingId));
    }
}

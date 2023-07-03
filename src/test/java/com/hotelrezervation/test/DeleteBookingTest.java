package com.hotelrezervation.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest extends BaseTest{

    @Test
    public void deleteBookingTest(){
        //Create token
        //Create Rezerve
        //Delete requet

        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .when()                                                         // create booking
                .delete("/booking/" + createBookingId());

        response
                .then()
                .statusCode(201);

    }
}

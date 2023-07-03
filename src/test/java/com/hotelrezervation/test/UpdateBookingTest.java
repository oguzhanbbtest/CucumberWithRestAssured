package com.hotelrezervation.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{


    @Test
    public void updateBookingTest(){
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .body(bookingObject("Test","User",1000,false))
                .put("/booking/"+createBookingId());

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");
        boolean depositpaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Test",firstName);
        Assertions.assertEquals("User",lastName);
        Assertions.assertEquals(1000,totalprice);
        Assertions.assertFalse(depositpaid);

    }
}

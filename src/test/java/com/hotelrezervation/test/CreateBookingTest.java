package com.hotelrezervation.test;

import com.hotelrezervation.models.Booking;
import com.hotelrezervation.models.BookingDates;
import com.hotelrezervation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class CreateBookingTest extends BaseTest{


    @Test
    public void ping(){
        Response response = given(spec)
                .when()
                .get("/ping");

        response
                .then()
                .statusCode(201);

    }


    @Test
    public void createBookingTest(){


        Response response = createBooking();

        Assertions.assertEquals("Boby", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Ram", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(500,(Integer)response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));
    }

    @Test
    public void createBookingWithPojo(){
        // rezervasyon tarihleri için bir nesne oluşturduk
        // yeni bir rezervasyon oluşturmak için bir nesne oluşturduk ve içinde rezervasyon tarihi nesnesini yerleştirdik

        BookingDates bookingDates = new BookingDates("2023-07-20","2023-07-27");
        Booking booking = new Booking("Mahmut","Tuncer",500,false,bookingDates,"massage service");


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");


        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class); //response u bir pojo nesnesi içerisinde tutuyoruz.
        System.out.println(bookingResponse + " response kaydedildi");

        Assertions.assertEquals("Mahmut",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Tuncer",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("massage service",bookingResponse.getBooking().getAdditionalneeds());
    }


}

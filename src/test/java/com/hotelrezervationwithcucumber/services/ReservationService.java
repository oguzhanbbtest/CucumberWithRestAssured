package com.hotelrezervationwithcucumber.services;

import com.hotelrezervationwithcucumber.models.Auth;
import com.hotelrezervationwithcucumber.models.Booking;
import com.hotelrezervationwithcucumber.models.BookingDates;
import com.hotelrezervationwithcucumber.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest{


    public String generateToken(){
        Auth authBody = new Auth("admin", "password123");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);
        return response.jsonPath().getJsonObject("token");
    }

    public BookingResponse createBooking(){
        BookingDates bookingsDates = new BookingDates("2023-07-23","2023-07-30");
        Booking booking = new Booking("agile","tester", 2000, false,bookingsDates,"none");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteReserve(String token, int bookingId){
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" +token)
                .when()
                .delete("/booking/"+bookingId);

        response
                .then()
                .statusCode(201);

    }
}

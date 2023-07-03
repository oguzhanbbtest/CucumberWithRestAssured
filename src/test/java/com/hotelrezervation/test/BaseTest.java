package com.hotelrezervation.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {
    RequestSpecification spec;

    @BeforeEach
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }
    protected int createBookingId(){
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }
    public String createToken(){

        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response = given(spec).contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post("/auth");

        return response.jsonPath().getJsonObject("token");
    }
    protected Response createBooking(){
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Boby","Ram",500,true))
                .post("/booking");

        response
                .then()
                .statusCode(200);
        return response;
    }

    protected String bookingObject(String firstname, String lastname, int totalPrice, boolean depositPaid){

        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",depositPaid);

        // JSON body içerisinde ayrı bir body var bunuda önce o body içerisinde ki key'in isminde bir json body
        // tanımlayıp sonra tekrar mevcut body içerisinde value yerine koyuyor

        //body içerisine ekleyeceğimiz nesneyi oluşturup key ve value değerlerini dolduruyoruz.
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2023-07-05");
        bookingDates.put("checkout","2023-07-15");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds","Breakfast and Alcohhol services");

        return body.toString();
    }
}

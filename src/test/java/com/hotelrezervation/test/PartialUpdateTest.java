package com.hotelrezervation.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateTest extends BaseTest{

    @Test
    public void partialUpdateTest(){
        JSONObject body = new JSONObject();
        body.put("firstname","Mahmut");

        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());

        Assertions.assertEquals("Mahmut",response.jsonPath().getJsonObject("firstname"));
    }
}

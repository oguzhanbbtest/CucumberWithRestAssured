package com.hotelrezervationwithcucumber.steps;

import com.hotelrezervationwithcucumber.models.BookingResponse;
import com.hotelrezervationwithcucumber.services.ReservationService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("Kullanici yeni bir rezervasyon olusturuyor")
    public void requestStart(){
        reservationService = new ReservationService();
    }

    @Given("Kullanici rezervasyon icin gereken bilgileri veriyor")
    public void createAuth(){
        authKey = reservationService.generateToken();
    }

    @When("Kullanici otel rezervasyonu yaratiyor")
    public void createReservation(){
        bookingResponse = reservationService.createBooking();
    }

    @Then("Rezervasyon basarili sekilde olusturuldu")
    public void reservationAssertion(){
        Assertions.assertEquals("agile",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("tester",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(2000,bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals("none",bookingResponse.getBooking().getAdditionalneeds());
    }

    @And("Kullanici olusturulan rezervasyonu iptal ediyor")
    public void cancelReserve(){
        reservationService.deleteReserve(authKey, bookingResponse.getBookingid());
    }
}

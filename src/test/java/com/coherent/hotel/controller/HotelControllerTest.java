package com.coherent.hotel.controller;

import com.coherent.hotel.model.request.HotelRequest;
import com.coherent.hotel.model.response.HotelResponse;
import com.coherent.hotel.model.response.ResponseStatus;
import com.coherent.hotel.repository.Reservation;
import com.coherent.hotel.service.HotelService;
import com.coherent.hotel.util.HotelConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class HotelControllerTest {

    @InjectMocks
    HotelController controller;

    @Mock
    HotelService service;

    @Test
    void getAllReservationsTest() {
        HotelResponse hotelResponse = new HotelResponse();
        ResponseStatus status = new ResponseStatus();
        status.setCode(200);
        status.setMessage("Success");
        Set<Reservation> reservations = new HashSet<>();
        reservations.add(new Reservation());
        hotelResponse.setReservations(reservations);
        hotelResponse.setStatus(status);
        Mockito.when(service.getAllReservations()).thenReturn(hotelResponse);

        ResponseEntity<HotelResponse> response = controller.getAllReservations();

        Assert.isTrue(response.getBody().getReservations().size() == 1, "Size was not 1");
    }

    @Test
    void createReservationTest() {
        HotelResponse hotelResponse = new HotelResponse();
        ResponseStatus status = new ResponseStatus();
        status.setCode(201);
        status.setMessage(HotelConstants.CREATED_MESSAGE);
        hotelResponse.setStatus(status);

        Mockito.when(service.createReservation(Mockito.any())).thenReturn(hotelResponse);

        ResponseEntity<HotelResponse> response = controller.createReservation(new HotelRequest());

        Assert.isTrue(response.getBody().getStatus().getMessage().equals(HotelConstants.CREATED_MESSAGE)
                , "Message was not 'Reservation Created'");
    }

    @Test
    void updateReservationTest() {
        HotelResponse hotelResponse = new HotelResponse();
        ResponseStatus status = new ResponseStatus();
        status.setCode(200);
        status.setMessage(HotelConstants.UPDATED_MESSAGE);
        hotelResponse.setStatus(status);

        Mockito.when(service.updateReservation(Mockito.any(), Mockito.any())).thenReturn(hotelResponse);

        ResponseEntity<HotelResponse> response = controller.updateReservation("1", new HotelRequest());

        Assert.isTrue(response.getBody().getStatus().getMessage().equals(HotelConstants.UPDATED_MESSAGE),
                "Message was not 'Reservation Updated'");
    }
}
package com.coherent.hotel.service;

import com.coherent.hotel.model.exception.ValidationException;
import com.coherent.hotel.model.request.HotelRequest;
import com.coherent.hotel.model.request.HotelValidator;
import com.coherent.hotel.model.response.HotelResponse;
import com.coherent.hotel.repository.HotelRepository;
import com.coherent.hotel.util.HotelConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class HotelServiceTest {

    @InjectMocks
    HotelService service;

    @Spy
    HotelValidator validator;

    @Mock
    HotelRepository repository;

    @Test
    void getAllReservationsTest() {
        Mockito.when(repository.getAllReservations()).thenReturn(new HashSet<>());

        HotelResponse response = service.getAllReservations();

        Assert.isTrue(response.getReservations() != null, "Reservations object was null");
    }

    @Test
    void createReservationTest() {
        HotelRequest hotelRequest = new HotelRequest();
        hotelRequest.setClientFullName("Test Name");
        hotelRequest.setRoomNumber(100);
        hotelRequest.setReservationDates(List.of(LocalDate.now()));

        Mockito.doNothing().when(repository).createReservation(Mockito.any());

        HotelResponse response = service.createReservation(hotelRequest);

        Assert.isTrue(response.getStatus().getMessage().equals(HotelConstants.CREATED_MESSAGE),
                "Message was not 'Reservation Created'");
    }

    @Test
    void createReservationErrorTest() {
        HotelRequest hotelRequest = new HotelRequest();

        Mockito.doNothing().when(repository).createReservation(Mockito.any());

        HotelResponse response = service.createReservation(hotelRequest);

        Assert.isTrue(response.getStatus().getMessage().contains(HotelConstants.VALIDATION_ERROR),
                "Message was not 'Validation Error'");
    }

    @Test
    void updateReservationTest() throws ValidationException {
        HotelRequest hotelRequest = new HotelRequest();
        hotelRequest.setClientFullName("Test Name");
        hotelRequest.setRoomNumber(100);
        hotelRequest.setReservationDates(List.of(LocalDate.now()));

        Mockito.doNothing().when(repository).updateReservation(Mockito.any(), Mockito.any());

        HotelResponse response = service.updateReservation("1", hotelRequest);

        Assert.isTrue(response.getStatus().getMessage().equals(HotelConstants.UPDATED_MESSAGE),
                "Message was not 'Reservation Updated'");
    }

    @Test
    void updateReservationErrorTest() throws ValidationException {
        HotelRequest hotelRequest = new HotelRequest();

        Mockito.doNothing().when(repository).updateReservation(Mockito.any(), Mockito.any());

        HotelResponse response = service.updateReservation("1", hotelRequest);

        Assert.isTrue(response.getStatus().getMessage().contains(HotelConstants.VALIDATION_ERROR),
                "Message was not 'Validation Error'");
    }
}
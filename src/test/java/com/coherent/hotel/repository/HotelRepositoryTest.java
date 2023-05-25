package com.coherent.hotel.repository;

import com.coherent.hotel.model.exception.ValidationException;
import com.coherent.hotel.model.request.HotelRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class HotelRepositoryTest {

    @Spy
    HotelRepository repository;

    @Test
    void createAndGetAllReservationsTest() {
        repository.createReservation(getHotelRequest());

        Assert.notEmpty(repository.getAllReservations(), "Reservations Set was empty");
    }

    @Test
    void updateReservationTest() throws ValidationException {
        repository.createReservation(getHotelRequest());

        assertDoesNotThrow(() -> repository.updateReservation("1", getHotelRequest()));
    }

    private HotelRequest getHotelRequest() {
        HotelRequest hotelRequest = new HotelRequest();
        hotelRequest.setClientFullName("Test Name");
        hotelRequest.setRoomNumber(100);
        hotelRequest.setReservationDates(List.of(LocalDate.now()));

        return hotelRequest;
    }

}
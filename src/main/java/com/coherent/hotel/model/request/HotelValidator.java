package com.coherent.hotel.model.request;

import com.coherent.hotel.model.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class HotelValidator {
    public void validateHotelRequest(HotelRequest request) throws ValidationException {
        StringBuilder errors = new StringBuilder();

        if (request.getClientFullName().isBlank()) {
            errors.append("Client name cannot be blank. ");
        }

        if (request.getRoomNumber() == 0) {
            errors.append("Room number is mandatory. ");
        }

        if (request.getReservationDates() ==null || request.getReservationDates().isEmpty()) {
            errors.append("At least one date needs to be provided for a reservation. ");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toString());
        }

    }

    public void validateHotelRequest(String reservationID, HotelRequest request) throws ValidationException {
        try {
            Integer.parseInt(reservationID);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Reservation number not valid, please provide a number for it. ");
        }

        validateHotelRequest(request);

    }
}

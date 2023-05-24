package com.coherent.hotel.repository;

import com.coherent.hotel.model.exception.ValidationException;
import com.coherent.hotel.model.request.HotelRequest;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class HotelRepository {
    private static Set<Reservation> reservations = new HashSet<>();
    private static int idCounter = 0;

    public Set<Reservation> getAllReservations() {
        return reservations;
    }

    public void createReservation(HotelRequest request) {
        Reservation reservation = new Reservation();
        reservation.setId(++idCounter);
        reservation.setClientFullName(request.getClientFullName());
        reservation.setRoomNumber(request.getRoomNumber());
        reservation.setReservationDates(request.getReservationDates());

        //TODO: Add logic to avoid reservations for the same dates for a room

        reservations.add(reservation);
    }

    public void updateReservation(String reservationID, HotelRequest request) throws ValidationException {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId() == Integer.parseInt(reservationID))
                .findAny().orElse(null);

        if(reservation == null) {
            throw new ValidationException("Reservation not found");
        }

        reservations.removeIf(r -> r.getId() == Integer.parseInt(reservationID));

        reservation.setClientFullName(request.getClientFullName());
        reservation.setRoomNumber(request.getRoomNumber());
        reservation.setReservationDates(request.getReservationDates());

        //TODO: Add logic to avoid reservations for the same dates for a room

        reservations.add(reservation);
    }
}

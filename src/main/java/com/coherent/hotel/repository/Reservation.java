package com.coherent.hotel.repository;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reservation {
    private int id;
    private String clientFullName;
    private int roomNumber;
    private List<LocalDate> reservationDates;
}

package com.coherent.hotel.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelRequest {
    private String clientFullName;
    private int roomNumber;
    private List<LocalDate> reservationDates;
}

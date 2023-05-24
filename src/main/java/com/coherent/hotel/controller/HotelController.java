package com.coherent.hotel.controller;

import com.coherent.hotel.model.request.HotelRequest;
import com.coherent.hotel.model.response.HotelResponse;
import com.coherent.hotel.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Validated
@RequestMapping("/reservations")
public class HotelController {

    @Autowired
    private HotelService service;

    @ResponseBody
    @GetMapping()
    public ResponseEntity<HotelResponse> getAllReservations() {

        log.info("[HOTEL CONTROLLER] [GET] - Start");
        HotelResponse response = service.getAllReservations();

        log.info("[HOTEL CONTROLLER] [GET] - End");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus().code));
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<HotelResponse> createReservation(@RequestBody HotelRequest request) {

        log.info("[HOTEL CONTROLLER] [CREATE] - Start");
        HotelResponse response = service.createReservation(request);

        log.info("[HOTEL CONTROLLER] [CREATE] - End");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus().code));
    }

    @ResponseBody
    @PutMapping(value = "/{reservation}", produces = "application/json")
    public ResponseEntity<HotelResponse> updateReservation(@PathVariable("reservation")String reservationID,
                                                            @RequestBody()HotelRequest request) {

        log.info("[HOTEL CONTROLLER] [UPDATE] - Start");
        HotelResponse response = service.updateReservation(reservationID, request);

        log.info("[HOTEL CONTROLLER] [UPDATE] - End");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus().code));
    }
}

package com.coherent.hotel.service;

import com.coherent.hotel.model.exception.ValidationException;
import com.coherent.hotel.model.request.HotelRequest;
import com.coherent.hotel.model.request.HotelValidator;
import com.coherent.hotel.model.response.HotelResponse;
import com.coherent.hotel.model.response.ResponseStatus;
import com.coherent.hotel.repository.HotelRepository;
import com.coherent.hotel.util.HotelConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelService {

    @Autowired
    HotelRepository repository;

    @Autowired
    HotelValidator validator;

    public HotelResponse getAllReservations() {
        HotelResponse response = new HotelResponse();
        ResponseStatus status = new ResponseStatus();

        try {
            log.info("[HOTEL SERVICE] [GET] - Received request");

            response.setReservations(repository.getAllReservations());

            status.setCode(HttpStatus.OK.value());
            status.setMessage(HotelConstants.SUCCESS_MESSAGE);
        } catch (Exception ex) {
            log.error("[HOTEL SERVICE] [GET] - Error while obtaining all reservations: {}", ex.getMessage());

            status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status.setMessage(HotelConstants.SYSTEM_ERROR);
        }

        response.setStatus(status);
        return response;
    }

    public HotelResponse createReservation(HotelRequest request) {
        HotelResponse response = new HotelResponse();
        ResponseStatus status = new ResponseStatus();

        try {
            log.info("[HOTEL SERVICE] [CREATE] - Received request '{}'", request);

            validator.validateHotelRequest(request);

            repository.createReservation(request);

            status.setCode(HttpStatus.CREATED.value());
            status.setMessage(HotelConstants.CREATED_MESSAGE);
        } catch (ValidationException e) {
            log.error("[HOTEL SERVICE] [CREATE] - Validation error on provided request. {}", e.getMessage());

            status.setCode(HttpStatus.BAD_REQUEST.value());
            status.setMessage(HotelConstants.VALIDATION_ERROR + e.getMessage());
        } catch (Exception ex) {
            log.error("[HOTEL SERVICE] [CREATE] - Error while creating a reservation: {}", ex.getMessage());

            status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status.setMessage(HotelConstants.SYSTEM_ERROR);
        }

        response.setStatus(status);
        return response;
    }

    public HotelResponse updateReservation(String reservationID, HotelRequest request) {
        HotelResponse response = new HotelResponse();
        ResponseStatus status = new ResponseStatus();

        try {
            log.info("[HOTEL SERVICE] [UPDATE] - Received request '{}'", request);

            validator.validateHotelRequest(reservationID, request);

            repository.updateReservation(reservationID, request);

            status.setCode(HttpStatus.OK.value());
            status.setMessage(HotelConstants.UPDATED_MESSAGE);
        } catch (ValidationException e) {
            log.error("[HOTEL SERVICE] [UPDATE] - Validation error on provided request. {}", e.getMessage());

            status.setCode(HttpStatus.BAD_REQUEST.value());
            status.setMessage(HotelConstants.VALIDATION_ERROR + e.getMessage());
        } catch (Exception ex) {
            log.error("[HOTEL SERVICE] [UPDATE] - Error while updating a reservation: {}", ex.getMessage());

            status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status.setMessage(HotelConstants.SYSTEM_ERROR);
        }

        response.setStatus(status);
        return response;
    }

}

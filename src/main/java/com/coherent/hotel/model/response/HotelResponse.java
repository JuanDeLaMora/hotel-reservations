package com.coherent.hotel.model.response;

import com.coherent.hotel.repository.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse implements Serializable {

    @JsonProperty("status")
    private ResponseStatus status;
    @JsonProperty("reservations")
    private Set<Reservation> reservations;
}

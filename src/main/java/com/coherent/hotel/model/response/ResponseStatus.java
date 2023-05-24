package com.coherent.hotel.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatus implements Serializable {
    public int code;
    public String message;
    public final long runId = Instant.now().getEpochSecond();
}

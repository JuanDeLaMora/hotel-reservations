package com.coherent.hotel.model.exception;

import lombok.Data;

@Data
public class ServerError {
    private String errorType;
    private String errorMessage;
    private String errorSource;
}

package com.example.testuserservice.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
@RequiredArgsConstructor
public class ApiException {
    private final String message;
    private  final HttpStatus httpStatus;
    private final ZonedDateTime time;
}

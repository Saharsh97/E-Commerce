package com.scaler.productservice.controlleradvices;

import com.scaler.productservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class SecondaryControllerAdvice {

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponseDTO> handleTimeoutExceptions(){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("timeout occurred, cannot give back response, created at SecondaryAdviceLevel");
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
}

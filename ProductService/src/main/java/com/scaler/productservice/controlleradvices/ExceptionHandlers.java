package com.scaler.productservice.controlleradvices;

import com.scaler.productservice.dto.ArithmeticExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // between client and controller.
// global.
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    // any controller throws arithmetic exception, this is triggered
    public ResponseEntity<Void> handleArithmeticExceptions(){
        ArithmeticExceptionDTO arithmeticExceptionDTO = new ArithmeticExceptionDTO();
        arithmeticExceptionDTO.setMessage("something gone wrong arithmetically");
        ResponseEntity<Void> responseEntity = new ResponseEntity(
                arithmeticExceptionDTO,
                HttpStatus.OK
        );
        return responseEntity;
    }
}

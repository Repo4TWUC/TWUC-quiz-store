package com.twuc.twucstore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TsExceptionHandler {

  @ExceptionHandler({
      RuntimeException.class
  })
  public ResponseEntity<Error> handleException (Exception e) {
    Error error = new Error(e.getMessage());
    return ResponseEntity.badRequest().body(error);
  }
}


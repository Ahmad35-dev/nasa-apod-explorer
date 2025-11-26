package com.example.apod.exception;

//public class GlobalExceptionHandler {
//    
//}
//package com.example.apod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(HttpClientErrorException.class)
 public ResponseEntity<Map<String, String>> handleNasaError(HttpClientErrorException ex) {
     return ResponseEntity
             .status(ex.getStatusCode())
             .body(Map.of(
                     "source", "nasa-api",
                     "status", ex.getStatusCode().toString(),
                     "message", ex.getStatusText()
             ));
 }

 @ExceptionHandler(RuntimeException.class)
 public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
     return ResponseEntity
             .status(HttpStatus.INTERNAL_SERVER_ERROR)
             .body(Map.of(
                     "status", "500",
                     "message", ex.getMessage()
             ));
 }
}


package com.library.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //Tüm Controller sınıflarını dinleyen küresel bir hata yakalayıcıdır
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) //Yakalanacak spesifik hata türünü belirtir
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now()); //Hatanın oluştuğu anın tarih ve saat bilgisini ekler
        response.put("status", HttpStatus.BAD_REQUEST.value()); //HTTP durum kodunun sayısal değerini ekler
        response.put("error", "Bad Request"); //Hatanın türünü ekler
        response.put("message", ex.getMessage()); //Kod içerisinde fırlatılan özel hatanın mesajını alıp ekler
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

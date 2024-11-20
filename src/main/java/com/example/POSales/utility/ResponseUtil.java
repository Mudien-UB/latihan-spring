package com.example.POSales.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.POSales.dto.CommonResponse;

public class ResponseUtil {
    public static <T> ResponseEntity <CommonResponse<T>> buildResponse(HttpStatus status, String message, T data) {
        CommonResponse<T> response = new CommonResponse<>(status.value(), message, data);
        return ResponseEntity.status(status).body(response);
    }
    
}

package com.goshop.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

//retrun Data to frontend
@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}

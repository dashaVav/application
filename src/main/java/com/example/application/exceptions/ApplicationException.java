package com.example.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationException {
    private String error;
    private Integer status;
}

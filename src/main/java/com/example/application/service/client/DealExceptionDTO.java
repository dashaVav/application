package com.example.application.service.client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DealExceptionDTO {
    private String error;
    private Integer status;
}

package com.example.application.controller;

import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/application")
public interface ApplicationController {
    @PostMapping
    ResponseEntity<List<LoanOfferDTO>> application(@RequestBody LoanApplicationRequestDTO loanApplicationRequest);

    @PutMapping(path = "/offer")
    ResponseEntity<Void> offer(@RequestBody LoanOfferDTO loanOfferDTO);
}

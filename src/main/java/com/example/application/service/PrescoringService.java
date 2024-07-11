package com.example.application.service;


import com.example.application.dtos.LoanApplicationRequestDTO;

public interface PrescoringService {
    void validationOfLoanApplicationRequest(LoanApplicationRequestDTO loanApplicationRequest);
}

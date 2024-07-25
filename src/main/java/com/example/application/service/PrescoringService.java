package com.example.application.service;


import com.example.application.dto.LoanApplicationRequestDTO;

public interface PrescoringService {
    void validationOfLoanApplicationRequest(LoanApplicationRequestDTO loanApplicationRequest);
}

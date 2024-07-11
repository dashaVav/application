package com.example.application.service;

import com.example.application.dtos.LoanApplicationRequestDTO;
import com.example.application.dtos.LoanOfferDTO;

import java.util.List;

public interface DealService {
    List<LoanOfferDTO> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequest);

    Void applyOffer(LoanOfferDTO loanOfferDTO);
}

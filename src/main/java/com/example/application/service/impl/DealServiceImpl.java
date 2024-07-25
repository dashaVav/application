package com.example.application.service.impl;

import com.example.application.service.DealClient;
import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import com.example.application.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealClient dealClient;

    @Override
    public List<LoanOfferDTO> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequest) {
        return dealClient.application(loanApplicationRequest);
    }

    @Override
    public Void applyOffer(LoanOfferDTO loanOfferDTO) {
        return dealClient.offer(loanOfferDTO);
    }
}

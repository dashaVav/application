package com.example.application.controller.impl;

import com.example.application.controller.ApplicationController;
import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import com.example.application.service.ApplicationService;
import com.example.application.service.PrescoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationControllerImpl implements ApplicationController {
    private final PrescoringService prescoringService;
    private final ApplicationService applicationService;

    @Override
    public ResponseEntity<List<LoanOfferDTO>> application(LoanApplicationRequestDTO loanApplicationRequest) {
        prescoringService.validationOfLoanApplicationRequest(loanApplicationRequest);
        return ResponseEntity.ok(applicationService.getLoanOffers(loanApplicationRequest));
    }

    @Override
    public ResponseEntity<Void> offer(LoanOfferDTO loanOfferDTO) {
        return ResponseEntity.ok(applicationService.applyOffer(loanOfferDTO));
    }
}

package com.example.application.controller.impl;

import com.example.application.annotation.AuditAction;
import com.example.application.controller.ApplicationController;
import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import com.example.application.service.ApplicationService;
import com.example.application.service.PrescoringService;
import com.example.application.utils.LoggerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationControllerImpl implements ApplicationController {
    private final PrescoringService prescoringService;
    private final ApplicationService applicationService;

    @AuditAction(message = "Validation of loan application.")
    @Override
    public ResponseEntity<List<LoanOfferDTO>> application(LoanApplicationRequestDTO loanApplicationRequest) {
        log.info("/application requested. Body: {}...", LoggerUtils.cut(loanApplicationRequest, 100));
        prescoringService.validationOfLoanApplicationRequest(loanApplicationRequest);
        return ResponseEntity.ok(applicationService.getLoanOffers(loanApplicationRequest));
    }

    @Override
    public ResponseEntity<Void> offer(LoanOfferDTO loanOfferDTO) {
        log.info("/application/offer requested with id - {}", loanOfferDTO.getApplicationId());
        return ResponseEntity.ok(applicationService.applyOffer(loanOfferDTO));
    }
}

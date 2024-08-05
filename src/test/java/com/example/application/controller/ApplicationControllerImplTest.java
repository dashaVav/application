package com.example.application.controller;

import com.example.application.controller.impl.ApplicationControllerImpl;
import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import com.example.application.service.ApplicationService;
import com.example.application.service.PrescoringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerImplTest {
    @InjectMocks
    private ApplicationControllerImpl applicationController;
    @Mock
    private PrescoringService prescoringService;
    @Mock
    private ApplicationService applicationService;

    @Test
    void testApplicationEndpointOk() {
        BigDecimal amount = new BigDecimal(10000);
        Integer term = 6;

        LoanApplicationRequestDTO request = new LoanApplicationRequestDTO();
        request.setAmount(amount);
        request.setTerm(term);

        List<LoanOfferDTO> loanOffers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
            loanOfferDTO.setRequestedAmount(amount);
            loanOfferDTO.setTerm(term);
            loanOffers.add(loanOfferDTO);
        }

        when(applicationService.getLoanOffers(request)).thenReturn(loanOffers);

        ResponseEntity<List<LoanOfferDTO>> result = applicationController.application(request);
        verify(prescoringService, times(1)).validationOfLoanApplicationRequest(request);
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        assertThat(Objects.requireNonNull(result.getBody()).size()).isEqualTo(loanOffers.size());
        assertThat(result.getBody().getFirst().getRequestedAmount()).isEqualTo(amount);
        assertThat(result.getBody().getFirst().getTerm()).isEqualTo(term);
    }

    @Test
    void testOfferEndpointOk() {
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        doNothing().when(applicationService).applyOffer(loanOfferDTO);
        ResponseEntity<Void> result = applicationController.offer(loanOfferDTO);
        assertThat(result.getStatusCode().value()).isEqualTo(200);
    }
}

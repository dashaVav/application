package com.example.application.service;

import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "deal-client", url = "${feign-client.deal-client.base-url}")
public interface DealClient {
    @PostMapping(
            value = "${feign-client.deal-client.application-path}",
            consumes = "application/json"
    )
    List<LoanOfferDTO> application(LoanApplicationRequestDTO loanApplicationRequest);

    @PutMapping(
            value = "${feign-client.deal-client.offer-path}",
            consumes = "application/json"
    )
    Void offer(LoanOfferDTO loanOfferDTO);
}

package com.example.application.service;

import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "deal-client", url = "${feign-client.deal-client.base-url}")
public interface DealClient {
    @RequestMapping(method = RequestMethod.POST,
            value = "${feign-client.deal-client.application-path}",
            consumes = "application/json"
    )
    List<LoanOfferDTO> application(LoanApplicationRequestDTO loanApplicationRequest);

    @RequestMapping(method = RequestMethod.PUT,
            value = "${feign-client.deal-client.offer-path}",
            consumes = "application/json"
    )
    Void offer(LoanOfferDTO loanOfferDTO);
}

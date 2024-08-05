package com.example.application.service.client;

import com.example.application.exception.DealException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private final ConversionService conversionService;

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            DealExceptionDTO e = mapper.readValue(bodyIs, DealExceptionDTO.class);
            throw Objects.requireNonNull(conversionService.convert(e, DealException.class));
        } catch (IOException e) {
            Exception exception = errorDecoder.decode(methodKey, response);
            log.error("Deal service return exception: {}", exception.getMessage());
            return exception;
        }
    }
}

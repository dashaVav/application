package com.example.application.service.client;

import com.example.application.exception.DealException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

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
            return errorDecoder.decode(methodKey, response);
        }
    }
}

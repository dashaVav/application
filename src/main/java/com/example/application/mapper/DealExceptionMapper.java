package com.example.application.mapper;

import com.example.application.exception.DealException;
import com.example.application.service.client.DealExceptionDTO;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface DealExceptionMapper extends Converter<DealExceptionDTO, DealException> {
    DealException convert(@NotNull DealExceptionDTO dto);
}
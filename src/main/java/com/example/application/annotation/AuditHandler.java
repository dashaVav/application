package com.example.application.annotation;

import com.example.application.dto.AuditActionDTO;
import com.example.application.dto.ServiceDTO;
import com.example.application.dto.Type;
import com.example.application.service.AuditProducer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditHandler {
    private final AuditProducer auditProducer;

    @Around("@annotation(auditAction)")
    public Object logAround(ProceedingJoinPoint joinPoint, AuditAction auditAction) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        String format = "%s - %s Body - {%s}.";

        auditProducer.produceAuditAction(new AuditActionDTO(
                UUID.randomUUID(), Type.START, ServiceDTO.APPLICATION,
                String.format(format, LocalDateTime.now(), auditAction.message(), arg)
        ));

        try {
            Object result = joinPoint.proceed();

            auditProducer.produceAuditAction(new AuditActionDTO(
                    UUID.randomUUID(), Type.SUCCESS, ServiceDTO.APPLICATION,
                    String.format(format, LocalDateTime.now(), auditAction.message(), arg)
            ));

            return result;
        } catch (Exception e) {
            auditProducer.produceAuditAction(new AuditActionDTO(
                    UUID.randomUUID(), Type.FAILURE, ServiceDTO.APPLICATION,
                    String.format(format, LocalDateTime.now(), auditAction.message(), arg)
                            + " Exception message: " + e.getMessage()
            ));
            throw e;
        }
    }
}

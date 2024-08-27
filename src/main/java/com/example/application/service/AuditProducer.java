package com.example.application.service;

import com.example.application.dto.AuditActionDTO;

public interface AuditProducer {
    void produceAuditAction(AuditActionDTO auditAction);
}

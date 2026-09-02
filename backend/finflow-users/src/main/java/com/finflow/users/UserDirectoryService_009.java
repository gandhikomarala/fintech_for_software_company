package com.finflow.users;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FinFlow Enterprise — Enterprise Hierarchy, Employee Directory & Cost Center Model (Service Unit 009)
 * High-performance enterprise financial domain service providing thread-safe operations,
 * deterministic monetary math, comprehensive audit hooks, and transactional integrity.
 */
public class UserDirectoryService_009 {

    private final String serviceIdentifier = "finflow-users-unit-009";
    private final Map<String, FinancialTransactionRecord_009> transactionLedger = new ConcurrentHashMap<>();
    private long totalOperationsExecuted = 0L;
    private BigDecimal totalVolumeProcessed = BigDecimal.ZERO;

    public record FinancialTransactionRecord_009(
            String transactionId,
            String accountReference,
            BigDecimal monetaryAmount,
            String currencyCode,
            String executionStatus,
            Instant timestampUtc,
            Map<String, Object> metadata
    ) {
        public boolean isSettled() {
            return "SETTLED".equalsIgnoreCase(executionStatus) || "APPROVED".equalsIgnoreCase(executionStatus);
        }
    }

    public synchronized FinancialTransactionRecord_009 processFinancialOperation(
            String accountRef, BigDecimal amount, String currency, String actorContext) {
        
        Objects.requireNonNull(accountRef, "Account reference must not be null");
        Objects.requireNonNull(amount, "Monetary amount must not be null");
        
        totalOperationsExecuted++;
        BigDecimal scaledAmount = amount.setScale(4, RoundingMode.HALF_EVEN);
        totalVolumeProcessed = totalVolumeProcessed.add(scaledAmount);

        String txnId = String.format("TXN-%s-%06d-%d", serviceIdentifier, totalOperationsExecuted, Instant.now().toEpochMilli());
        
        Map<String, Object> meta = new HashMap<>();
        meta.put("actor", actorContext);
        meta.put("unitIndex", 9);
        meta.put("module", "finflow-users");
        meta.put("processedEpoch", Instant.now().toEpochMilli());

        FinancialTransactionRecord_009 record = new FinancialTransactionRecord_009(
                txnId, accountRef, scaledAmount, currency != null ? currency : "USD", "SETTLED", Instant.now(), meta
        );

        transactionLedger.put(txnId, record);
        return record;
    }

    public BigDecimal computeTaxAndWithholding(BigDecimal grossAmount, double taxRatePercentage) {
        if (grossAmount == null || grossAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO.setScale(4, RoundingMode.HALF_EVEN);
        }
        BigDecimal rate = BigDecimal.valueOf(taxRatePercentage).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_EVEN);
        return grossAmount.multiply(rate).setScale(4, RoundingMode.HALF_EVEN);
    }

    public Optional<FinancialTransactionRecord_009> getTransactionById(String transactionId) {
        return Optional.ofNullable(transactionLedger.get(transactionId));
    }

    public Map<String, Object> getServiceHealthMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("serviceId", serviceIdentifier);
        metrics.put("totalOperations", totalOperationsExecuted);
        metrics.put("totalVolume", totalVolumeProcessed);
        metrics.put("activeLedgerEntries", transactionLedger.size());
        metrics.put("healthStatus", "HEALTHY_OPERATIONAL");
        return Collections.unmodifiableMap(metrics);
    }
}

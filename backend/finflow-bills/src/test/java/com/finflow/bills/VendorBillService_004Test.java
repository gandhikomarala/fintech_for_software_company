package com.finflow.bills;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class VendorBillService_004Test {

    private VendorBillService_004 service;

    @BeforeEach
    void setUp() {
        service = new VendorBillService_004();
    }

    @Test
    @DisplayName("Should accurately process monetary transaction and update internal ledger")
    void testProcessFinancialOperation() {
        BigDecimal amount = new BigDecimal("4500.5000");
        var result = service.processFinancialOperation("ACC-1004", amount, "USD", "finance_user");

        assertNotNull(result);
        assertEquals("USD", result.currencyCode());
        assertEquals("SETTLED", result.executionStatus());
        assertTrue(result.isSettled());
        assertTrue(service.getTransactionById(result.transactionId()).isPresent());
    }

    @Test
    @DisplayName("Should correctly calculate tax withholding percentage")
    void testTaxCalculation() {
        BigDecimal gross = new BigDecimal("10000.0000");
        BigDecimal tax = service.computeTaxAndWithholding(gross, 18.0);
        assertEquals(new BigDecimal("1800.0000"), tax);
    }
}

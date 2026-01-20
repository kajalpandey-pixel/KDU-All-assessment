package org.example.event;

public record PaymentMessage(
        String transactionId,
        String bookingId,
        double amount
) {}

package org.example.event;

public record TicketBookedEvent(
        String bookingId,
        String userId,
        String trainId,
        String seatNo,
        String phone
) {}


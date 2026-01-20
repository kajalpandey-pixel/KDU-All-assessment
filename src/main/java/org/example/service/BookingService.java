package org.example.service;

import org.example.broker.DummyBroker;
import org.example.dto.BookingRequest;
import org.example.event.BookingMessage;
import org.example.event.TicketBookedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final DummyBroker broker;

    public BookingService(DummyBroker broker) {
        this.broker = broker;
    }

    public String book(BookingRequest request) {
        String bookingId = "BKG-" + System.currentTimeMillis();

        // Part 2: queue message (validated/processed with retry + DLQ)
        broker.publishBookingMessage(new BookingMessage(bookingId, request.getAge()));

        // Part 1: publish event (inventory + notification consumers)
        publishTicketBookedAsync(new TicketBookedEvent(
                bookingId,
                request.getUserId(),
                request.getTrainId(),
                request.getSeatNo(),
                request.getPhone()
        ));

        return bookingId;
    }

    @Async
    public void publishTicketBookedAsync(TicketBookedEvent event) {
        broker.publishTicketBooked(event);
    }
}

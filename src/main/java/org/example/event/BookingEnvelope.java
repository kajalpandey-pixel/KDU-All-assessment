package org.example.event;

public class BookingEnvelope {
    private final BookingMessage message;
    private int attempt;

    public BookingEnvelope(BookingMessage message) {
        this.message = message;
        this.attempt = 0;
    }

    public BookingMessage getMessage() {
        return message;
    }

    public int incrementAndGetAttempt() {
        attempt++;
        return attempt;
    }

    public int getAttempt() {
        return attempt;
    }
}

package org.example.consumer;

import jakarta.annotation.PostConstruct;
import org.example.broker.DummyBroker;
import org.example.event.BookingEnvelope;
import org.example.event.BookingMessage;
import org.springframework.stereotype.Component;

@Component
public class BookingConsumer {

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 700;

    private final DummyBroker broker;

    public BookingConsumer(DummyBroker broker) {
        this.broker = broker;
    }

    @PostConstruct
    public void startLoop() {
        Thread t = new Thread(this::runLoop, "booking-consumer-thread");
        t.setDaemon(true);
        t.start();
    }

    private void runLoop() {
        while (true) {
            BookingEnvelope env = broker.pollBookingQueue();

            if (env == null) {
                sleep(200);
                continue;
            }

            BookingMessage msg = env.getMessage();
            int attempt = env.incrementAndGetAttempt();

            System.out.println("[BookingConsumer] Processing bookingId=" + msg.bookingId()
                    + " age=" + msg.age() + " attempt=" + attempt);

            try {
                process(msg);
                System.out.println("[BookingConsumer] SUCCESS bookingId=" + msg.bookingId());
            } catch (RuntimeException ex) {
                System.out.println("[BookingConsumer] FAILED bookingId=" + msg.bookingId()
                        + " attempt=" + attempt + " reason=" + ex.getMessage());

                if (attempt >= MAX_RETRIES) {
                    broker.sendToDlq(env);
                    System.out.println("[DLQ] Moved bookingId=" + msg.bookingId()
                            + " to booking-error-queue. DLQ size=" + broker.dlqSize());
                } else {
                    sleep(RETRY_DELAY_MS);
                    broker.requeueBookingEnvelope(env); // IMPORTANT: keeps attempt count
                }
            }
        }
    }

    private void process(BookingMessage msg) {
        // Poison pill rule
        if (msg.age() < 0) {
            throw new RuntimeException("Age cannot be negative");
        }

        // Simulate processing
        System.out.println("[BookingConsumer] Booking validated for bookingId=" + msg.bookingId());
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

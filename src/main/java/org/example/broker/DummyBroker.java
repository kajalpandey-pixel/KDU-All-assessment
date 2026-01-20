package org.example.broker;

import org.example.event.BookingEnvelope;
import org.example.event.BookingMessage;
import org.example.event.TicketBookedEvent;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
@Component
public class DummyBroker {

    //this is part 1
    //
    private final List<Consumer<TicketBookedEvent>> subscribers = new CopyOnWriteArrayList<>();

    public void subscribe(Consumer<TicketBookedEvent> consumer) {
        subscribers.add(consumer);
    }

    public void publish(TicketBookedEvent event) {
        for (Consumer<TicketBookedEvent> consumer : subscribers) {
            consumer.accept(event);
        }
    }


    // part -2

    private final Queue<BookingEnvelope> bookingQueue = new ConcurrentLinkedQueue();
    private final Queue<BookingEnvelope> bookingErrorQueue = new ConcurrentLinkedQueue(); // DLQ

    public void publishBookingMessage(BookingMessage message) {
        bookingQueue.add(new BookingEnvelope(message));
    }

    public BookingEnvelope pollBookingQueue() {
        return bookingQueue.poll();
    }

    public void requeueBookingEnvelope(BookingEnvelope env) {
        bookingQueue.add(env);
    }

    public void sendToDlq(BookingEnvelope env) {
        bookingErrorQueue.add(env);
    }

    public List<BookingEnvelope> getDlqMessages() {
        return List.copyOf(bookingErrorQueue);
    }

    public int dlqSize() {
        return bookingErrorQueue.size();
    }
    private final List<Consumer<TicketBookedEvent>> ticketBookedSubscribers
            = new CopyOnWriteArrayList<>();

    public void subscribeTicketBooked(Consumer<TicketBookedEvent> consumer) {
        ticketBookedSubscribers.add(consumer);
    }

    public void publishTicketBooked(TicketBookedEvent event) {
        for (Consumer<TicketBookedEvent> consumer : ticketBookedSubscribers) {
            consumer.accept(event);
        }
    }
}

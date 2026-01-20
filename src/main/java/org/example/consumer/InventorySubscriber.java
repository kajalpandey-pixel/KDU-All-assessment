package org.example.consumer;

import jakarta.annotation.PostConstruct;
import org.example.broker.DummyBroker;
import org.example.event.TicketBookedEvent;
import org.springframework.stereotype.Component;

@Component
public class InventorySubscriber {

    private final DummyBroker broker;

    public InventorySubscriber(DummyBroker broker) {
        this.broker = broker;
    }

    @PostConstruct
    public void init() {
        broker.subscribeTicketBooked(this::handle);
    }

    private void handle(TicketBookedEvent event) {
        System.out.println("[Inventory] Seat occupied bookingId=" + event.bookingId()
                + " seatNo=" + event.seatNo());
    }
}

package org.example.controller;

import org.example.dto.BookingRequest;
import org.example.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public String book(@RequestBody BookingRequest request) {
        String bookingId = bookingService.book(request);
        return "Booking in Progress: " + bookingId;
    }
}

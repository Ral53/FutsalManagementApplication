package com.example.futsalmanagement.controller;

import com.example.futsalmanagement.entity.Booking;
import com.example.futsalmanagement.pojo.BookingPojo;
import com.example.futsalmanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok().body(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<BookingPojo> createBooking(@RequestBody BookingPojo bookingPojo) {
        BookingPojo createdBooking = bookingService.createBooking(bookingPojo);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Integer id, @RequestBody BookingPojo bookingPojo) {
        boolean updated = bookingService.updateBooking(id, bookingPojo);
        if (updated) {
            return ResponseEntity.ok("Booking updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable Integer id) {
        boolean deleted = bookingService.deleteBookingById(id);
        if (deleted) {
            return ResponseEntity.ok("Booking deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }
}

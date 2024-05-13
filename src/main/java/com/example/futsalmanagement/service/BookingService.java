package com.example.futsalmanagement.service;

import com.example.futsalmanagement.entity.Booking;
import com.example.futsalmanagement.pojo.BookingPojo;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(Integer id);

    BookingPojo createBooking(BookingPojo bookingPojo);

    boolean updateBooking(Integer id, BookingPojo bookingPojo);

    boolean deleteBookingById(Integer id);

}

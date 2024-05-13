package com.example.futsalmanagement.service.impl;

import com.example.futsalmanagement.entity.Booking;
import com.example.futsalmanagement.pojo.BookingPojo;
import com.example.futsalmanagement.repository.BookingRepository;
import com.example.futsalmanagement.service.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public BookingPojo createBooking(BookingPojo bookingPojo) {
        Booking booking = new Booking();
        booking.setStartTime(bookingPojo.getStartTime());
        booking.setEndTime(bookingPojo.getEndTime());
        booking.setStatus(bookingPojo.getStatus());
        booking.setPrice(bookingPojo.getPrice());
        Booking savedBooking = bookingRepository.save(booking);
        return convertToPojo(savedBooking);
    }

    @Override
    public boolean updateBooking(Integer id, BookingPojo bookingPojo) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setStartTime(bookingPojo.getStartTime());
            existingBooking.setEndTime(bookingPojo.getEndTime());
            existingBooking.setStatus(bookingPojo.getStatus());
            existingBooking.setPrice(bookingPojo.getPrice());
            bookingRepository.save(existingBooking);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBookingById(Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper methods to convert between Entity and Pojo
    private Booking convertToEntity(BookingPojo bookingPojo) {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingPojo, booking);
        return booking;
    }

    private BookingPojo convertToPojo(Booking booking) {
        BookingPojo bookingPojo = new BookingPojo();
        BeanUtils.copyProperties(booking, bookingPojo);
        return bookingPojo;
    }
}

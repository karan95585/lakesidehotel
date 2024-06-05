package com.lakesidehotel.backend.service;

import com.lakesidehotel.backend.model.BookedRoom;

import java.util.List;


public interface IBookingService {
    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    void cancelBooking(Long bookingId);

    List<BookedRoom> getAllBookings();

}

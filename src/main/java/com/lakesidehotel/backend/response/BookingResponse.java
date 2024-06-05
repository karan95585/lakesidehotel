package com.lakesidehotel.backend.response;

import com.lakesidehotel.backend.model.Room;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BookingResponse {
    private Long bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private  String guestFullName;
    private String guestEmail;
    private int NumOfAdults;
    private int NumOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;
    private RoomResponse room;

    public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }


}

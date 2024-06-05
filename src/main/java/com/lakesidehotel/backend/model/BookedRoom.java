package com.lakesidehotel.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private  String guestFullName;
    private String guestEmail;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuest;


    @Setter
    private String bookingConfirmationCode;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn
    private Room room;


    public void calculateTotalNumOfGuest(){
        this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNumOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNumOfGuest();
    }
}

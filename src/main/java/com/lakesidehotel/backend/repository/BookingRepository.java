package com.lakesidehotel.backend.repository;

import com.lakesidehotel.backend.model.BookedRoom;
import com.lakesidehotel.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookedRoom, Long> {
    List<BookedRoom> findByRoomId(Long roomId);

    BookedRoom findBybookingConfirmationCode(String confirmationCode);
}

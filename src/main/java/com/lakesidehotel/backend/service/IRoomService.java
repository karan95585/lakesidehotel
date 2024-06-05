package com.lakesidehotel.backend.service;

import com.lakesidehotel.backend.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;

    List<Room> getAllRooms();

    byte[] getRoomPohotoByRoomId(Long roomId) throws SQLException;

    void deleteRoom(Long roomId);

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws SQLException;

    Optional<Room> getRoomById(Long roomId);

    List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    List<String> getAllRoomTypes();
}

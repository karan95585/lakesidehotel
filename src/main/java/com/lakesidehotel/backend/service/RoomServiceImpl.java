package com.lakesidehotel.backend.service;

import com.lakesidehotel.backend.exception.ResourceNotFoundException;
import com.lakesidehotel.backend.model.Room;
import com.lakesidehotel.backend.repository.RoomRepository;
import com.lakesidehotel.backend.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    @Autowired
     RoomRepository roomRepository;

    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException {

        Room room= new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!photo.isEmpty()){
            byte[] bytes=photo.getBytes();
            Blob blob=new SerialBlob(bytes);

            room.setPhoto(blob);

        }
        System.out.println("Room");
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public byte[] getRoomPohotoByRoomId(Long roomId) throws SQLException {
        Optional<Room> theRoom = roomRepository.findById(roomId);
        if(theRoom.isEmpty()){
            throw new ResourceAccessException("Soory, Room Not Found!!");
        }
        Blob photoBlob = theRoom.get().getPhoto();
        if(photoBlob != null){
            return photoBlob.getBytes(1,(int) photoBlob.length());

        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> theRoom = roomRepository.findById(roomId);
        if(theRoom.isPresent()){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws SQLException {

        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found"));

        if(roomType != null)room.setRoomType(roomType);
        if(roomPrice != null)room.setRoomPrice(roomPrice);
        if(photoBytes!= null && photoBytes.length>0){
            try{
                room.setPhoto(new SerialBlob(photoBytes));
            } catch (SerialException e) {
                throw new RuntimeException(e);
            }
        }
        return roomRepository.save(room);

    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {

        return Optional.of(roomRepository.findById(roomId).get());
    }

    @Override
    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return roomRepository.findAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType);

    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }


}

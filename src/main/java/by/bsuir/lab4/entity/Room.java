package by.bsuir.lab4.entity;

import java.util.Objects;

public class Room implements Entity{
    public static final String TABLE_NAME = " `room` ";
    public static final String ID = "id";
    public static final String ROOM_NUMBER = "room_number";
    public static final String OCCUPIED = "occupied";
    private Integer id;
    private String roomNumber;
    private Boolean occupied;

    public Room(Integer id, String roomNumber, Boolean occupied) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.occupied = occupied;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id) && roomNumber.equals(room.roomNumber) && occupied.equals(room.occupied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, occupied);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", occupied=" + occupied +
                '}';
    }
}

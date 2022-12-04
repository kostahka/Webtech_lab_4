package by.bsuir.lab4.repository;

import by.bsuir.lab4.repository.impl.RoomRepository;
import by.bsuir.lab4.repository.impl.UserRepository;

public class RepositoryFactory {
    private static final RoomRepository roomRepository = new RoomRepository();
    private static final UserRepository userRepository = new UserRepository();

    public static RoomRepository getRoomRepository(){
        return roomRepository;
    }
    public static UserRepository getUserRepository(){
        return userRepository;
    }
}

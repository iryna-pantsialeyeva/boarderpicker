package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserByID(int userID) throws ObjectPersistenceException;

    UserDTO saveUser(User user) throws IllegalArgumentException;

    void deleteUserByID(int id) throws ObjectPersistenceException;

    UserDTO updateUser(int userID, User user) throws ObjectPersistenceException;
}

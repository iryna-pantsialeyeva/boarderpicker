package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserByID(int userID);

    UserDTO saveUser(User user) throws IllegalArgumentException;

    void deleteUserByID(int id);

    UserDTO updateUser(int userID, User user);
}

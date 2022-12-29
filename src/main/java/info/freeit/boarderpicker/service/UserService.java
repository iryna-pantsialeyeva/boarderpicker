package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectAlreadyExistException;
import info.freeit.boarderpicker.exception.ObjectNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserByID(int userID) throws ObjectNotFoundException;

    UserDTO saveUser(User user) throws ObjectAlreadyExistException;

    void deleteUserByID(int id) throws ObjectNotFoundException;
}

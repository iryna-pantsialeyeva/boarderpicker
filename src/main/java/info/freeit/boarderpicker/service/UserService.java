package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.dto.UserDTOForSaveUpdate;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> getAllUsers();

    UserDTO getUserByID(int userID);

    UserDTO saveUser(UserDTOForSaveUpdate userDTO) throws IllegalArgumentException;

    void deleteUserByID(int id);

    UserDTO updateUser(int userID, UserDTOForSaveUpdate userDTO);

    void setAdminAuthority(int userID);

    void banUser(int id);

    void unbanUser(int id);
}

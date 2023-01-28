package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserByID(int userID);

    User saveUser(User user) throws IllegalArgumentException;

    void deleteUserByID(int id);

    User updateUser(int id, User user);

    void setAdminAuthority(int userID);

    void banUser(int id);

    void unbanUser(int id);
}

package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.Role;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;
import info.freeit.boarderpicker.repository.RoleRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = Streamable.of(userRepository.findAll()).toList();
        return users.stream().map(user -> UserDTO.fromUser(user)).toList();
    }

    @Override
    public UserDTO getUserByID(int userID) throws ObjectPersistenceException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ObjectPersistenceException(String.format("User with id %d not found", userID)));
        return UserDTO.fromUser(user);
    }

    @Override
    public UserDTO saveUser(User user) throws IllegalArgumentException {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException(String.format("User %s already exists", user.getUserName()));
        }
        Role role = roleRepository.findByRole("User");
        user.getRoles().add(role);
        return UserDTO.fromUser(userRepository.save(user));
    }

    @Override
    public void deleteUserByID(int id) throws ObjectPersistenceException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ObjectPersistenceException(String.format("User with id %d not found", id));
        }
    }

    @Override
    public UserDTO updateUser(int id, User user) throws ObjectPersistenceException {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new ObjectPersistenceException(String.format("User with id %d not found", id)));
        userFromDB.setUserName(user.getUserName());
        userFromDB.setPassword(user.getPassword());
        userRepository.save(userFromDB);
        return UserDTO.fromUser(userFromDB);
    }
}

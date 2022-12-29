package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.Role;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectAlreadyExistException;
import info.freeit.boarderpicker.exception.ObjectNotFoundException;
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
    public UserDTO getUserByID(int userID) throws ObjectNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with id %d not found", userID)));
        return UserDTO.fromUser(user);
    }

    @Override
    public UserDTO saveUser(User user) throws ObjectAlreadyExistException {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new ObjectAlreadyExistException(String.format("User %s already exists", user.getUserName()));
        }
        Role role = roleRepository.findByRole("User");
        user.addRole(role);
        return UserDTO.fromUser(userRepository.save(user));
    }

    @Override
    public void deleteUserByID(int id) throws ObjectNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(String.format("User with id %d not found", id));
        }
    }
}

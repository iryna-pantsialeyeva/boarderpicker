package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.dto.UserDTOForSaveUpdate;
import info.freeit.boarderpicker.entity.Role;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.repository.RoleRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return Streamable.of(userRepository.findAll()).stream().map(user -> UserDTO.fromUser(user)).toList();
    }

    @Override
    public UserDTO getUserByID(int userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", userID)));
        return UserDTO.fromUser(user);
    }

    @Override
    public UserDTO saveUser(UserDTOForSaveUpdate userDTO) throws IllegalArgumentException {
        if (userRepository.findByUserName(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException(String.format("User %s already exists", userDTO.getUsername()));
        }
        Role role = roleRepository.findByRole("User");
        User user = UserDTOForSaveUpdate.fromUserDTOForSaveUpdate(userDTO);
        user.setActive(true);
        user.addRole(role);
        return UserDTO.fromUser(userRepository.save(user));
    }

    @Override
    public void deleteUserByID(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("User with id %d not found", id));
        }
    }

    @Override
    public UserDTO updateUser(int id, UserDTOForSaveUpdate userDTO) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
        userFromDB.setUserName(userDTO.getUsername());
        userFromDB.setPassword(userDTO.getPassword());
        userFromDB.setEmail(userDTO.getEmail());
        userRepository.save(userFromDB);
        return UserDTO.fromUser(userFromDB);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String
                .format("User %s is not found", username)));
        return UserDTO.getUserDetails(user);
    }
}

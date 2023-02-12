package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.entity.Role;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.repository.RoleRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return Streamable.of(userRepository.findAll()).toList();
    }

    @Override
    public User getUserByID(int userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", userID)));
    }

    @Override
    public User saveUser(User user) throws IllegalArgumentException {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new IllegalArgumentException(String.format("User %s already exists", user.getUserName()));
        }
        Role role = roleRepository.findByRole("User");
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
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
    public User updateUser(int id, User user) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
        userFromDB.setUserName(user.getUserName());
        userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDB.setEmail(user.getEmail());
        userRepository.save(userFromDB);
        return userFromDB;
    }

    @Override
    public void setAdminAuthority(int userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", userID)));
        Role role = roleRepository.findByRole("Admin");
        Set<Role> roles = user.getRoles();
        if (!roles.contains(role)) {
            roles.add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void banUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
        user.setActive(false);
        userRepository.save(user);
    }

    public void unbanUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String
                .format("User %s is not found", username)));
        return BPUserDetails.getUserDetails(user);
    }
}

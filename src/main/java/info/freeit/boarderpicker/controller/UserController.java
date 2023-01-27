package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.dto.UpdateUserDto;

import info.freeit.boarderpicker.mapper.UserMapper;
import info.freeit.boarderpicker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('User', 'Admin')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(userMapper::fromUser).toList();
    }

    @GetMapping("/{userID}")
    public UserDTO getUserByID(@PathVariable int userID) {
        return userMapper.fromUser(userService.getUserByID(userID));
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UpdateUserDto userDTO) throws IllegalArgumentException {
        return userMapper.fromUser(userService.saveUser(userDTO));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteUser(@RequestParam int id) {
        userService.deleteUserByID(id);
    }

    @PutMapping("/{userID}")
    public UserDTO updateUser(@PathVariable int userID, @RequestBody UpdateUserDto userDTO,
                              @AuthenticationPrincipal BPUserDetails user) {
        return userMapper.fromUser(userService.updateUser(userID, userDTO, user));
    }
}

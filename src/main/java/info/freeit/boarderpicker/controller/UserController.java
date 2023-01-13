package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.dto.UpdateUserDto;

import info.freeit.boarderpicker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public UserDTO getUserByID(@PathVariable int userID) {
        return userService.getUserByID(userID);
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UpdateUserDto userDTO) throws IllegalArgumentException {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteUser(@RequestParam int id) {
        userService.deleteUserByID(id);
    }

    @PutMapping("/{userID}")
    public UserDTO updateUser(@PathVariable int userID, @RequestBody UpdateUserDto userDTO,
                              @AuthenticationPrincipal BPUserDetails user) {
        return userService.updateUser(userID, userDTO, user);
    }
}

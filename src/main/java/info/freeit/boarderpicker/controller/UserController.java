package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.dto.UserDTOForSaveUpdate;
import info.freeit.boarderpicker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public UserDTO getUserByID(@PathVariable int userID) {
        return userService.getUserByID(userID);
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTOForSaveUpdate userDTO) throws IllegalArgumentException {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam int id) {
        userService.deleteUserByID(id);
    }

    @PutMapping("/{userID}")
    public UserDTO updateUser(@PathVariable int userID, @RequestBody UserDTOForSaveUpdate userDTO) {
        return userService.updateUser(userID, userDTO);
    }
}

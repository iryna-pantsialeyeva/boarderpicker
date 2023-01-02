package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;
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
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public UserDTO getUserByID(@PathVariable int userID) throws ObjectPersistenceException {
        return userService.getUserByID(userID);
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody User user) throws IllegalArgumentException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam int id) throws ObjectPersistenceException {
       userService.deleteUserByID(id);
    }

    @PutMapping("/update/{userID}")
    public UserDTO updateUser(@PathVariable int userID, @RequestBody User user) throws ObjectPersistenceException {
        return userService.updateUser(userID, user);
    }
}

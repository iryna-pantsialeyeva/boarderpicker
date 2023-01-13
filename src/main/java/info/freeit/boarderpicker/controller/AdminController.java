package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/set_admin")
    public void setAdminAuthority(@RequestParam int userID) {
        userService.setAdminAuthority(userID);
    }

    @PutMapping("/ban_user")
    public void banUser(@RequestParam int id) {
        userService.banUser(id);
    }

    @PutMapping("/unban_user")
    public void unbanUser(@RequestParam int id) {
        userService.unbanUser(id);
    }
}

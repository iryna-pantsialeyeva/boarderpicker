package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

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

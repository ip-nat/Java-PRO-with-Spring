package org.ippnat.controller;

import org.ippnat.entity.User;
import org.ippnat.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @PostMapping("/create")
    public User create(@RequestParam("username") String username) {
        return service.createUser(username);
    }

    @DeleteMapping("{userId}/delete")
    public void delete(@PathVariable Long userId) {
        service.deleteUser(userId);
    }


}

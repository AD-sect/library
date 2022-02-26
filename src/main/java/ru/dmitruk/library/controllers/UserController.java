package ru.dmitruk.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.models.User;
import ru.dmitruk.library.repository.UserRepository;
import ru.dmitruk.library.services.UserService;

import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/about")
//    @PreAuthorize("hasAuthority('users:read')")
    public String getUser(Authentication auth) {

        if (!( auth == null)) {
            return "Username is: " + auth.getName() +
                    "\n  authorities: "+ auth.getAuthorities();
        }else {
            return "User is not logged in";
            }
        }

    @GetMapping("/users")
//    @PreAuthorize("hasAuthority('users:write')")
    public Iterable<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/user/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public User createUser(@PathVariable Long id, @RequestBody User user) {
        return userService.createUser(id, user);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:write')")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}




package ru.dmitruk.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitruk.library.models.User;
import ru.dmitruk.library.repository.UserRepository;

import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    UserRepository userRepository;

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
    @PreAuthorize("hasAuthority('users:write')")
    public Iterable<User> getUsers() {
        Iterable<User> user = userRepository.findAll();
        return user;
    }
}




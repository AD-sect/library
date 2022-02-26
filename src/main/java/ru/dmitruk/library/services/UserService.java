package ru.dmitruk.library.services;

import ru.dmitruk.library.models.Book;
import ru.dmitruk.library.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Iterable<User> getAll();
    public User createUser(Long id, User user);
    public User updateUserById(Long id, User user);

}

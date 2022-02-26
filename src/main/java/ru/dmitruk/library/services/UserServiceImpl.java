package ru.dmitruk.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitruk.library.models.User;
import ru.dmitruk.library.repository.UserRepository;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(Long id, User user) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setPassword(user.getPassword());
                    u.setRoles(user.getRoles());
                    u.setStatus(user.getStatus());
                    return userRepository.save(u);
                }).orElse(null);
    }
}

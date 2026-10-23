package com.example.Praktika3_Ilin.service;

import com.example.Praktika3_Ilin.repository.UserRepository;
import com.example.Praktika3_Ilin.model.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с именем " + user.getUsername() + " уже существует!");
        }

        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }

        return false;
    }
}

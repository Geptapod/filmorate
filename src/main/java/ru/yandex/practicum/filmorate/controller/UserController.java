package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, User> users = new HashMap<>();
    private long newId = 1;

    @GetMapping
    public Collection<User> getAllFilms() {
        log.debug("Current users number: {}", users.size());
        return users.values();
    }

    @SneakyThrows
    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        User newUser = updateName(user);
        newUser.setId(generateNewId());
        users.put(newUser.getId(), newUser);
        log.debug("User added by id: {}", newUser.getId());
        return newUser;
    }

    @SneakyThrows
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        User newUser = user;
        if (!users.containsKey(newUser.getId())) {
            throw new ValidationException("User not exist by id: " + newUser.getId());
        } else {
            newUser = updateName(newUser);
            users.put(newUser.getId(), newUser);
            log.debug("User added by id: {}.", newUser.getId());
        }
        return newUser;
    }

    private User updateName(User user) {
        if (user.getName() == null
                || user.getName().equals("")
                || user.getName().equals("^[\\s]+")
        ) {
            user.setName(user.getLogin());
        }
        return user;
    }

    public long generateNewId() {
        return newId++;
    }
}



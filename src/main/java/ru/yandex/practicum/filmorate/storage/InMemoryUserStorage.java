package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private long newId = 1;

    public ArrayList<User> getAllUsers() {
        log.debug("Current users number: {}", users.size());
        return new ArrayList<>(users.values());
    }

    public User getUserById(Long id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new UserNotFoundException(String.format("User by id: %d - doesn't exist.", id));
        }
    }

    public User addUser(User user) {
        User newUser = updateName(user);
        newUser.setId(generateNewId());
        users.put(newUser.getId(), newUser);
        log.debug("User added by id: {}", newUser.getId());
        return newUser;
    }

    public User updateUser(User user) throws UserNotFoundException {
        User newUser = user;
        if (!users.containsKey(newUser.getId())) {
            throw new UserNotFoundException("User not exist by id: " + newUser.getId());
        } else {
            newUser = updateName(newUser);
            users.put(newUser.getId(), newUser);
            log.debug("User added by id: {}.", newUser.getId());
        }
        return newUser;
    }

    public User updateName(User user) {
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

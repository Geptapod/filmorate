package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;

public interface UserStorage {
    ArrayList<User> getAllUsers();

    User getUserById(Long id) throws UserNotFoundException;

    User addUser(User user);

    User updateUser(User user) throws UserNotFoundException;

    User updateName(User user);

    long generateNewId();
}

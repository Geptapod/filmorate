package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage inMemoryUserStorage;

    public User addFriend(Long id, Long friendId) throws UserNotFoundException {
        User user1 = inMemoryUserStorage.getUserById(friendId);
        User user2 = inMemoryUserStorage.getUserById(id);
        if (user1 != null && user2 != null) {
            user1.addFriend(id);
            user2.addFriend(friendId);
        }
        return user2;
    }

    public User deleteFriend(Long id, Long friendId) throws UserNotFoundException {
        User user = inMemoryUserStorage.getUserById(id);
        if (user != null) {
            user.deleteFriend(friendId);
        }
        return user;
    }

    public List<User> getAllFriends(Long id) throws UserNotFoundException {
        List<User> friends = new ArrayList<>();
        User user = inMemoryUserStorage.getUserById(id);
        if (user != null) {
            for (Long idFriend : user.getFriends()) {
                friends.add(inMemoryUserStorage.getUserById(idFriend));
            }
        }
        return friends;
    }

    /**
     * Returns list common friends
     */
    public List<User> getCommonFriends(Long id, Long otherId) throws UserNotFoundException {
        List<User> commonFriends = new ArrayList<>();
        User user1 = inMemoryUserStorage.getUserById(id);
        User user2 = inMemoryUserStorage.getUserById(otherId);
        if (user1 != null && user2 != null
                && user1.getFriends() != null && user2.getFriends() != null) {
            for (Long idFriend : user1.getFriends()) {
                if (user2.getFriends().contains(idFriend)) {
                    User friend = inMemoryUserStorage.getUserById(idFriend);
                    commonFriends.add(friend);
                }
            }
        }
        return commonFriends;
    }
}

package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.data.UserStorage;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(int id, int friendId) {
        User user = userStorage.getUserById(id);
        User friend = userStorage.getUserById(friendId);
        List<User> usersFriends;
        List<User> friendsFriends;
        if (user.getFriends() == null) {
            usersFriends = new ArrayList<>();
        } else {
            usersFriends = user.getFriends();
        }
        usersFriends.add(friend);
        user.setFriends(usersFriends);
        if (friend.getFriends() == null) {
            friendsFriends = new ArrayList<>();
        } else {
            friendsFriends = friend.getFriends();
        }
        friend.setFriends(friendsFriends);
    }

    public void deleteFriend(int id, int friendId) {
        User user = userStorage.getUserById(id);
        User friend = userStorage.getUserById(friendId);
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
    }

    public List<User> getFriends(int id) {
        return userStorage.getUserById(id).getFriends();
    }

    public List<User> getCommonFriends(int id, int otherId) {
        User user = userStorage.getUserById(id);
        User otherUser = userStorage.getUserById(otherId);
        List<User> commonFriends = new ArrayList<>();
        for (User userFriend : user.getFriends()) {
            for (User otherUserFriend : otherUser.getFriends()) {
                if (userFriend.equals(otherUserFriend)) {
                    commonFriends.add(userFriend);
                }
            }
        }
        return commonFriends;
    }
}
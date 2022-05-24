package ru.yandex.practicum.filmorate.data;

import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {

    void updateUser(Integer id, User user);

    void createUser(User user) throws EntityIsAlreadyExistException;

    List<User> getUsers();

    User getUserById(int id);

    void deleteUser(int id);
}

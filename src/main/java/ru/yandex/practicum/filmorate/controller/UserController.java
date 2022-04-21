package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.UserStorage;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public Map<String, User> getUsers() {
        return UserStorage.getUsers();
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws IllegalArgumentException, NullPointerException {
        UserStorage.updateUser(user.getLogin(), user);
    }

    @PostMapping
    public void createUser(@RequestBody User user) throws EntityIsAlreadyExistException, IllegalArgumentException {
        UserStorage.createUser(user);
    }
}

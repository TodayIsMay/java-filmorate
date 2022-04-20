package ru.yandex.practicum.filmorate.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

class UserStorageTest {
    private User user;

    @BeforeEach
    public void init() {
        user = new User(0, "mail@yandex.ru", "login", "name", LocalDate.of(
                1996, 5, 7));
    }

    @Test
    public void testThrowExceptionWhenEmptyEmail() {
        user.setEmail(null);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenBlankEmail() {
        user.setEmail(" ");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenEmailWithoutDog() { //"@" means "At", but "dog" is funnier, sorry :)
        user.setEmail("emailWithoutDog");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenEmptyLogin() {
        user.setLogin(null);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenLoginWithSpace() {
        user.setLogin("login with space");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
    }

    @Test
    public void testSettingLoginAsNameIfEmptyName() {
        user.setName(null);
        try {
            UserStorage.createUser(user);
        } catch (EntityIsAlreadyExistException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(user.getLogin(), user.getName());
        UserStorage.clear();
    }

    @Test
    public void testThrowExceptionWhenUserIsAlreadyExist() {
        try {
            UserStorage.createUser(user);
        } catch (EntityIsAlreadyExistException e) {
            e.printStackTrace();
        }
        EntityIsAlreadyExistException exception = Assertions.assertThrows(EntityIsAlreadyExistException.class, () ->
                UserStorage.createUser(user));
        Assertions.assertEquals("Такой пользователь уже существует!", exception.getMessage());
        UserStorage.clear();
    }

    @Test
    public void testUpdateUser() throws EntityIsAlreadyExistException {
        UserStorage.createUser(user);
        user.setName("new name");
        UserStorage.updateUser(user.getLogin(), user);
        Assertions.assertEquals(user, UserStorage.getUsers().get("login"));
        UserStorage.clear();
    }
}
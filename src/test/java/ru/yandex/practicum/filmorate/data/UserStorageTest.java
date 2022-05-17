//package ru.yandex.practicum.filmorate.data;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
//import ru.yandex.practicum.filmorate.model.User;
//
//import java.time.LocalDate;
//
//class UserStorageTest {
//    private InMemoryUserStorage userStorage;
//    private User user;
//
//    @BeforeEach
//    public void init() {
//        user = new User(0, "mail@yandex.ru", "login", "name", LocalDate.of(
//                1996, 5, 7));
//    }
//
//    @Test
//    public void testThrowExceptionWhenEmptyEmail() {
//        user.setEmail(null);
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenBlankEmail() {
//        user.setEmail(" ");
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenEmailWithoutDog() { //"@" means "At", but "dog" is funnier, sorry :)
//        user.setEmail("emailWithoutDog");
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenEmptyLogin() {
//        user.setLogin(null);
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenLoginWithSpace() {
//        user.setLogin("login with space");
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Некорректные данные пользователя!", exception.getMessage());
//    }
//
//    @Test
//    public void testSettingLoginAsNameIfEmptyName() {
//        user.setName(null);
//        try {
//            userStorage.createUser(user);
//        } catch (EntityIsAlreadyExistException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertEquals(user.getLogin(), user.getName());
//        InMemoryUserStorage.clear();
//    }
//
//    @Test
//    public void testThrowExceptionWhenUserIsAlreadyExist() {
//        try {
//            userStorage.createUser(user);
//        } catch (EntityIsAlreadyExistException e) {
//            e.printStackTrace();
//        }
//        EntityIsAlreadyExistException exception = Assertions.assertThrows(EntityIsAlreadyExistException.class, () ->
//                userStorage.createUser(user));
//        Assertions.assertEquals("Такой пользователь уже существует!", exception.getMessage());
//        InMemoryUserStorage.clear();
//    }
//
//    @Test
//    public void testUpdateUser() throws EntityIsAlreadyExistException {
//        userStorage.createUser(user);
//        user.setName("new name");
//        userStorage.updateUser(user.getId(), user);
//        Assertions.assertEquals(user, userStorage.getUsers().get("login"));
//        InMemoryUserStorage.clear();
//    }
//}
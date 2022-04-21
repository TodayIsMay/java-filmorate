package ru.yandex.practicum.filmorate.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Logger log = LoggerFactory.getLogger(FilmStorage.class);
    private static Map<String, User> users = new HashMap<>();

    /**
     * Creates new user and puts it in the map
     * @param user user info, that was get from http-request
     * @throws EntityIsAlreadyExistException if the user is already exists
     * @throws IllegalArgumentException if there is an incorrect user data
     */
    public static void createUser(User user) throws EntityIsAlreadyExistException {
        if (user.getName() == null) {
            user.setName(user.getLogin());
            log.info("В качестве имени пользователя использован логин");
        }
        if (users.containsKey(user.getLogin())) {
            log.error("Попытка добавить дублирующегося пользователя");
            throw new EntityIsAlreadyExistException("Такой пользователь уже существует!");
        } else if (!isValidUser(user.getEmail(), user.getLogin(), user.getBirthday())) {
            log.error("Некорректные данные пользователя");
            throw new IllegalArgumentException("Некорректные данные пользователя!");
        } else {
            users.put(user.getLogin(), user);
            log.info("Пользователь добавлен");
        }
    }

    /**
     * Updates the user by id.
     *
     * @param login Login of the user that is going to be updated
     * @param user new information
     * @throws IllegalArgumentException if there is an incorrect user data
     */
    public static void updateUser(String login, User user) throws IllegalArgumentException {
        if (!isValidUser(user.getEmail(), user.getLogin(), user.getBirthday())) {
            log.error("Неорректные данные пользователя");
            throw new IllegalArgumentException("Некорректные данные пользователя");
        } else if (!users.containsKey(login)) {
            throw new NullPointerException("Пользователь с таким логином не зарегестрирован!");
        } else {
            users.replace(login, user);
        }
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void clear() {
        users.clear();
    }

    private static boolean isValidUser(String email, String login, LocalDate birthday) {
        return isValidEmail(email) && isValidLogin(login) && isValidBirthday(birthday);
    }

    /**
     * Email shouldn't be null or empty <p>Email should contain "@" symbol</p>
     *
     * @param email user's email
     * @return true if everything is ok
     */
    private static boolean isValidEmail(String email) {
        if (email == null) {
            log.debug("email == null");
            return false;
        } else if (email.isBlank()) {
            log.debug("email is blank");
            return false;
        } else if (!email.contains("@")) {
            log.debug("Email doesn't contain @");
            return false;
        }
        return true;
    }

    /**
     * Login shouldn't be null or empty <p>Login shouldn't contain spaces</p>
     *
     * @param login user's login
     * @return true if everything is ok
     */
    private static boolean isValidLogin(String login) {
        if (login == null) {
            log.debug("login == null");
            return false;
        } else if (login.contains(" ")) {
            log.debug("login contains space");
            return false;
        }
        return true;
    }

    /**
     * Birthday shouldn't be in the future
     *
     * @param birthday user's birthday
     * @return true if everything is ok
     */
    private static boolean isValidBirthday(LocalDate birthday) {
        return !birthday.isAfter(LocalDate.now());
    }
}

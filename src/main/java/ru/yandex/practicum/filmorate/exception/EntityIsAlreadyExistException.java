package ru.yandex.practicum.filmorate.exception;

public class EntityIsAlreadyExistException extends Exception {
    public EntityIsAlreadyExistException(String message) {
        super(message);
    }
}

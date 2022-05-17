package ru.yandex.practicum.filmorate.exception;

public class InvalidIdException extends Exception{
    String message;
    public InvalidIdException(String message) {
        super(message);
    }
}

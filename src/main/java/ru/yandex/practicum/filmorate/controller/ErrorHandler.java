package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.InvalidIdException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(final InvalidIdException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(final NoSuchElementException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    // добавьте сюда класс ErrorResponse
    static class ErrorResponse {
        // название ошибки
        String error;
        // подробное описание
        String description;

        public ErrorResponse(String error, String description) {
            this.error = error;
            this.description = description;
        }

        // геттеры необходимы, чтобы Spring Boot мог получить значения полей
        public String getError() {
            return error;
        }

        public String getDescription() {
            return description;
        }
    }
}

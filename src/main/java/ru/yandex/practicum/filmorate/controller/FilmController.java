package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.FilmStorage;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {

    @GetMapping
    public Map<Integer, Film> getFilms(Map<Integer, Film> films) {
        return FilmStorage.getFilms();
    }

    @PutMapping
    public String updateFilm(@RequestBody Film film) {
        try {
            FilmStorage.updateFilm(film.getId(), film);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Фильм обновлён!";
    }

    @PostMapping
    public void addFilm(@RequestBody Film film) throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private void test() {
        System.out.println("ljk;ty ,snm 500");
    }
}

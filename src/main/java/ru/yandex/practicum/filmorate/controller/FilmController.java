package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.FilmStorage;
import ru.yandex.practicum.filmorate.data.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.data.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.InvalidIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.util.ValidationUtils;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage filmStorage;
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getFilms(Map<Integer, Film> films) {
        return filmStorage.getFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable final int id) throws NoSuchElementException {
        return filmStorage.getFilmById(id);
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) throws IllegalArgumentException {
        filmStorage.updateFilm(film.getId(), film);
        return film;
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) throws EntityIsAlreadyExistException {
        return filmStorage.addFilm(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) throws NoSuchElementException {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getTopLikedFilms(@RequestParam(defaultValue = "10", required = false) int count) {
        return filmService.getTopLikedFilms(count);
    }
}

package ru.yandex.practicum.filmorate.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.util.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class InMemoryFilmStorage implements FilmStorage{
    private static final Logger log = LoggerFactory.getLogger(InMemoryFilmStorage.class);
    private static final LocalDate FIRST_FILM_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    static Map<Integer, Film> films = new TreeMap<>();
    private int filmId = 0;

    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    public Film getFilmById(int id) throws NoSuchElementException{
        Film film = films.getOrDefault(id, null);
        if (film == null) {
            throw new NoSuchElementException("Фильм не найден!");
        } else {
            return film;
        }
    }

    /**
     * Updates film if the information passes validation
     *
     * @param id   Film's identification number
     * @param film Update information
     * @throws IllegalArgumentException
     */
    public void updateFilm(int id, Film film) throws IllegalArgumentException {
        if (id < 0) {
            log.error("Некорректный ID");
            throw new IllegalArgumentException("Некорректный ID");
        } else if (!ValidationUtils.isValidFilm(film)) {
            log.error("Некорректные данные фильма");
            throw new IllegalArgumentException("Некорректные данные фильма!");
        } else {
            log.info("Фильм обновлён");
            films.replace(id, film);
        }
    }

    /**
     * Adds film, if it passes validation
     *
     * @param film information that is going to be added
     * @throws EntityIsAlreadyExistException if such film is already exists
     */
    public Film addFilm(Film film) throws EntityIsAlreadyExistException, IllegalArgumentException {
        if (films.containsKey(film.getId())) {
            log.error("Попытка добавить уже существующий фильм");
            throw new EntityIsAlreadyExistException("Такой фильм уже существует!");
        } else if (!ValidationUtils.isValidFilm(film)) {
            log.error("Неккоректные данные фильма");
            throw new IllegalArgumentException("Некорректные данные фильма!");
        } else {
            if (ValidationUtils.isValidFilm(film)) {
                filmId = filmId + 1;
                film.setId(filmId);
                log.info("Фильм добавлен");
                films.put(film.getId(), film);
            } else {
                throw new IllegalArgumentException("Некорректные данные фильма!");
            }
            return film;
        }
    }

    public void clear() {
        films.clear();
        log.debug("Хранилище очищено");
    }
}

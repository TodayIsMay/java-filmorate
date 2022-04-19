package ru.yandex.practicum.filmorate.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FilmStorage {
    private static final Logger log = LoggerFactory.getLogger(FilmStorage.class);
    static Map<Integer, Film> films = new HashMap<>();

    public static Map<Integer, Film> getFilms() {
        return films;
    }

    /**
     * Updates film if the information passes validation
     * @param id Film's identification number
     * @param film Update information
     * @throws IllegalArgumentException
     */
    public static void updateFilm(int id, Film film) throws IllegalArgumentException {
        if (id < 0) {
            log.error("Некорректный ID");
            throw new IllegalArgumentException("Некорректный ID");
        } else if (!isValidFilm(film)) {
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
    public static void addFilm(Film film) throws EntityIsAlreadyExistException, IllegalArgumentException {
        if (films.containsKey(film.getId())) {
            log.error("Попытка добавить уже существующий фильм");
            throw new EntityIsAlreadyExistException("Такой фильм уже существует!");
        } else if (!isValidFilm(film)) {
            log.error("Неккоректные данные фильма");
            throw new IllegalArgumentException("Некорректные данные фильма!");
        } else {
            log.info("Фильм добавлен");
            films.put(film.getId(), film);
        }
    }

    public static void clear() {
        films.clear();
        log.debug("Хранилище очищено");
    }

    /**
     * Validates film. <p>Film's name shouldn't be empty</p>
     * <p>Release date of the film shouldn't be before 28.12.1895</p>
     * <p>Film's name shouldn't be longer than 200 characters</p>
     * <p>Film's duration shouldn't be negative</p>
     *
     * @param film information that is going to be added
     * @return true if everything is ok
     */
    private static boolean isValidFilm(Film film) {
        if (film.getName() == null | film.getName().isBlank()) {
            return false;
        } else if (film.getName().length() > 200) {
            return false;
        } else if (film.getRelease() != null && film.getRelease().isBefore(LocalDate.of(1895, 12, 28))) {
            return false;
        } else if (film.getDuration().isNegative()) {
            return false;
        }
        return true;
    }
}

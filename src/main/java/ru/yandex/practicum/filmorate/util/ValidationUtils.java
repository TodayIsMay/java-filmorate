package ru.yandex.practicum.filmorate.util;

import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidationUtils {
    private static final LocalDate FIRST_FILM_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    /**
     * Validates film. <p>Film's name shouldn't be empty</p>
     * <p>Release date of the film shouldn't be before 28.12.1895</p>
     * <p>Film's name shouldn't be longer than 200 characters</p>
     * <p>Film's duration shouldn't be negative</p>
     *
     * @param film information that is going to be added
     * @return true if everything is ok
     */
    public static boolean isValidFilm(Film film) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(film.getReleaseDate(), formatter);
        if (film.getName() == null || film.getName().isBlank()) {
            return false;
        } else if (film.getName().length() > 200) {
            return false;
        } else if (film.getReleaseDate() != null && releaseDate.isBefore(FIRST_FILM_RELEASE_DATE)) {
            return false;
        } else if (film.getDuration() < 0) {
            return false;
        } else if (film.getDescription().isBlank() || film.getDescription().isEmpty() ||
                film.getDescription().length() > 200) {
            return false;
        }
        return true;
    }

    public static boolean isValidId(int id) {
        if (id >= 0) {
            return true;
        }
        return false;
    }
}

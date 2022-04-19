package ru.yandex.practicum.filmorate.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;

class FilmStorageTest {
    private Film film;

    @BeforeEach
    public void init() {
        film = new Film(0, "name", "description", LocalDate.of(2022, 2, 2),
                Duration.ofHours(2));
    }

    @AfterEach
    public void clear() {
        FilmStorage.clear();
    }

    @Test
    public void testUpdateFilm() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        film.setName("new name");
        FilmStorage.updateFilm(0, film);
        Assertions.assertEquals(film, FilmStorage.getFilms().get(0));
    }

    @Test
    public void testThrowExceptionWhenIncorrectId() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                FilmStorage.updateFilm(-1, film));
        Assertions.assertEquals("Некорректный ID", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenEmptyName() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        film.setName(" ");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                FilmStorage.updateFilm(0, film));
        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenReleaseIsBeforeTheEdge() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        film.setRelease(LocalDate.of(1700, 1, 1));
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                FilmStorage.updateFilm(0, film));
        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
    }

    @Test
    public void testThrowExceptionWhenTooLongName() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        StringBuilder builder = new StringBuilder("L");
        for (int i = 0; i < 202; i++) {
            builder.append("o");
        }
        builder.append("ng name");
        film.setName(builder.toString());
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                FilmStorage.updateFilm(0, film));
        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
    }

    @Test
    public void shouldAddFilm() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        Assertions.assertEquals(film, FilmStorage.getFilms().get(0));
    }

    @Test
    public void testThrowsWhenFilmIsAlreadyExist() throws EntityIsAlreadyExistException {
        FilmStorage.addFilm(film);
        EntityIsAlreadyExistException exception = Assertions.assertThrows(EntityIsAlreadyExistException.class, () ->
                FilmStorage.addFilm(film));
        Assertions.assertEquals("Такой фильм уже существует!", exception.getMessage());
    }
}
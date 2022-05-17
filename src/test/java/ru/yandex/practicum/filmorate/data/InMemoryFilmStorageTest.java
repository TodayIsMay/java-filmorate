//package ru.yandex.practicum.filmorate.data;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
//import ru.yandex.practicum.filmorate.model.Film;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//class InMemoryFilmStorageTest {
//    private InMemoryFilmStorage filmStorage;
//    private Film film;
//
//    @BeforeEach
//    public void init() {
//        filmStorage = new InMemoryFilmStorage();
//        film = new Film("name", "description", LocalDate.of(2022, 2, 2),
//                Duration.ofHours(2));
//    }
//
//    @AfterEach
//    public void clear() {
//        filmStorage.clear();
//    }
//
//    @Test
//    public void testUpdateFilm() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        film.setName("new name");
//        filmStorage.updateFilm(0, film);
//        Assertions.assertEquals(film, filmStorage.getFilms().get(0));
//    }
//
//    @Test
//    public void testThrowExceptionWhenIncorrectId() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                filmStorage.updateFilm(-1, film));
//        Assertions.assertEquals("Некорректный ID", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenEmptyName() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        film.setName(" ");
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                filmStorage.updateFilm(0, film));
//        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenNullName() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        film.setName(null);
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                filmStorage.updateFilm(0, film));
//        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenReleaseIsBeforeTheEdge() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        film.setRelease(LocalDate.of(1700, 1, 1));
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                filmStorage.updateFilm(0, film));
//        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
//    }
//
//    @Test
//    public void testThrowExceptionWhenTooLongName() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        StringBuilder builder = new StringBuilder("L");
//        for (int i = 0; i < 202; i++) {
//            builder.append("o");
//        }
//        builder.append("ng name");
//        film.setName(builder.toString());
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                filmStorage.updateFilm(0, film));
//        Assertions.assertEquals("Некорректные данные фильма!", exception.getMessage());
//    }
//
//    @Test
//    public void shouldAddFilm() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        Assertions.assertEquals(film, filmStorage.getFilms().get(0));
//    }
//
//    @Test
//    public void testThrowsWhenFilmIsAlreadyExist() throws EntityIsAlreadyExistException {
//        filmStorage.addFilm(film);
//        EntityIsAlreadyExistException exception = Assertions.assertThrows(EntityIsAlreadyExistException.class, () ->
//                filmStorage.addFilm(film));
//        Assertions.assertEquals("Такой фильм уже существует!", exception.getMessage());
//    }
//}
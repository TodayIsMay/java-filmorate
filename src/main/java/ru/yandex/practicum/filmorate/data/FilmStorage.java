package ru.yandex.practicum.filmorate.data;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityIsAlreadyExistException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public interface FilmStorage {

    List<Film> getFilms();

    Film getFilmById(int id) throws NoSuchElementException;

    void updateFilm(int id, Film film);

    Film addFilm(Film film) throws EntityIsAlreadyExistException, IllegalArgumentException;

    void clear();
}

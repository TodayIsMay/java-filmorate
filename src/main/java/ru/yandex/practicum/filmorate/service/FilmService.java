package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.data.FilmStorage;
import ru.yandex.practicum.filmorate.data.UserStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public void addLike(int id, int userId) throws NoSuchElementException {
        User whoLiked = userStorage.getUserById(userId);
        if (whoLiked == null) {
            throw new NoSuchElementException("Пользователь не найден!");
        }
        Film film = filmStorage.getFilmById(id);
        if (film == null) {
            throw new NoSuchElementException("Фильм не найден!");
        }
        List<User> whoLikes = new ArrayList<>();
        whoLikes.add(whoLiked);
        film.setUsersLiked(whoLikes);
    }

    public void deleteLike(int id, int userId) throws NoSuchElementException{
        User whoDeletedLike = userStorage.getUserById(userId);
        Film film = filmStorage.getFilmById(id);
        film.getUsersLiked().remove(whoDeletedLike);
    }

    public List<Film> getTopLikedFilms(int count) {
        if (count == 0) {
            count = 10;
        }
        List<Film> top = new ArrayList<>();
        Comparator<Film> comparator = Comparator.comparing(o -> {
            if (o.getUsersLiked() == null) {
                return 1;
            } else {
                return o.getUsersLiked().size() * -1;
            }
        });
        return filmStorage.getFilms().stream().sorted(comparator).skip(0).limit(count).collect(Collectors.toList());
    }
}

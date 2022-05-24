package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Film {
    private String name;
    private String releaseDate;
    private String description;
    private int duration;

    @Builder.Default
    private int id = 0;
    @Builder.Default
    private List<User> usersLiked = new ArrayList<>();
}

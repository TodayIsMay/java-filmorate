package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {
    private String login;
    private String name;
    private String email;
    private LocalDate birthday;

    @Builder.Default
    private int id = 0;
    @Builder.Default
    private List<User> friends = new ArrayList<>();
}

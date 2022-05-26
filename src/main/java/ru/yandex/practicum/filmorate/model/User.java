package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Builder.Default
    private List<User> friends = new ArrayList<>();
}

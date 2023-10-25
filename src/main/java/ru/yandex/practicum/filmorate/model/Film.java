package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.constraints.Update;

import java.time.LocalDate;

@Data
@Builder
public class Film {

    private Long id;
    @NotBlank
    private String name;
    @Size(max = 200, message = "превышена максимальная длина - 200 символов")
    private String description;
    private LocalDate releaseDate;
    @Positive
    private Integer duration;
}

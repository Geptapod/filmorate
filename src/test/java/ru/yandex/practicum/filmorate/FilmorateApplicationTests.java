package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
class FilmorateApplicationTests {
    public Film film1 = Film.builder()
            .name("Punk")
            .description("Punk in NC")
            .releaseDate(LocalDate.of(2077, Month.SEPTEMBER, 8))
            .duration(300)
            .build();


    @Test
    void contextLoads() {

    }

}

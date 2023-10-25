package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Long, Film> films = new HashMap<>();
    private long newId = 1;

    @GetMapping
    public Collection<Film> getAllFilms() {
        log.debug("Current films number: {}", films.size());
        return films.values();
    }

    @SneakyThrows
    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        if (isValid(film)) {
            film.setId(generateNewId());
            films.put(film.getId(), film);
            log.debug("Film added by id: {}", film.getId());
        } else {
            throw new ValidationException("Film can't added. Not valid film data.");
        }
        return film;
    }

    @SneakyThrows
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (isValid(film)) {
            if (!films.containsKey(film.getId())) {
                throw new ValidationException("Film not exist. Not valid film data.");
            } else {
                films.put(film.getId(), film);
                log.debug("Film added by id: {}.", film.getId());
            }
        } else {
            throw new ValidationException("Film can't update. Not valid film data.");
        }
        return film;
    }

    private boolean isValid(Film film) {

        boolean isValid = true;
        final LocalDate NOT_BEFORE = LocalDate.of(1895, Month.DECEMBER, 28);

        if (film.getReleaseDate().isBefore(NOT_BEFORE)) {
            return isValid = false;
        }
        return isValid;
    }

    public long generateNewId() {
        return newId++;
    }
}



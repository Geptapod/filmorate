package ru.yandex.practicum.filmorate.storage;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public interface FilmStorage {

    ArrayList<Film> getAllFilms();

    Film addFilm(Film film) throws ValidationException;

    Film updateFilm(Film film) throws FilmNotFoundException, ValidationException;

    default boolean isValid(Film film) {

        boolean isValid = true;
        final LocalDate NOT_BEFORE = LocalDate.of(1895, Month.DECEMBER, 28);

        if (film.getReleaseDate().isBefore(NOT_BEFORE)) {
            return isValid = false;
        }
        return isValid;
    }

    long generateNewId();

    Film getFilmById(Long id) throws FilmNotFoundException;
}

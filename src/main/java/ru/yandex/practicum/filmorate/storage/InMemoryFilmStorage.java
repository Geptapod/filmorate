package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();
    private long newId = 1;

    public ArrayList<Film> getAllFilms() {
        log.debug("Current films number: {}", films.size());
        return new ArrayList<>(films.values());
    }

    public Film addFilm(Film film) throws ValidationException {
        if (isValid(film)) {
            film.setId(generateNewId());
            films.put(film.getId(), film);
            log.debug("Film added by id: {}", film.getId());
        } else {
            throw new ValidationException("Film can't added. Not valid film data.");
        }
        return film;
    }

    public Film updateFilm(Film film) throws FilmNotFoundException, ValidationException {
        if (isValid(film)) {
            if (!films.containsKey(film.getId())) {
                throw new FilmNotFoundException("Film not exist. Not valid film data.");
            } else {
                films.put(film.getId(), film);
                log.debug("Film added by id: {}.", film.getId());
            }
        } else {
            throw new ValidationException("Film can't update. Not valid film data.");
        }
        return film;
    }

    public long generateNewId() {
        return newId++;
    }

    public Film getFilmById(Long id) throws FilmNotFoundException {
        if (films.containsKey(id)) {
            return films.get(id);
        } else {
            throw new FilmNotFoundException(String.format("Film by id: %d - doesn't exist.", id));
        }
    }
}

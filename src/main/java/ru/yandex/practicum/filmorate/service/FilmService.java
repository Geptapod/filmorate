package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage inMemoryFilmStorage;

    public Film putLikeToFilm(Long id, Long userId) throws FilmNotFoundException {
        Film film = inMemoryFilmStorage.getFilmById(id);
        if (film != null) {
            film.addLike(userId);
        }
        return film;
    }

    public Film deleteLike(Long id, Long userId) throws FilmNotFoundException {
        Film film = inMemoryFilmStorage.getFilmById(id);
        if (film != null && id > 0 && userId > 0) {
            film.deleteLike(userId);
        } else {
            throw new FilmNotFoundException(String.format("Film id: %s; userId: %s", id, userId));
        }
        return film;
    }

    public List<Film> getPopularFilms(Integer count) {
        return inMemoryFilmStorage.getAllFilms().stream()
                .sorted((o1, o2) -> Long.compare(o2.getLikes().size(), o1.getLikes().size()))
                .limit(count)
                .toList();
    }
}

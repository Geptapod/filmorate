package ru.yandex.practicum.filmorate.model;

public enum TypeOfGenre {
    COMEDY("Comedy"),
    DRAMA("Drama"),
    CARTOON("Cartoon"),
    THRILLER("Thriller"),
    DOCUMENTARY("Documentary"),
    ACTION("Action");

    private final String displayName;

    TypeOfGenre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

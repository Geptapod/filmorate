package ru.yandex.practicum.filmorate.model;

public enum MpaRating {
    G("G — General Audiences"),
    PG("PG — Parental Guidance Suggested"),
    PG_13("PG-13 — Parents Strongly Cautioned"),
    R("R — Restricted"),
    NC_17("NC-17 — No One 17 and Under Admitted");

    private final String description;

    MpaRating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


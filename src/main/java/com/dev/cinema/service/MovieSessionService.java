package com.dev.cinema.service;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    MovieSession add(MovieSession session) throws DataProcessingException;

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date)
            throws DataProcessingException;

    List<MovieSession> getAll();

    MovieSession getById(Long id);
}

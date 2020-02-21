package com.dev.cinema.service;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;

    Movie getById(Long id);
}

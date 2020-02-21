package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/add")
    private MovieSession addMovieSession(@RequestBody MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime(), FORMATTER));
        movieSession.setMovie(movieSessionDto.getMovie());
        movieSession.setCinemaHall(movieSessionDto.getCinemaHall());
        return movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    private List<MovieSessionDto> getAllMovieSessions(@RequestParam(name = "movie_id") Long movieId,
                                                      @RequestParam(name = "date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return movieSessionService.findAvailableSessions(movieId, localDate)
                .stream()
                .map(this::getMovieSessionDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    private List<MovieSessionDto> getAll() {
        return movieSessionService
                .getAll()
                .stream()
                .map(this::getMovieSessionDto)
                .collect(Collectors.toList());
    }

    private MovieSessionDto getMovieSessionDto(MovieSession movieSession) {
        MovieSessionDto movieSessionDto = new MovieSessionDto();
        movieSessionDto.setCinemaHall(movieSession.getCinemaHall());
        movieSessionDto.setMovie(movieSession.getMovie());
        movieSessionDto.setShowTime(movieSession.getShowTime().format(FORMATTER));
        return movieSessionDto;
    }
}

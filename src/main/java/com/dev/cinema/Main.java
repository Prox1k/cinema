package com.dev.cinema;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws DataProcessingException {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        Movie movie1 = new Movie();
        movie1.setTitle("movie1");
        movieService.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("moviw2");
        movieService.add(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("movie3");
        movieService.add(movie3);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);

        cinemaHallService.getAll().forEach(System.out::println);

        CinemaHall hall1 = new CinemaHall();
        hall1.setCapacity(100);
        cinemaHallService.add(hall1);

        CinemaHall hall2 = new CinemaHall();
        hall2.setCapacity(200);
        cinemaHallService.add(hall2);

        CinemaHall hall3 = new CinemaHall();
        hall3.setCapacity(300);
        cinemaHallService.add(hall3);

        cinemaHallService.getAll().forEach(System.out::println);

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.findAvailableSessions(movie1.getId(), LocalDate.now())
                .forEach(System.out::println);

        MovieSession session1 = new MovieSession();
        session1.setMovie(movie1);
        session1.setCinemaHall(hall1);
        session1.setShowTime(LocalDateTime.now());
        movieSessionService.add(session1);

        MovieSession session2 = new MovieSession();
        session2.setMovie(movie1);
        session2.setCinemaHall(hall2);
        session2.setShowTime(LocalDateTime.now().plusDays(1));
        movieSessionService.add(session2);

        MovieSession session3 = new MovieSession();
        session3.setMovie(movie2);
        session3.setCinemaHall(hall1);
        session3.setShowTime(LocalDateTime.now().plusHours(2));
        movieSessionService.add(session3);

        MovieSession session4 = new MovieSession();
        session4.setMovie(movie3);
        session4.setCinemaHall(hall3);
        session4.setShowTime(LocalDateTime.now());
        movieSessionService.add(session4);

        movieSessionService.findAvailableSessions(movie1.getId(), LocalDate.now())
                .forEach(System.out::println);
    }
}
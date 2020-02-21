package com.dev.cinema.controllers;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataInjectionController {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService authenticationService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public DataInjectionController(MovieService movieService, CinemaHallService cinemaHallService,
                                   MovieSessionService movieSessionService,
                                   AuthenticationService authenticationService,
                                   ShoppingCartService shoppingCartService,
                                   OrderService orderService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/inject")
    public String inject() throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("movie1");
        movieService.add(movie);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("Red");
        cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(),
                LocalTime.of(15, 0)));
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L, LocalDate.now())
                .forEach(System.out::println);

        authenticationService.register("email@g.com", "pass1");
        User user = authenticationService.login("email@g.com", "pass1");

        shoppingCartService.addSession(movieSession, user);

        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        Order order = orderService.completeOrder(user);
        orderService.getOrderHistory(user).forEach(System.out::println);

        return "injected";
    }
}

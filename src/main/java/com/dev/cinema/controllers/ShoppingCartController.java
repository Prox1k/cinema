package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionDto;
import com.dev.cinema.dto.ShoppingCartDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  CinemaHallService cinemaHallService,
                                  MovieService movieService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @GetMapping("/byuser")
    private ShoppingCartDto getShoppingCartByUserId(Long userId) {
        User user = userService.findById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setUserId(user.getId());
        shoppingCartDto.setTickets(shoppingCart.getTickets());
        return shoppingCartDto;
    }

    @PostMapping("/addmoviesession")
    public String addMovieSession(@RequestBody MovieSessionDto movieSessionDto,
                                @RequestParam(value = "user_id") Long userId) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getById(movieSessionDto.getCinemaHallId()));
        movieSession.setMovie(movieService.getById(movieSessionDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime(), FORMATTER));
        User user = userService.findById(userId);
        shoppingCartService.addSession(movieSession, user);
        return "Added movie session";
    }
}

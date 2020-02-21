package com.dev.cinema.controllers;

import com.dev.cinema.dto.CinemaHallDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping("/")
    private List<CinemaHallDto> getAllCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::getCinemaHallDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    private void addCinemaHall(@RequestBody CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        cinemaHall.setCapacity(cinemaHallDto.getCapacity());
        cinemaHallService.add(cinemaHall);
    }

    private CinemaHallDto getCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        return cinemaHallDto;
    }
}

package com.dev.cinema.dto;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;

public class MovieSessionDto {
    private Movie movie;
    private CinemaHall cinemaHall;
    private String showTime;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}

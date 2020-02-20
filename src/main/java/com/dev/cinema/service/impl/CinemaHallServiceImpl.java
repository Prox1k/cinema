package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private  CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) throws DataProcessingException {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() throws DataProcessingException {
        return cinemaHallDao.getAll();
    }
}

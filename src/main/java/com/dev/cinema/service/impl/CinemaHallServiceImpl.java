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
    private static CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) throws DataProcessingException {
        try {
            return cinemaHallDao.add(cinemaHall);
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Error adding Cinema Hall", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() throws DataProcessingException {
        try {
            return cinemaHallDao.getAll();
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Error getting all Cinema Halls", e);
        }
    }
}

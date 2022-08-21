package cinema.service.impl;

import cinema.dao.CinemaHallDao;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    public static final Logger logger = LogManager.getLogger(CinemaHallServiceImpl.class);
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        CinemaHall createdCinemaHall = cinemaHallDao.add(cinemaHall);
        logger.info("A new cinema hall has been added. Params:{cinemaHallId:{}}",
                createdCinemaHall.getId());
        return createdCinemaHall;
    }

    @Override
    public CinemaHall get(Long id) {
        return cinemaHallDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get cinema hall by id " + id));
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}

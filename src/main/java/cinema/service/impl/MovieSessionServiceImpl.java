package cinema.service.impl;

import cinema.dao.MovieSessionDao;
import cinema.model.MovieSession;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    public static final Logger logger = LogManager.getLogger(MovieSessionServiceImpl.class);
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        MovieSession createdMovieSession = movieSessionDao.add(session);
        logger.info("A new movie session has been added. Params:{movieSessionId:{}, "
                + "cinemaHallId:{}, movieId:{}}", createdMovieSession.getId(),
                createdMovieSession.getCinemaHall().getId(),
                createdMovieSession.getMovie().getId());
        return createdMovieSession;
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id).orElseThrow(
                () -> new RuntimeException("Session with id " + id + " not found"));
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        MovieSession updatedMovieSession = movieSessionDao.update(movieSession);
        logger.info("A movie session has been updated. Params:{movieSessionId:{}}",
                updatedMovieSession.getId());
        return updatedMovieSession;
    }

    @Override
    public void delete(Long id) {
        movieSessionDao.delete(id);
        logger.info("A movie session has been deleted. Params:{movieSessionId:{}}", id);
    }
}

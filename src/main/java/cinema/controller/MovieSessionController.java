package cinema.controller;

import cinema.model.MovieSession;
import cinema.model.dto.request.MovieSessionRequestDto;
import cinema.model.dto.response.MovieSessionResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.mapper.RequestDtoMapper;
import cinema.service.mapper.ResponseDtoMapper;
import cinema.util.DateTimePatternUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final RequestDtoMapper<MovieSessionRequestDto, MovieSession>
            movieSessionRequestDtoMapper;
    private final ResponseDtoMapper<MovieSessionResponseDto, MovieSession>
            movieSessionResponseDtoMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
            RequestDtoMapper<MovieSessionRequestDto, MovieSession> movieSessionRequestDtoMapper,
            ResponseDtoMapper<MovieSessionResponseDto, MovieSession>
                                      movieSessionResponseDtoMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionRequestDtoMapper = movieSessionRequestDtoMapper;
        this.movieSessionResponseDtoMapper = movieSessionResponseDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieSessionResponseDto add(@RequestBody @Valid MovieSessionRequestDto requestDto) {
        MovieSession movieSession = movieSessionRequestDtoMapper.mapToModel(requestDto);
        movieSessionService.add(movieSession);
        return movieSessionResponseDtoMapper.mapToDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllAvailable(@RequestParam Long movieId,
                                                         @RequestParam
            @DateTimeFormat(pattern = DateTimePatternUtil.DATE_PATTERN)
                                                        LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public MovieSessionResponseDto update(@PathVariable Long id,
                                          @RequestBody @Valid MovieSessionRequestDto requestDto) {
        MovieSession movieSession = movieSessionRequestDtoMapper.mapToModel(requestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
        return movieSessionResponseDtoMapper.mapToDto(movieSession);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieSessionService.delete(id);
    }
}

package cinema.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MovieSessionRequestDto {
    @Positive
    @JsonProperty("movie_id")
    private Long movieId;
    @Positive
    @JsonProperty("cinema_hall_id")
    private Long cinemaHallId;
    @NotNull
    @JsonProperty("show_time")
    private LocalDateTime showTime;
}

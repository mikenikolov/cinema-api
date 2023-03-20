package cinema.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieSessionResponseDto {
    @JsonProperty("movie_session_id")
    private Long movieSessionId;
    @JsonProperty("movie_id")
    private Long movieId;
    @JsonProperty("movie_title")
    private String movieTitle;
    @JsonProperty("cinema_hall_id")
    private Long cinemaHallId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("show_time")
    private LocalDateTime showTime;
}

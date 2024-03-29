package cinema.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CinemaHallRequestDto {
    @Min(10)
    private Integer capacity;
    @Size(max = 200)
    private String description;
}
